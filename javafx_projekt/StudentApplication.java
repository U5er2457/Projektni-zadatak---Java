package com.javafx.project.javafx_projekt;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class StudentApplication extends Application {

    private static Stage mainStage;

    @Override
    public void start(Stage stage) throws IOException {
        mainStage = stage;
        FXMLLoader loader = new FXMLLoader(StudentApplication.class.getResource("main-screen.fxml"));
        Scene scene = new Scene(loader.load(), 1024, 768);

        stage.setTitle("Student Application");
        stage.setScene(scene);
        stage.show();
    }

    public static Stage getMainStage(){
        return mainStage;
    }
}
