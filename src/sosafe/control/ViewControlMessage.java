/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sosafe.control;

/**
 *
 * @author Z
 */
public class ViewControlMessage {
    public static final String SENSOR_INFO_UPDATED = "SENSOR_INFO_UPDATED";
    public static final String ALARM_INFO_UPDATED = "ALARM_INFO_UPDATED";
    
    public final String type;
    public final Object data;
    
    public ViewControlMessage(String type, Object data) {
        this.type = type;
        this.data = data;
    }
}
