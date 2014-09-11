/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sosafe.network;

/**
 *
 * @author Z
 */
public class DataPacket {
    public static final int DEFAULT_DATA_SIZE = 1024;
    
    String host;
    int port;
    byte[] data;
    
    public DataPacket() {
        this.host = "";
        this.port = 0;
        this.data = new byte[DataPacket.DEFAULT_DATA_SIZE];
    }
    
    public DataPacket(String host, int port, byte[] data) {
        this.host = host;
        this.port = port;
        this.data = data;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
    
    @Override
    public String toString() {
        return " Host: " + host + " Port: " + port + " Data: " + new String(data);
    }
}
