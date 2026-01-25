package com.javafx.controller;

import com.javafx.database.ExamRepository;
import com.javafx.entity.Exam;
import com.javafx.entity.SubjectName;
import com.javafx.utils.DialogUtils;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class ExamController {

    @FXML private ComboBox<SubjectName> subject;
    @FXML private TextField locationField;
    @FXML private TextField duration;

    @FXML private TableView<Exam> tableExams;

    @FXML private TableColumn<Exam, String> colSubject;
    @FXML private TableColumn<Exam, Integer> colDuration;
    @FXML private TableColumn<Exam, String> colLocation;
    @FXML private TableColumn<Exam, String> colDateTime;

    private final ExamRepository repo = new  ExamRepository();

    @FXML
    private void initialize() {
        subject.setItems(FXCollections.observableArrayList(SubjectName.values()));

        colSubject.setCellValueFactory(param -> new ReadOnlyStringWrapper(
                param.getValue().getSubject().toString()
        ));
        colDuration.setCellValueFactory(param -> new SimpleObjectProperty<>(
                param.getValue().getDuration()
        ));
        colLocation.setCellValueFactory(param -> new ReadOnlyStringWrapper(
                param.getValue().getLocation()
        ));
        colDateTime.setCellValueFactory(param -> new ReadOnlyStringWrapper(
                param.getValue().getDateTime() == null ? "" : param.getValue().getDateTime().toString()
        ));

        refreshAll();
    }

    private void refreshAll() {
        try {
            tableExams.setItems(FXCollections.observableArrayList(repo.getAll()));
        } catch (Exception _) {
            DialogUtils.showDisplayScreenErrorDialog();
        }
    }

    @FXML
    private void onSearch() {
        SubjectName su = subject.getValue();
        String loc = locationField.getText().trim();

        Integer dur = null;
        if (!duration.getText().trim().isEmpty()) {
            try {
                dur = Integer.parseInt(duration.getText().trim());
            }
            catch (NumberFormatException _) {
                DialogUtils.showDisplayScreenInputErrorDialog("Trajanje mora biti cijeli broj.");
                return;
            }
        }

        if (su == null && loc.isEmpty() && dur == null) {
            DialogUtils.showDisplayScreenInputErrorDialog("Unesi barem jedan kriterij pretraživanja.");
            return;
        }

        try {
            var result = repo.search(su, loc, dur);
            tableExams.setItems(FXCollections.observableArrayList(result));
        } catch (Exception _) {
            DialogUtils.showDisplayScreenErrorDialog();
        }
    }

    @FXML
    private void onClear() {
        subject.getSelectionModel().clearSelection();
        locationField.clear();
        duration.clear();
        refreshAll();
    }
}