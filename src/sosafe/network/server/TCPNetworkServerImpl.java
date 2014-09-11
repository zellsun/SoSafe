/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sosafe.network.server;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import sosafe.network.DataPacket;
import sosafe.network.NetworkTrafficLogger;
import sosafe.network.AlarmProtocol;

/**
 *
 * @author Z
 */
public class TCPNetworkServerImpl extends NetworkServerImpl {

    public TCPNetworkServerImpl() {
        super();
    }

    @Override
    public void run() {
        
        try {
            ServerSocket serverSocket = new ServerSocket(super.port);
            NetworkTrafficLogger.getInstance().writeLogToTextArea("Start listening to TCP on Port: " + super.port);
            while (true) {
                try {
                    Socket socket = serverSocket.accept();
                    DataPacket packet = new DataPacket(socket.getInetAddress().getHostName(), socket.getPort(), new byte[DataPacket.DEFAULT_DATA_SIZE]);
                    InputStream input = socket.getInputStream();
                    input.read(packet.getData());
                    input.close();
                    super.message_queue.put(packet);
                    NetworkTrafficLogger.getInstance().writeReceiveLogToTextArea(packet);
                } catch (InterruptedException ex) {
                    Logger.getLogger(TCPNetworkServerImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(TCPNetworkServerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
