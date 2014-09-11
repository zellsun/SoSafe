/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sosafe.network.server;

import java.util.concurrent.BlockingQueue;
import sosafe.network.DataPacket;

/**
 *
 * @author Z
 */
public abstract class NetworkServerImpl extends Thread {
    int port;
    BlockingQueue<DataPacket> message_queue;

    public NetworkServerImpl() {
        super();
    }
    
    public void listen(int port, BlockingQueue<DataPacket> message_queue) {
        this.port = port;
        this.message_queue = message_queue;
        
        if (!this.isAlive()) {
            this.start();
        }
    }
    
    @Override
    public abstract void run();
}
