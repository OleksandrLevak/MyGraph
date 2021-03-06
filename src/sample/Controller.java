package sample;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button lab1_3;

    @FXML
    private Button lab4;

    @FXML
    private Button lab5;

    @FXML
    private Button lab6;

    @FXML
    void initialize() {
        lab1_3.setOnMouseClicked((event) -> {
            lab1_3.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("graph.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setTitle("MyGraph");
            stage.setScene(new Scene(root));
            stage.showAndWait();
        });

        lab4.setOnMouseClicked((event) -> {
            lab4.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("graph_two.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setTitle("MyGraph");
            stage.setScene(new Scene(root));
            stage.showAndWait();
        });

        lab5.setOnMouseClicked((event) -> {
            lab5.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("graph_three.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setTitle("MyGraph");
            stage.setScene(new Scene(root));
            stage.showAndWait();
        });

        lab6.setOnMouseClicked((event) -> {
            lab6.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("graph_four.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setTitle("MyGraph");
            stage.setScene(new Scene(root));
            stage.showAndWait();
        });
    }
}

