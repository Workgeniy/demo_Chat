package com.example.demo_chat;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.net.Socket;
import java.util.Random;

public class HelloApplication extends Application {
String name = "";


    @Override
    public void start(Stage stage) throws IOException {

        Client client = new Client();//, host, port);
        client.connect();

        Group rootName = new Group();
        Scene nameScene = new Scene(rootName);
        VBox vboxName = new VBox();
        Label nameLabel = new Label("Введите имя для чата");
        TextField nameField = new TextField();
        Button sendName = new Button("Confirm");
        Stage stageName = new Stage();
        stageName.setTitle("Name");
        stageName.setScene(nameScene);


        rootName.getChildren().add(vboxName);
        vboxName.getChildren().addAll(nameLabel, nameField, sendName);
        sendName.setOnAction(e -> {
            name = nameField.getText();
            try {
                client.say(name);
                stageName.close();
            } catch (IOException ex) {
                System.out.println("Не удалось отправить имя");
            }
        });

        Group root = new Group();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Chat");
        stage.show();

        HBox hBox = new HBox();
       VBox vbox = new VBox();
        root.getChildren().add(hBox);

        TextField textField = new TextField();
        vbox.getChildren().add(textField);

        Button sendButton = new Button("Send");
        vbox.getChildren().add(sendButton);

        VBox boxChat = new VBox();

        hBox.getChildren().add(vbox);
        hBox.getChildren().add(boxChat);

        sendButton.setOnAction(event -> {
            boxChat.getChildren().clear();
            String message = textField.getText();
            try {
                 client.say(message);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
                textField.clear();
        });

        stageName.show();

        new Thread(new Runnable() {
            @Override
            public void run() {
               while (true) {
                   Platform.runLater(() -> {
                       try {
                           System.out.println("Thread");
                           String message = client.listen();
                           Label label = new Label(message);
                           boxChat.getChildren().add(label);
                       } catch (IOException e) {
                           throw new RuntimeException(e);
                       }
                   });

               }
            }
        });

    }

    public static void main(String[] args) {
        launch();
    }
}