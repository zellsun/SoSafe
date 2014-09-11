/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sosafe.view;

import java.applet.AudioClip;
import java.awt.Point;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import sosafe.control.ViewController;

/**
 *
 * @author Z
 */
public class SensorIcon extends JButton {
    
    private long sensorId;
    private ImageIcon downIcon;
    private ImageIcon offIcon;
    private ImageIcon onIcon;
    private ImageIcon hotIcon;
    private AudioClip alertClip;
    private Point dragOffset;
    private SensorState currState;

    public SensorIcon(long sensorId, ImageIcon downIcon, ImageIcon offIcon, ImageIcon onIcon, ImageIcon hotIcon, AudioClip alertClip) {
        super(onIcon);
        this.sensorId = sensorId;
        this.downIcon = downIcon;
        this.offIcon = offIcon;
        this.onIcon = onIcon;
        this.hotIcon = hotIcon;
        this.alertClip = alertClip;
        this.dragOffset = new Point(0,0);
        this.currState = new SensorStateOn();
    }
    
    public long getSensorId() {
        return sensorId;
    }

    public ImageIcon getDownIcon() {
        return downIcon;
    }
    
    public ImageIcon getOffIcon() {
        return offIcon;
    }
    
    public ImageIcon getOnIcon() {
        return onIcon;
    }

    public ImageIcon getHotIcon() {
        return hotIcon;
    }
    
    public AudioClip getAlertClip() {
        return alertClip;
    }
    
    public void sensorIconMouseDragged(java.awt.event.MouseEvent evt) {                                               
        // TODO add your handling code here:
        evt.getComponent().setLocation(evt.getComponent().getX() - dragOffset.x + evt.getX(), 
                evt.getComponent().getY() - dragOffset.y + evt.getY());
    }
    
    public void sensorIconMouseClicked(java.awt.event.MouseEvent evt) {                                               
        // TODO add your handling code here:
        // For demo purpose
        if (evt.getClickCount() == 2) {
            goNextState();
        }
    }
    public void sensorIconMousePressed(java.awt.event.MouseEvent evt) {                                               
        // TODO add your handling code here:
        dragOffset = evt.getPoint();
        evt.getComponent().getParent().setComponentZOrder(evt.getComponent(), 0);
    }
    
    public void sensorIconMouseReleased(java.awt.event.MouseEvent evt) {                                               
        // TODO add your handling code here:
        ViewController.getInstance().updateSensorScreenPos(sensorId, evt.getComponent().getX(), evt.getComponent().getY());
    }
    
    public void changeState(SensorState state) {
        currState = state;
        currState.handleChange(this);
    }
    
    public void goNextState() {
        currState = currState.nextState();
        currState.handleChange(this);
    }
}
