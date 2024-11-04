package com.example.demo_chat;

import com.almasb.fxgl.net.Server;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import static org.junit.jupiter.api.Assertions.*;

class ClientTest {
    private static ServerSocket server;

    @Test
    void connect() {
    }


    @Test
    void say() {
    }

    @Test
    void listen() {
    }

    @BeforeAll
    static void BeforeClass() throws Exception {
        server = new ServerSocket(5000);
        System.out.println("server start");
    }

    @AfterAll
    static void AfterClass() throws Exception {
        server.close();
        System.out.println("server closed");
    }

    @Test
    void testNewClient() {

        Socket client = null;

        try {
            Socket server = new Socket("localhost", 5000);

            client = server.accept();

            ObjectOutputStream out = new ObjectOutputStream(server.getOutputStream());
        }
        catch (IOException e) {

            e.printStackTrace();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}