/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sosafe.network.client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import sosafe.network.DataPacket;
import sosafe.network.NetworkTrafficLogger;

/**
 *
 * @author Z
 */
public class UDPNetworkClientImpl extends NetworkClientImpl {

    public UDPNetworkClientImpl() {
        super();
    }

    @Override
    public void run() {
        while (true) {
            try {
                DataPacket packet = super.message_queue.take();
                DatagramPacket datagram = new DatagramPacket(packet.getData(), packet.getData().length, InetAddress.getByName(packet.getHost()), packet.getPort());
                DatagramSocket socket = new DatagramSocket();
                socket.send(datagram);
                socket.close();
                NetworkTrafficLogger.getInstance().writeSendLogToTextArea(packet);
            } catch (InterruptedException ex) {
                Logger.getLogger(TCPNetworkClientImpl.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnknownHostException ex) {
                Logger.getLogger(UDPNetworkClientImpl.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SocketException ex) {
                Logger.getLogger(UDPNetworkClientImpl.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(UDPNetworkClientImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
