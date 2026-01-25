package com.javafx;

import com.javafx.controller.MenuController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class StudentApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(StudentApplication.class.getResource("main-screen.fxml"));
        Scene scene = new Scene(loader.load(), 1024, 768);

        MenuController controller = loader.getController();

        controller.setStage(stage);

        stage.setTitle("Student Application");
        stage.setScene(scene);
        stage.show();
    }
}
