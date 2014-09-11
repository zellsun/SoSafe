/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sosafe.network.client;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import sosafe.network.DataPacket;
import sosafe.network.NetworkTrafficLogger;

/**
 *
 * @author Z
 */
public class TCPNetworkClientImpl extends NetworkClientImpl {

    public TCPNetworkClientImpl() {
        super();
    }

    @Override
    public void run() {
        while (true) {
            try {
                DataPacket packet = super.message_queue.take();
                Socket socket = new Socket(InetAddress.getByName(packet.getHost()), packet.getPort());
                OutputStream output = socket.getOutputStream();
                output.write(packet.getData());
                output.flush();
                output.close();
                socket.close();
                NetworkTrafficLogger.getInstance().writeSendLogToTextArea(packet);
            } catch (InterruptedException ex) {
                Logger.getLogger(TCPNetworkClientImpl.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(TCPNetworkClientImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
