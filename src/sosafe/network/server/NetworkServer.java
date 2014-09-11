/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sosafe.network.server;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import sosafe.network.DataPacket;


/**
 *
 * @author Z
 */
public abstract class NetworkServer extends Thread {
    private static final int QUEUE_CAPACITY = 10;
    BlockingQueue<DataPacket> message_queue;
    private final NetworkServerImpl receiver;

    public NetworkServer(NetworkServerImpl receiver) {
        this.message_queue = new ArrayBlockingQueue<>(NetworkServer.QUEUE_CAPACITY);
        this.receiver = receiver;
    }
    
    public void listen(int port) {
        this.receiver.listen(port, this.message_queue);
        if (!this.isAlive()) {
            this.start();
        }
    }
    
    public abstract void process(DataPacket packet);
    
    @Override
    public void run() {
        while (true) {
            try {
                DataPacket packet = this.message_queue.take();
                process(packet);
            } catch (InterruptedException ex) {
                Logger.getLogger(NetworkServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
