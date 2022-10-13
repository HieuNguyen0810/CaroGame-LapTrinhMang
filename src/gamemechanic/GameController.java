/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gamemechanic;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 *
 * @author giang
 */
public class GameController {
    private final Map<String, Game> games = new HashMap<>();
    public Game getGame(String id){
        return games.get(id);
    }
    
    public String addGame() {
        Game g = new Game();
        String id = UUID.randomUUID().toString();
        games.put(id, g);
        return id;
    }
}
