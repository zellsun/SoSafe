/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sosafe.network;

/**
 *
 * @author Z
 */
public class SensorProtocol {
    public static final short SENSOR_TYPE_FIRE = 1; 
    public static final short SENSOR_TYPE_SECURITY = 2;
    
    public static final short SENSOR_STATUS_DOWN = -1;
    public static final short SENSOR_STATUS_OFF = 0;
    public static final short SENSOR_STATUS_ON = 1;
    public static final short SENSOR_STATUS_HOT = 2;
}
