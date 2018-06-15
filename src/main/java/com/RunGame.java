package com;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class RunGame extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent parent = FXMLLoader.load(getClass().getResource("/fromFXML/Form.fxml"));
        stage.setScene(new Scene(parent,800,600));
        stage.setTitle("2048");
        stage.getScene().getStylesheets().add("css/css.css");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
