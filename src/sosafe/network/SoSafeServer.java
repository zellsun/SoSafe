/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sosafe.network;

import java.util.Arrays;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import sosafe.control.ViewController;
import sosafe.model.AlarmInfo;
import sosafe.network.server.NetworkServer;
import sosafe.network.server.NetworkServerImpl;

/**
 *
 * @author Z
 */
public class SoSafeServer extends NetworkServer {

    public SoSafeServer(NetworkServerImpl receiver) {
        super(receiver);
    }
    
    public AlarmInfo parseAlarmData(byte[] data) {
        AlarmInfo alarm = null;
        String[] input = new String(data).split(AlarmProtocol.DEFAULT_SEPARATOR);
        if (Short.parseShort(input[AlarmProtocol.INDEX_DATA_TYPE].trim()) == AlarmProtocol.DATA_TYPE_ALARM) {
            alarm = new AlarmInfo();
            alarm.setSerialNumber(ViewController.getInstance().getNewMaxAlarmSerialNumber());
            alarm.setAlarmType(Short.valueOf(input[AlarmProtocol.INDEX_ALARM_TYPE].trim()));
            alarm.setAlarmStatus(AlarmProtocol.ALARM_STATUS_NEW);
            alarm.setRelatedSensorId(ViewController.getInstance().getSensorInfoJpaController().findSensorInfo(Long.valueOf(input[AlarmProtocol.INDEX_SENSOR_ID].trim())));
            alarm.setAlarmMemo(input[AlarmProtocol.INDEX_ALARM_MEMO].trim());
            alarm.setOccurrenceTime(new Date());
        }        
        return alarm;
    }
    
    @Override
    public void process(DataPacket packet) {
        NetworkTrafficLogger.getInstance().writeLogToTextArea("Process Data: " + new String(packet.getData()));
        try {
            AlarmInfo alarm = this.parseAlarmData(packet.getData());
            ViewController.getInstance().getAlarmInfoJpaController().create(alarm);
            ViewController.getInstance().alarmUpdated(alarm);
        } catch (Exception ex) {
            Logger.getLogger(SoSafeServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
