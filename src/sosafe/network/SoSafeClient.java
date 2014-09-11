/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sosafe.network;

import sosafe.network.client.NetworkClient;
import sosafe.network.client.NetworkClientImpl;

/**
 *
 * @author Z
 */
public class SoSafeClient extends NetworkClient {

    public SoSafeClient(NetworkClientImpl sender) {
        super(sender);
    }
    
    public void send(DataPacket packet) {
        super.send(packet.getHost(), packet.getPort(), packet.getData());
    }
}
