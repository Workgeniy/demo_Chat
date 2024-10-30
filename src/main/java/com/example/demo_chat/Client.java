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
            Random number = new Random();
            oos = new ObjectOutputStream(
                    socket.getOutputStream());
            oos.writeObject("Client " + number.nextInt(100));
            System.out.println();
        }
        catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    void bye() {
        try {
            socket.close();
        }
        catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    void say(String msg) throws IOException {
        oos = new ObjectOutputStream(
                socket.getOutputStream());
        oos.writeObject(msg);
        System.out.println(msg);
        //oos.close();  // почему нельзя закрывать?
    }

    String listen() throws IOException {
        String result = "";
        ois = new ObjectInputStream(
                socket.getInputStream());
        try {
            result = (String) ois.readObject ();
        } catch (ClassNotFoundException cnf) {
            cnf.getMessage();
        }
        System.out.println(result);
       // ois.close();  // почему нельзя закрывать?
        return result;
    }



}
