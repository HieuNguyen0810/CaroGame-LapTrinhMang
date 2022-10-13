/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SocketController;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author giang
 */
public class SocketData {
    private Socket socket = null;
    private ObjectInputStream objectInputStream = null;
    private ObjectOutputStream objectOutputStream = null;
    public SocketData(Socket socket ) {
        try {
        this.socket = socket;
        this.objectInputStream = new ObjectInputStream(socket.getInputStream());
        this.objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        } catch(IOException exception) {
            System.out.println(exception);
        }
    }

    public synchronized ObjectInputStream getObjectInputStream() {
        return objectInputStream;
    }


    public synchronized ObjectOutputStream getObjectOutputStream() {
        return objectOutputStream;
    }



    public synchronized Socket getSocket() {
        return socket;
    }


    
}
