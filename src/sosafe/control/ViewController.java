/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sosafe.control;

import java.util.List;
import java.util.Observable;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import sosafe.control.exceptions.NonexistentEntityException;
import sosafe.model.AlarmInfo;
import sosafe.model.CustomerInfo;
import sosafe.model.SensorInfo;
import sosafe.network.SensorProtocol;
import sosafe.network.SoSafeClient;
import sosafe.network.SoSafeServer;
import sosafe.network.client.TCPNetworkClientImpl;
import sosafe.network.client.UDPNetworkClientImpl;
import sosafe.network.server.TCPNetworkServerImpl;
import sosafe.network.server.UDPNetworkServerImpl;
import sosafe.view.LoginView;
import sosafe.view.MainView;
import sosafe.view.SignupView;

/**
 *
 * @author Z
 */
public class ViewController extends Observable implements Runnable {
    
    private CustomerInfo currentUser;
    private SystemState currState;
    
    private SoSafeServer sosafeServer;
    private SoSafeClient sosafeClient;
    
    private AtomicLong maxAlarmSerialNumber;
    private AtomicLong maxSensorId;
    
    private MainView mainView;
    private LoginView loginView;
    private SignupView signupView;
    private final EntityManagerFactory entityManagerFactory;
    private final AlarmInfoJpaController alarmInfoJpaController;
    private final CustomerInfoJpaController customerInfoJpaController;
    private final SensorInfoJpaController sensorInfoJpaController;
    
    private static final String PERSISTENCE_UNIT_NAME = "SoSafePU";
    private static final int SOSAFE_SERVER_PORT_NUMBER = 5925;
    
    private ViewController() {
        this.entityManagerFactory = javax.persistence.Persistence.createEntityManagerFactory(ViewController.PERSISTENCE_UNIT_NAME);
        this.alarmInfoJpaController = new AlarmInfoJpaController(entityManagerFactory);
        this.customerInfoJpaController = new CustomerInfoJpaController(entityManagerFactory);
        this.sensorInfoJpaController = new SensorInfoJpaController(entityManagerFactory);
        
        this.currState = new SystemStateActive();
        
        //this.sosafeServer = new SoSafeServer(new TCPNetworkServerImpl()); 
        this.sosafeClient = new SoSafeClient(new TCPNetworkClientImpl()); // Use TCP to send to Sensors 
        this.sosafeServer = new SoSafeServer(new UDPNetworkServerImpl()); // Use UDP to listen to Sensors
        //this.sosafeClient = new SoSafeClient(new UDPNetworkClientImpl());
        
        this.maxAlarmSerialNumber = new AtomicLong();
        this.maxSensorId = new AtomicLong();
    }
    
    public static ViewController getInstance() {
        return ViewControllerHolder.INSTANCE;
    }
    
    private static class ViewControllerHolder {
        private static final ViewController INSTANCE = new ViewController();
    }
    
    public CustomerInfo getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(CustomerInfo currentUser) {
        this.currentUser = currentUser;
    }
    
    public AlarmInfoJpaController getAlarmInfoJpaController() {
        return alarmInfoJpaController;
    }

    public CustomerInfoJpaController getCustomerInfoJpaController() {
        return customerInfoJpaController;
    }

    public SensorInfoJpaController getSensorInfoJpaController() {
        return sensorInfoJpaController;
    }
    
    public long getNewMaxAlarmSerialNumber() {
        return maxAlarmSerialNumber.incrementAndGet();
    }

    public long getNewMaxSensorId() {
        return maxSensorId.incrementAndGet();
    }
    
    public void login(CustomerInfo customerInfo) {
        refreshSensorStatus();
        
        currentUser = customerInfo;
        if (mainView == null) {
            mainView = new MainView();
        }
        if (loginView != null) {
            loginView.setVisible(false);
        }
        if (signupView != null) {
            signupView.setVisible(false);
        }
        mainView.setVisible(true);
        
        this.sosafeServer.listen(ViewController.SOSAFE_SERVER_PORT_NUMBER);
        Long maxSN = this.alarmInfoJpaController.getMaxSerialNumber();
        if (maxSN != null) {
            maxAlarmSerialNumber.set(maxSN);
        }
        Long maxID = this.sensorInfoJpaController.getMaxSensorId();
        if (maxID != null) {
            maxSensorId.set(maxID);
        }
    }
    
    public void logoff() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
        
    public void signup() {
        if (signupView == null) {
            signupView = new SignupView();
        }
        loginView.setVisible(false);
        signupView.setVisible(true);
    }
    
    @Override
    public void run() {
        if (loginView == null) {
            loginView = new LoginView();
        }
        loginView.setVisible(true);
    }
    
    public void launch() {
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(this);
    }
    
    public void changeState(SystemState state) {
        currState = state;
        currState.handleChange(this);
    }
    
    public void goNextState() {
        currState = currState.nextState();
        currState.handleChange(this);
    }
    
    public interface SystemState {
        void handleChange(ViewController vc);
        SystemState nextState();
    }

    public class SystemStateActive implements SystemState {
        @Override
        public void handleChange(ViewController vc) {
            mainView.goActiveState();
        }

        @Override
        public SystemState nextState() {
            return new SystemStateDeactive();
        }
    }

    public class SystemStateDeactive implements SystemState {
        @Override
        public void handleChange(ViewController vc) {
            mainView.goDeactiveState();
        }

        @Override
        public SystemState nextState() {
            return new SystemStateActive();
        }
    }
    public void alarmUpdated() {
        super.setChanged();
        super.notifyObservers(new ViewControlMessage(ViewControlMessage.ALARM_INFO_UPDATED, null));
    }
    
    public void alarmUpdated(AlarmInfo alarm) {
        super.setChanged();
        super.notifyObservers(new ViewControlMessage(ViewControlMessage.ALARM_INFO_UPDATED, alarm));
    }
    
    public void sensorUpdated() {
        super.setChanged();
        super.notifyObservers(new ViewControlMessage(ViewControlMessage.SENSOR_INFO_UPDATED, null));
    }
    
    public void sensorUpdated(SensorInfo sensor) {
        super.setChanged();
        super.notifyObservers(new ViewControlMessage(ViewControlMessage.SENSOR_INFO_UPDATED, sensor));
    } 
    
    public void updateSensorScreenPos(long sensorId, int screenPosX, int screenPosY) {
        String newPos = screenPosX + "," + screenPosY;
        SensorInfo sensor = sensorInfoJpaController.findSensorInfo(sensorId);
        sensor.setScreenPosition(newPos);
        try {
            sensorInfoJpaController.edit(sensor);
            sensorUpdated(sensor);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ViewController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateSensorStatus(long sensorId, short status) {
        SensorInfo sensor = sensorInfoJpaController.findSensorInfo(sensorId);
        sensor.setSensorStatus(status);
        try {
            sensorInfoJpaController.edit(sensor);
            sensorUpdated(sensor);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ViewController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void refreshSensorStatus() {
        //TODO: For current version, it just starts with normal status
        //In the future, it can start with real status
        List<SensorInfo> list = sensorInfoJpaController.findSensorInfoEntities();
        for (SensorInfo sensor : list) {
            sensor.setSensorStatus(SensorProtocol.SENSOR_STATUS_ON);
            try {
                sensorInfoJpaController.edit(sensor);
                sensorUpdated(sensor);
            } catch (NonexistentEntityException ex) {
                Logger.getLogger(ViewController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(ViewController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
