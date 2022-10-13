/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SocketController;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 *
 * @author giang
 */
public class SocketController {
    private final Map<String, SocketData> sockets = new HashMap<>();
    public String addSocket(SocketData sd) {
        UUID ui = UUID.randomUUID();
        String id = ui.toString();
        sockets.put(id, sd);
        return id;
    }
    
    public void removeSocket(String socketId) {
        sockets.remove(socketId);
    }
    
    public SocketData findSocket(String socketID) {
        return sockets.get(socketID);
    }
    
}
