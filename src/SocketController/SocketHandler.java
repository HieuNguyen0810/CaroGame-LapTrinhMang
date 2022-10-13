/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SocketController;

import gamemechanic.Game;
import gamemechanic.GameController;
import gamemechanic.StepPosition;
import message.LoginMessageRequest;
import message.LoginMessageResponse;
import message.ResponseMessage;
import UserController.User;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * @author giang
 */
public class SocketHandler implements Runnable {
    private final Socket socket;
    private final SocketController socketController;
    private final GameController gameController;

    public SocketHandler(Socket sk, SocketController socketController, GameController gameController) {
        this.socket = sk;
        this.socketController = socketController;
        this.gameController = gameController;
    }

    @Override
    public void run() {
        try {
            DataInputStream di = new DataInputStream(socket.getInputStream());
            SocketData curentSocketData = new SocketData(socket);
            while (true || !curentSocketData.getSocket().isClosed()) {

                Object obj = curentSocketData.getObjectInputStream().readObject();
                // verify user
                if (obj instanceof LoginMessageRequest) {
                    LoginMessageRequest lmr = (LoginMessageRequest) obj;

                    boolean isLogin = User.isRegisted(lmr.getUsername(), lmr.getPassword());

                    if (isLogin) {
                        LoginMessageResponse lmresponse = new LoginMessageResponse(200, "login success");
                        curentSocketData.getObjectOutputStream().writeObject(lmresponse);
                    } else {
                        LoginMessageResponse lmresponse = new LoginMessageResponse(400, "login failed");
                        curentSocketData.getObjectOutputStream().writeObject(lmresponse);
                        curentSocketData.getSocket().close();
                    }
                }
//                else if (obj instanceof StepPosition) {
//                    System.out.println(((StepPosition) obj).getX() + " " + ((StepPosition) obj).getRole());
//                    StepPosition newStep = new StepPosition("o", 2, 2);
//                    curentSocketData.getObjectOutputStream().writeObject(newStep);
//                }


                String currSocketId = socketController.addSocket(curentSocketData);
                // action
                String action = (String) curentSocketData.getObjectInputStream().readObject();
                System.out.println(action);
                switch (action) {
                    case "create": {
                        String id = gameController.addGame();
                        Game g = gameController.getGame(id);
                        g.setRolex(currSocketId);
                        curentSocketData.getObjectOutputStream().writeObject(new ResponseMessage(200, "create success"));
                        curentSocketData.getObjectOutputStream().writeObject(id);
                        SocketData socketOpponet = null;

                        while (socketOpponet == null) {

                            if (socketController.findSocket(g.getRoley()) != null) {
                                socketOpponet = socketController.findSocket(g.getRoley());
                                break;
                            }

                        }
                        while (!g.isOver()) {
                            Thread.sleep(500);
                            if (g.winner() == null) {

                                if (g.getRole().equals("x")) {

                                    StepPosition step = (StepPosition) curentSocketData.getObjectInputStream().readObject();
                                    System.out.println("nguoi khoi tao" + step.getRole());
                                    g.addPosition(step);

                                    socketOpponet.getObjectOutputStream().writeObject(step);
                                }

                            }
                        }
                    }
                    case "join": {
                        Game gopp = null;
                        while (gopp == null) {
                            String gameid = (String) curentSocketData.getObjectInputStream().readObject();

                            gopp = gameController.getGame(gameid);
                            if (gopp == null) {
                                curentSocketData.getObjectOutputStream().writeObject(new ResponseMessage(400, "wrong id"));
                            } else {
                                gopp.setRoley(currSocketId);
                                curentSocketData.getObjectOutputStream().writeObject(new ResponseMessage(200, "joined"));
                            }
                        }

                        SocketData socketOpponetx = null;
                        while (socketOpponetx == null) {
                            if (socketController.findSocket(gopp.getRolex()) != null) {
                                socketOpponetx = socketController.findSocket(gopp.getRolex());
                                break;
                            }
                        }
                        Thread.sleep(200);
                        while (!gopp.isOver()) {
                            Thread.sleep(500);
                            if (gopp.winner() == null) {
                                if (gopp.getRole().equals("o")) {
                                    System.out.println("hallo");
                                    StepPosition step = (StepPosition) curentSocketData.getObjectInputStream().readObject();
                                    gopp.addPosition(step);
                                    System.out.println(step.getRole());
                                    System.out.println(step.getX());
                                    System.out.println(step.getY());
                                    socketOpponetx.getObjectOutputStream().writeObject(step);
                                }

                            }
                        }
                    }
                    default:
                        curentSocketData.getObjectOutputStream().writeObject("NOT_ALLOW");
                }
                curentSocketData.getSocket().close();
            }

        } catch (IOException exception) {
            System.out.println(exception);
        } catch (ClassNotFoundException exception) {
            System.out.println(exception);
        } catch (InterruptedException exception) {
            System.out.println(exception);
        }
    }

}
