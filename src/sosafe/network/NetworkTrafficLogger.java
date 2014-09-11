/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sosafe.network;

import java.util.Calendar;
import javax.swing.JTextArea;

/**
 *
 * @author Z
 */
public class NetworkTrafficLogger {
    private javax.swing.JTextArea logTextArea;
    
    private NetworkTrafficLogger() {
    }
    
    public static NetworkTrafficLogger getInstance() {
        return NetworkTrafficLoggerHolder.INSTANCE;
    }
    
    private static class NetworkTrafficLoggerHolder {
        private static final NetworkTrafficLogger INSTANCE = new NetworkTrafficLogger();
    }
    
    public void initLogTextArea(JTextArea logTextArea) {
        this.logTextArea = logTextArea;
    }
    
    public void writeLogToTextArea(String log) {
        if (logTextArea != null) {
            logTextArea.append("Time: " + Calendar.getInstance().getTime().toString() + "; " + log + "\n");
        }
    }
    
    public void writeSendLogToTextArea(DataPacket packet) {
        if (logTextArea != null) {
            logTextArea.append("Time: " + Calendar.getInstance().getTime().toString() + "; Send to " + packet.toString() + "\n");
        }
    }
    
    public void writeReceiveLogToTextArea(DataPacket packet) {
        if (logTextArea != null) {
            logTextArea.append("Time: " + Calendar.getInstance().getTime().toString() + "; Receive from " + packet.toString() + "\n");
        }
    }
}
