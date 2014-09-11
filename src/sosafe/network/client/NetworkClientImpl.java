/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sosafe.network.client;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import sosafe.network.DataPacket;

/**
 *
 * @author Z
 */
public abstract class NetworkClientImpl extends Thread {
    private static final int QUEUE_CAPACITY = 10;
    BlockingQueue<DataPacket> message_queue;

    public NetworkClientImpl() {
        super();
        this.message_queue = new ArrayBlockingQueue<>(NetworkClientImpl.QUEUE_CAPACITY);
    }
    
    public void send(String host, int port, byte[] data) {
        if (!this.isAlive()) {
            this.start();
        }
                
        DataPacket packet = new DataPacket(host, port, data);
        try {
            this.message_queue.put(packet);
        } catch (InterruptedException ex) {
            Logger.getLogger(NetworkClientImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public abstract void run();
}
