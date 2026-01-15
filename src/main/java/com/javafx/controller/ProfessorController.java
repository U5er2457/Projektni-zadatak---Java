package com.javafx.controller;

import com.javafx.entity.Professor;
import com.javafx.input.JSONStorage;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import com.javafx.utils.DialogUtils;

import java.util.List;

public class ProfessorController{

    @FXML private TextField firstName;
    @FXML private TextField lastName;
    @FXML private TextField email;
    @FXML private TextField oib;
    @FXML private TableView<Professor> tableProfessors;

    private List<Professor> allProfessors;

    @FXML
    private void initialize() {
        allProfessors = JSONStorage.loadProfessors();
    }

    @FXML
    private void onSearch() {
        String fn = firstName.getText().trim();
        String ln = lastName.getText().trim();
        String e = email.getText().trim();
        String o = oib.getText().trim();

        if (fn.isEmpty() && ln.isEmpty() && e.isEmpty() && o.isEmpty()) {
            DialogUtils.showDisplayScreenInputErrorDialog("Unesi barem jedan kriterij pretraživanja.");
            return;
        }

        var result = allProfessors.stream()
                .filter(p -> fn.isEmpty() || p.getFirstName().toLowerCase().contains(fn.toLowerCase()))
                .filter(p -> ln.isEmpty() || p.getLastName().toLowerCase().contains(ln.toLowerCase()))
                .filter(p -> e.isEmpty() || p.getEmail().toLowerCase().contains(e))
                .filter(p -> o.isEmpty() || p.getOib().contains(o))
                .toList();

        tableProfessors.setItems(javafx.collections.FXCollections.observableArrayList(result));
    }

    @FXML
    private void onClear() {
        firstName.clear();
        lastName.clear();
        email.clear();
        oib.clear();
        tableProfessors.getItems().clear();
    }

}
