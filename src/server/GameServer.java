package server;

import message.LoginMessageRequest;
import message.LoginMessageResponse;
import gamemechanic.StepPosition;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;

public class GameServer {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ServerSocket server = new ServerSocket(2207);
        System.out.println("Server is running...");

        Socket connection = server.accept();
        ObjectInputStream ois = new ObjectInputStream(connection.getInputStream());
        ObjectOutputStream oos = new ObjectOutputStream(connection.getOutputStream());
        Scanner sc = new Scanner(System.in);
        int count = 0;


        while (true) {


            System.out.println(++count);
            System.out.println(connection.getInetAddress());
//            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//
//            //Tạo outputStream, nối tới socket
//            DataOutputStream dos =
//                    new DataOutputStream(connection.getOutputStream());


//            Object obj = ois.readObject();
//            if (obj instanceof LoginMessageRequest) {
//                LoginMessageRequest loginMessageRequest = (LoginMessageRequest) ois.readObject();
//                if (loginMessageRequest.getUsername().equals("client1") && loginMessageRequest.getPassword().equals("0000")) {
//                    LoginMessageResponse loginResponse = new LoginMessageResponse(200, "OK");
//                    oos.writeObject(loginResponse);
//                    oos.flush();
//                } else {
//                    LoginMessageResponse loginResponse = new LoginMessageResponse(400, "Bad request");
//                    oos.writeObject(loginResponse);
//                    oos.flush();
//                }
//            } else if (obj instanceof StepPosition) {
//                System.out.println(((StepPosition) obj).getX() + "--" + ((StepPosition) obj).getRole());
//            }

            if (ois.readObject() != null) {

                Random random = new Random();
                StepPosition stepPosition = new StepPosition("o", random.nextInt(20), random.nextInt(20));
                oos.writeObject(stepPosition);
            }

            //Tạo input stream, nối tới Socket


            //Đọc thông tin từ socket
//            String sentence_from_client = br.readLine();
////            br.close();
//            System.out.println(sentence_from_client);

//            String sentence_to_client = sc.nextLine();
//            //ghi dữ liệu ra socket
//            dos.writeBytes(sentence_to_client);
////            br.close();
//            dos.flush();
//            dos.close();
//            connection.close();
        }
    }
}
