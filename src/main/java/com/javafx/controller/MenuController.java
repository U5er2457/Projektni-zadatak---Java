package com.javafx.controller;

import com.javafx.StudentApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import com.javafx.utils.DialogUtils;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController {

    private Stage stage;

    public void setStage(Stage stage){
        this.stage = stage;
    }

    @FXML
    private StackPane contentPane;

    @FXML
    public void initialize() {
        loadIntoCenter("student-screen.fxml");
    }

    @FXML
    public void showStudentScreen() {
        loadIntoCenter("student-screen.fxml");
        stage.setTitle("Student Screen");
    }

    @FXML
    public void showProfessorScreen() {
        loadIntoCenter("professor-screen.fxml");
        stage.setTitle("Professor Screen");
    }

    @FXML
    public void showSubjectScreen() {
        loadIntoCenter("subject-screen.fxml");
        stage.setTitle("Subject Screen");
    }

    @FXML
    public void showExamScreen() {
        loadIntoCenter("exam-screen.fxml");
        stage.setTitle("Exam Screen");
    }

    private void loadIntoCenter(String fxml) {
        try {
            FXMLLoader loader = new FXMLLoader(StudentApplication.class.getResource(fxml));
            Parent view = loader.load();
            contentPane.getChildren().setAll(view);
        } catch (IOException _) {
            DialogUtils.showDisplayScreenErrorDialog();
        }
    }
}

