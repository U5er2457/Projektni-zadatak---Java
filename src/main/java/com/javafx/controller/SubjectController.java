package com.javafx.controller;

import com.javafx.entity.Subject;
import com.javafx.input.JSONStorage;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import com.javafx.utils.DialogUtils;

import java.util.List;

public class SubjectController {

    @FXML private TextField name;
    @FXML private TextField ects;
    @FXML private TextField year;
    @FXML private TextField duration;
    @FXML private ComboBox<String> location;
    @FXML private TableView<Subject> tableSubjects;

    private List<Subject> allSubjects;

    @FXML
    private void initialize() {
        allSubjects = JSONStorage.loadSubjects();
        location.setItems(javafx.collections.FXCollections.observableArrayList("Borongaj", "Vrbik"));
    }

    @FXML
    private void onSearch() {
        String n = name.getText().trim();

        String l = location.getValue();

        if (n.isEmpty() && ects == null && l.isEmpty()) {
            DialogUtils.showDisplayScreenInputErrorDialog("Unesi barem jedan kriterij pretraživanja.");
            return;
        }

        var result = allSubjects.stream()
                .filter(s -> n.isEmpty() || s.name().toString().toLowerCase().contains(n.toLowerCase()))
                .filter(s -> ects == null)
                .filter(s -> l == null || s.location().equals(l))
                .toList();

        tableSubjects.setItems(javafx.collections.FXCollections.observableArrayList(result));
    }

    @FXML
    private void onClear() {
        name.clear();
        ects.clear();
        location.getSelectionModel().clearSelection();
        tableSubjects.getItems().clear();
    }


}
