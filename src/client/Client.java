package client;

import controller.SocketHandler;
import model.User;
import view.BoardView;
import view.ChooseOption;
import view.LoginForm;

import java.io.IOException;

public class Client {
    public static BoardView boardView;
    public static LoginForm loginForm;
    public static SocketHandler socketHandler;
    public static ChooseOption chooseOption;
    public static String id;
    public static User thisUser;
    public static User unVerifyUser;


    public static void main(String[] args) throws IOException {
        loginForm = new LoginForm();
//        boardView = new BoardView("room master");
        socketHandler = new SocketHandler();
        socketHandler.run();
    }
}
