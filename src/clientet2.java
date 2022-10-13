/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */


import gamemechanic.StepPosition;
import message.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author giang
 */
public class clientet2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println("clienttest");
        try {
            Socket sk = new Socket("localhost", 3000);
            ObjectOutputStream ojOutputStream = new ObjectOutputStream(sk.getOutputStream());
            ObjectInputStream ojInputStream = new ObjectInputStream(sk.getInputStream());
            LoginMessageRequest lr = new LoginMessageRequest("nam123", "123456");
            ojOutputStream.writeObject(lr);
            LoginMessageResponse lmresponse = (LoginMessageResponse) ojInputStream.readObject();
            System.out.println(lmresponse.getStatus());
            System.out.println(lmresponse.getMessage());
            if(lmresponse.getStatus() == 200) {
               ojOutputStream.writeObject(new String("join"));

                while(true) {
                ojOutputStream.writeObject(new String("9f253884-c757-4a9f-b0be-e0edeffc5d81"));
                ResponseMessage responseMessage = (ResponseMessage) ojInputStream.readObject();
                System.out.println(responseMessage.getStatus());
                System.out.println(responseMessage.getMessage());
                if(responseMessage.getStatus() == 200) {break;}
               }


               while(true) {

                     StepPosition step = (StepPosition) ojInputStream.readObject();
                      System.out.println(step.getRole() +": " + String.valueOf(step.getX()) + " " + String.valueOf(step.getY()));
                     System.out.println(step.getRole());

                    StepPosition st = new StepPosition("o", 0, 0);
                    ojOutputStream.writeObject(st);
                    System.out.println("run1");


               }
            }

        } catch(IOException exception) {
            System.out.println(exception);
        }catch(ClassNotFoundException exception) {
            System.out.println(exception);
        }
    }

}
