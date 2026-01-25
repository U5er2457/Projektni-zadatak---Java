package com.javafx.controller;

import com.javafx.database.SubjectRepository;
import com.javafx.entity.Subject;
import com.javafx.entity.SubjectName;
import com.javafx.utils.DialogUtils;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class SubjectController {

    @FXML private ComboBox<SubjectName> name;
    @FXML private TextField ects;
    @FXML private TextField locationField;
    @FXML private TextField duration;

    @FXML private TableView<Subject> tableSubjects;

    @FXML private TableColumn<Subject, String> colName;
    @FXML private TableColumn<Subject, Integer> colEcts;
    @FXML private TableColumn<Subject, String> colLocation;
    @FXML private TableColumn<Subject, Integer> colDuration;
    @FXML private TableColumn<Subject, String> colDateTime;

    private final SubjectRepository repo = new SubjectRepository();

    @FXML
    private void initialize() {
        name.setItems(FXCollections.observableArrayList(SubjectName.values()));

        colName.setCellValueFactory(param -> new ReadOnlyStringWrapper(
                String.valueOf(param.getValue().name())
        ));
        colEcts.setCellValueFactory(param -> new SimpleObjectProperty<>(
                param.getValue().ects()
        ));
        colLocation.setCellValueFactory(param -> new ReadOnlyStringWrapper(
                param.getValue().location()
        ));
        colDuration.setCellValueFactory(param -> new SimpleObjectProperty<>(
                param.getValue().duration()
        ));
        colDateTime.setCellValueFactory(param -> new ReadOnlyStringWrapper(
                param.getValue().dateTime() == null ? "" : param.getValue().dateTime().toString()
        ));

        refreshAll();
    }

    private void refreshAll() {
        try {
            tableSubjects.setItems(FXCollections.observableArrayList(repo.getAll()));
        } catch (Exception _) {
            DialogUtils.showDisplayScreenErrorDialog();
        }
    }

    @FXML
    private void onSearch() {
        SubjectName n = name.getValue();

        Integer e = null;
        if (!ects.getText().trim().isEmpty()) {
            try { e = Integer.parseInt(ects.getText().trim()); }
            catch (NumberFormatException _) {
                DialogUtils.showDisplayScreenInputErrorDialog("ECTS mora biti cijeli broj.");
                return;
            }
        }

        Integer d = null;
        if (!duration.getText().trim().isEmpty()) {
            try { d = Integer.parseInt(duration.getText().trim()); }
            catch (NumberFormatException _) {
                DialogUtils.showDisplayScreenInputErrorDialog("Trajanje mora biti cijeli broj.");
                return;
            }
        }

        String loc = locationField.getText().trim();

        if (n == null && e == null && d == null && loc.isEmpty()) {
            DialogUtils.showDisplayScreenInputErrorDialog("Unesi barem jedan kriterij pretraživanja.");
            return;
        }

        try {
            var result = repo.search(n, e, loc, d);
            tableSubjects.setItems(FXCollections.observableArrayList(result));
        } catch (Exception _) {
            DialogUtils.showDisplayScreenErrorDialog();
        }
    }

    @FXML
    private void onClear() {
        name.getSelectionModel().clearSelection();
        ects.clear();
        locationField.clear();
        duration.clear();
        refreshAll();
    }
}