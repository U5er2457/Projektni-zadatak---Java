package com.javafx.controller;

import com.javafx.entity.Exam;
import com.javafx.entity.Subject;
import com.javafx.input.JSONStorage;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import com.javafx.utils.DialogUtils;

import java.util.List;

public class ExamController {

    @FXML private ComboBox<Subject> subject;
    @FXML private TextField location;
    @FXML private TextField duration;
    @FXML private TableView<Exam> tableExams;

    private List<Exam> allExams;

    @FXML
    private void initialize() {
        allExams = JSONStorage.loadExams();
        subject.setItems(javafx.collections.FXCollections.observableArrayList(JSONStorage.loadSubjects()));
    }

    @FXML
    private void onSearch() {
        Subject su = subject.getValue();
        String l = location.getText().trim();
        String dur = duration.getText().trim();

        if (su == null && l.isEmpty() && dur.isEmpty()) {
            DialogUtils.showDisplayScreenInputErrorDialog("Unesi barem jedan kriterij pretraživanja.");
            return;
        }

        var result = allExams.stream()
                .filter(e -> su == null)
                .filter(e -> l.isEmpty() || e.getLocation().equals(l))
                .filter(e -> dur.isEmpty())
                .toList();

        tableExams.setItems(javafx.collections.FXCollections.observableArrayList(result));
    }

    @FXML
    private void onClear() {
        subject.getSelectionModel().clearSelection();
        location.clear();
        duration.clear();
        tableExams.getItems().clear();
    }

}
