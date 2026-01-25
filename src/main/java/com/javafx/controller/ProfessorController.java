package com.javafx.controller;

import com.javafx.database.ProfessorRepository;
import com.javafx.entity.Professor;
import com.javafx.utils.DialogUtils;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class ProfessorController {

    @FXML private TextField firstName;
    @FXML private TextField lastName;
    @FXML private TextField email;
    @FXML private TextField oib;

    @FXML private TableView<Professor> tableProfessors;

    @FXML private TableColumn<Professor, String> colFirstName;
    @FXML private TableColumn<Professor, String> colLastName;
    @FXML private TableColumn<Professor, String> colEmail;
    @FXML private TableColumn<Professor, String> colOib;

    private final ProfessorRepository repo = new ProfessorRepository();

    @FXML
    private void initialize() {
        colFirstName.setCellValueFactory(param -> new ReadOnlyStringWrapper(
                param.getValue().getFirstName()
        ));
        colLastName .setCellValueFactory(param -> new ReadOnlyStringWrapper(
                param.getValue().getLastName()
        ));
        colEmail    .setCellValueFactory(param -> new ReadOnlyStringWrapper(
                param.getValue().getEmail()
        ));
        colOib      .setCellValueFactory(param -> new ReadOnlyStringWrapper(
                param.getValue().getOib()
        ));

        refreshAll();
    }

    private void refreshAll() {
        try {
            tableProfessors.setItems(FXCollections.observableArrayList(repo.getAll()));
        } catch (Exception _) {
            DialogUtils.showDisplayScreenErrorDialog();
        }
    }

    @FXML
    private void onSearch() {
        String fn = firstName.getText().trim();
        String ln = lastName.getText().trim();
        String em = email.getText().trim();
        String ob = oib.getText().trim();

        if (fn.isEmpty() && ln.isEmpty() && em.isEmpty() && ob.isEmpty()) {
            DialogUtils.showDisplayScreenInputErrorDialog("Unesi barem jedan kriterij pretraživanja.");
            return;
        }

        try {
            var result = repo.search(fn, ln, em, ob);
            tableProfessors.setItems(FXCollections.observableArrayList(result));
        } catch (Exception _) {
            DialogUtils.showDisplayScreenErrorDialog();
        }
    }

    @FXML
    private void onClear() {
        firstName.clear();
        lastName.clear();
        email.clear();
        oib.clear();
        refreshAll();
    }
}