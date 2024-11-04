package com.example.demo_chat;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Random;

public class Client {
    private String host;
    private int port;
    private Socket socket;
    ObjectOutputStream oos = null;
    ObjectInputStream ois = null;

    public Client() {
        host = "localhost";
        port = 5000;
    }

    void connect() {
        try {
            socket = new Socket(host, port);
        }catch(IOException e){
            System.out.println("Vechno ne dozvonishsia");
        }
    }

    void bye() {
        try {
            socket.close();
        }catch(IOException e){
            System.out.println("Vot i pogovorili...");
        }
    }

    void say(String msg) throws IOException {
         oos = new ObjectOutputStream(
                socket.getOutputStream());
        oos.writeObject(msg);
        System.out.println(msg);
    }

    String listen() throws IOException {
        String result = null;
         ois = new ObjectInputStream(
                socket.getInputStream());
        try {
            result = (String) ois.readObject ();
        } catch (ClassNotFoundException onf) {
            System.out.printf("Cannot read ");
        }
        return result;
    }

}


