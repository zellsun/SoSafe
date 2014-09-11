/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sosafe.network.server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;
import sosafe.network.DataPacket;
import sosafe.network.NetworkTrafficLogger;
import sosafe.network.AlarmProtocol;

/**
 *
 * @author Z
 */
public class UDPNetworkServerImpl extends NetworkServerImpl {

    public UDPNetworkServerImpl() {
        super();
    }
    
    @Override
    public void run() {
        try {
            DatagramSocket serverSocket = new DatagramSocket(super.port);
            NetworkTrafficLogger.getInstance().writeLogToTextArea("Start listening to UDP on Port: " + super.port);
            while (true) {
                try {
                    byte[] receiveData = new byte[DataPacket.DEFAULT_DATA_SIZE];
                    DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                    serverSocket.receive(receivePacket);
                    DataPacket packet = new DataPacket(receivePacket.getAddress().getHostName(), receivePacket.getPort(), receivePacket.getData());
                    super.message_queue.put(packet);
                    NetworkTrafficLogger.getInstance().writeReceiveLogToTextArea(packet);
                } catch (IOException ex) {
                    Logger.getLogger(UDPNetworkServerImpl.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InterruptedException ex) {
                    Logger.getLogger(UDPNetworkServerImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (SocketException ex) {
            Logger.getLogger(UDPNetworkServerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
