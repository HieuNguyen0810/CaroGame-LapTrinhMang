
package caroserver;

import gamemechanic.GameController;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import SocketController.SocketHandler;
import SocketController.SocketController;
/**
 *
 * @author giang
 */
public class Caro {
    public final static int PORT = 3000;
    /**
     * @param args the command line arguments
     */
    public final static SocketController socketController = new SocketController();
    public final static GameController gameController = new GameController();
    public static void main(String[] args) {
        // TODO code application logic here
        try {
            ServerSocket serversocket = new ServerSocket(PORT);
            System.out.print("Server running on Port: ");
            System.out.println(PORT);
            Boolean isStopApp = false;
            while(!isStopApp) {
                Socket sk = serversocket.accept();
                
                SocketHandler sh = new SocketHandler(sk, socketController,gameController);
                Thread t = new Thread(sh);
                t.start();
            }
           
        } catch(IOException exception) {
            System.out.println(exception);
        }
    }
    
}
