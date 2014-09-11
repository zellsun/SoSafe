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
public class AlarmProtocol {
    
    public static final int INDEX_DATA_TYPE = 0;
    public static final int INDEX_ALARM_TYPE = 1;
    public static final int INDEX_SENSOR_ID = 2;
    public static final int INDEX_ALARM_MEMO = 3;
    
    public static final String DEFAULT_SEPARATOR = ",";
    
    public static final short DATA_TYPE_ALARM = 1;

    public static final short ALARM_TYPE_FIRE = 1;
    public static final short ALARM_TYPE_SECURITY = 2;
    public static final short ALARM_TYPE_FAULT = 3;
    
    public static final short ALARM_STATUS_NEW = 1;
    public static final short ALARM_STATUS_CLEAR = 2;
    
    public static final String ALARM_MEMO_FIRE = "Fire Alarm";
    public static final String ALARM_MEMO_SECURITY = "Security Alarm";
    public static final String ALARM_MEMO_FAULT = "Fault Alarm";
    
    public static byte[] generateAlarm(short alarmType, long sensorId, String alarmMemo) {
        String output = AlarmProtocol.DATA_TYPE_ALARM + AlarmProtocol.DEFAULT_SEPARATOR 
                + alarmType + AlarmProtocol.DEFAULT_SEPARATOR 
                + sensorId + AlarmProtocol.DEFAULT_SEPARATOR 
                + alarmMemo;
        return output.getBytes();
    }
}
