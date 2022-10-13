package controller;

import client.Client;
import constants.Constants;
import message.LoginMessageResponse;
import gamemechanic.StepPosition;
import message.ResponseMessage;
import model.User;
import view.BoardView;
import view.ChooseOption;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketHandler implements Runnable {
    private BufferedReader is;
    private PrintWriter os;

    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    private Socket client;

    public SocketHandler() throws IOException {
        this.client = new Socket("127.0.0.1", Constants.SERVER_PORT);
    }

    public void sendObjectToServer(Object obj) throws IOException {
        oos.writeObject(obj);
        oos.flush();
    }


    @Override
    public void run() {
        try {
            this.oos = new ObjectOutputStream(client.getOutputStream());
            this.ois = new ObjectInputStream(client.getInputStream());

            Object obj = null;
            while (true) {
//                String message = is.readLine();
                obj = ois.readObject();
                if (obj == null) {
                    break;
                }

                // verify đăng nhập
                if (obj instanceof LoginMessageResponse) {
                    int status = ((LoginMessageResponse) obj).getStatus();
                    if (status == 200 && ((LoginMessageResponse) obj).getMessage().equals("login success")) {
                        System.out.println("Đăng nhập thành công");
                        Client.thisUser = new User(Client.unVerifyUser.getUsername(), Client.unVerifyUser.getPassword());
                        Client.loginForm.setVisible(false);
                        Client.chooseOption = new ChooseOption();
                    } else {
                        System.out.println("Đăng nhập thất bại");
                        Client.loginForm.getNotLogin().setVisible(true);
                    }
                } else if (obj instanceof ResponseMessage) {
                    if (((ResponseMessage) obj).getStatus() == 200
                            && ((ResponseMessage) obj).getMessage().equals("create success")) {
                        Client.chooseOption.setVisible(false);
                        Client.boardView = new BoardView("room master");
                    } else if (((ResponseMessage) obj).getStatus() == 200
                            && ((ResponseMessage) obj).getMessage().equals("joined")) {
                        Client.chooseOption.setVisible(false);
                        Client.boardView = new BoardView("client");
                    } else if (((ResponseMessage) obj).getStatus() == 400
                            && ((ResponseMessage) obj).getMessage().equals("wrong id")) {
                        Client.chooseOption.setWrongIdWarning();
                    }
                } else if (obj instanceof StepPosition) { // đi 1 nước của đối thủ
                    int x = ((StepPosition) obj).getX();
                    int y = ((StepPosition) obj).getY();
                    String role = ((StepPosition) obj).getRole();
                    System.out.println(obj);
                    Client.boardView.updatePosition(role, x, y);
                } else if (obj instanceof String) {
                    Client.id = (String) obj;
                    System.out.println(Client.id);
                }
            }
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
