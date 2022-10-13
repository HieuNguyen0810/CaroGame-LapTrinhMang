/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */


import gamemechanic.StepPosition;
import message.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 *
 * @author giang
 */
public class Caroclienttest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println("clienttest");
        try {
            Socket sk = new Socket("localhost", 3000);
            OutputStream os = sk.getOutputStream();
            ObjectOutputStream ojOutputStream = new ObjectOutputStream(os);
            ObjectInputStream ojInputStream = new ObjectInputStream(sk.getInputStream());
//            ObjectInputStream ojInputStream1 = new ObjectInputStream(sk.getInputStream());
            LoginMessageRequest lr = new LoginMessageRequest("nam", "123456");
            ojOutputStream.writeObject(lr);
            LoginMessageResponse lmresponse = (LoginMessageResponse) ojInputStream.readObject();
            System.out.println(lmresponse.getStatus());
            System.out.println(lmresponse.getMessage());
            if(lmresponse.getStatus() == 200) {
               ojOutputStream.writeObject(new String("create"));
               ResponseMessage responseMessage = (ResponseMessage) ojInputStream.readObject();
               System.out.println(responseMessage.getStatus());
               System.out.println(responseMessage.getMessage());
               String id = (String) ojInputStream.readObject();;
               System.out.println(id);
               while(true) {
                    StepPosition st = new StepPosition("x", 0, 0);
                    ojOutputStream.writeObject(st);
                    StepPosition step = (StepPosition) ojInputStream.readObject();
                    System.out.println(step.getRole() +": " + String.valueOf(step.getX()) + " " + String.valueOf(step.getY()));
                    
                    
                }
            }
            
        } catch(IOException exception) {
            System.out.println(exception);
        }catch(ClassNotFoundException exception) {
            System.out.println(exception);
        } 
    }
    
}
