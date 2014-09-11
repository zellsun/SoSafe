/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sosafe.network.client;

/**
 *
 * @author Z
 */
public class NetworkClient {
    private final NetworkClientImpl sender;

    public NetworkClient(NetworkClientImpl sender) {
        this.sender = sender;
    }
    
    public void send(String host, int port, byte[] data) {
        this.sender.send(host, port, data);
    }
}
