/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sosafe.control;

import sosafe.network.SensorProtocol;
import java.applet.Applet;
import java.applet.AudioClip;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import javax.swing.ImageIcon;
import sosafe.model.SensorInfo;
import sosafe.view.SensorIcon;

/**
 *
 * @author Z
 */
public class FlyweightSensorIconFactory {

    private static final Map<String, ImageIcon> sensorImageIcons = new HashMap<>();
    private static final Map<String, AudioClip> sensorAudioClips = new HashMap<>();
    
    private static ImageIcon getImageIcon(String key) {
        if (!sensorImageIcons.containsKey(key)) {
            URL url = FlyweightSensorIconFactory.class.getResource(key);
            ImageIcon icon = new ImageIcon(url);
            sensorImageIcons.put(key, icon);
        }
        return sensorImageIcons.get(key);
    }
    
    private static AudioClip getAudioClip(String key) {
        if (!sensorAudioClips.containsKey(key)) {
            URL url = FlyweightSensorIconFactory.class.getResource(key);
            AudioClip clip = Applet.newAudioClip(url);
            sensorAudioClips.put(key, clip);
        }
        return sensorAudioClips.get(key);
    }
    
    public static SensorIcon getSensorIcon(SensorInfo sensorInfo) {
        
        ImageIcon downIcon = null;
        ImageIcon offIcon = null;
        ImageIcon onIcon = null;
        ImageIcon hotIcon = null;
        AudioClip alertClip = null;

        switch (sensorInfo.getSensorType()) {
            case SensorProtocol.SENSOR_TYPE_FIRE:
                downIcon = getImageIcon("/sosafe/res/FireAlarmDown.gif");
                offIcon = getImageIcon("/sosafe/res/FireAlarmOff.gif");
                onIcon = getImageIcon("/sosafe/res/FireAlarmOn.gif");
                hotIcon = getImageIcon("/sosafe/res/FireAlarmAlert.gif");
                alertClip = getAudioClip("/sosafe/res/FireAlarm.wav");
                break;
            case SensorProtocol.SENSOR_TYPE_SECURITY:
                downIcon = getImageIcon("/sosafe/res/SecurityAlarmDown.gif");
                offIcon = getImageIcon("/sosafe/res/SecurityAlarmOff.gif");
                onIcon = getImageIcon("/sosafe/res/SecurityAlarmOn.gif");
                hotIcon = getImageIcon("/sosafe/res/SecurityAlarmAlert.gif");
                alertClip = getAudioClip("/sosafe/res/SecurityAlarm.wav");
                break;
            default:
                break;
        }
        
        SensorIcon sensorIcon = new SensorIcon(sensorInfo.getSensorId(), downIcon, offIcon, onIcon, hotIcon, alertClip);
                
        sensorIcon.setRolloverEnabled(false);
        sensorIcon.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            @Override
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                sensorIcon.sensorIconMouseDragged(evt);
            }
        });
        sensorIcon.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sensorIcon.sensorIconMouseClicked(evt);
            }
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                sensorIcon.sensorIconMousePressed(evt);
            }

            @Override
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                sensorIcon.sensorIconMouseReleased(evt);
            }
        });
        return sensorIcon;
    }
}
