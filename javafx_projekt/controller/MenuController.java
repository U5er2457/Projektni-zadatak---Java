package com.javafx.project.javafx_projekt.controller;

import com.javafx.project.javafx_projekt.StudentApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import com.javafx.project.javafx_projekt.utils.DialogUtils;

import java.io.IOException;

public class MenuController {

    @FXML
    private StackPane contentPane;

    @FXML
    public void initialize() {
        loadIntoCenter("student-screen.fxml");
    }

    @FXML
    public void showStudentScreen() {
        loadIntoCenter("student-screen.fxml");
        StudentApplication.getMainStage().setTitle("Student Screen");
    }

    @FXML
    public void showProfessorScreen() {
        loadIntoCenter("professor-screen.fxml");
        StudentApplication.getMainStage().setTitle("Professor Screen");
    }

    @FXML
    public void showSubjectScreen() {
        loadIntoCenter("subject-screen.fxml");
        StudentApplication.getMainStage().setTitle("Subject Screen");
    }

    @FXML
    public void showExamScreen() {
        loadIntoCenter("exam-screen.fxml");
        StudentApplication.getMainStage().setTitle("Exam Screen");
    }

    private void loadIntoCenter(String fxml) {
        try {
            FXMLLoader loader = new FXMLLoader(StudentApplication.class.getResource(fxml));
            Parent view = loader.load();
            contentPane.getChildren().setAll(view);
        } catch (IOException e) {
            DialogUtils.showDisplayScreenErrorDialog();
        }
    }
}

