package com.example.demo_chat;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    private Client client;

    @Override
    public void start(Stage stage) throws IOException {

        client = new Client();
        client.connect();
        Group root = new Group();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Chat");
        stage.show();

       VBox vbox = new VBox();
        root.getChildren().add(vbox);

        TextField textField = new TextField();
        vbox.getChildren().add(textField);

        Button sendButton = new Button("Send");
        vbox.getChildren().add(sendButton);

        VBox boxChat = new VBox();
        vbox.getChildren().add(boxChat);

        sendButton.setOnAction(event -> {
            boxChat.getChildren().clear();
            String message = textField.getText();
            try {
                client.say(message);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
           // textField.clear();
            String history = "";
            try {
                history = client.listen();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            String[] list = history.split("@");
            for (int i = 0; i < list.length; i++) {
                Label label = new Label(list[i]);
                boxChat.getChildren().add(label);
                System.out.println(list[i]);
            }
        });

//        while (true){
//            client.listen();
//        }


//        String startHistory = "";
//        try {
//            startHistory = client.listen();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        String[] list = startHistory.split("@");
//        for (int i = 0; i < list.length; i++) {
//            Label labelStary = new Label(list[i]);
//            boxChat.getChildren().add(labelStary);
//            System.out.println(list[i]);
//        }
    }

    public static void main(String[] args) {
        launch();
    }
}