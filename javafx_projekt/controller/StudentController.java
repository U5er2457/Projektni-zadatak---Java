package com.javafx.project.javafx_projekt.controller;

import com.javafx.project.javafx_projekt.entity.Student;
import com.javafx.project.javafx_projekt.input.JSONStorage;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import com.javafx.project.javafx_projekt.utils.DialogUtils;

import java.util.List;

public class StudentController {

    @FXML private TextField firstName, lastName, jmbag, year;
    @FXML private ComboBox<String> course;
    @FXML private TableView<Student> tableStudents;

    private List<Student> allStudents;

    @FXML
    private void initialize() {
        allStudents = JSONStorage.loadStudents();
    }

    @FXML
    private void onSearch() {
        String fn = firstName.getText().trim();
        String ln = lastName.getText().trim();
        String jmbg = jmbag.getText().trim();
        String crs = course.getValue();

        Integer y = null;
        if (!year.getText().trim().isEmpty()) {
            try { y = Integer.parseInt(year.getText().trim()); }
            catch (NumberFormatException e) {
                DialogUtils.showDisplayScreenInputErrorDialog("Godina mora biti cijeli broj.");
                return;
            }
        }

        if (fn.isEmpty() && ln.isEmpty() && jmbg.isEmpty() && crs == null && y == null) {
            DialogUtils.showDisplayScreenInputErrorDialog("Unesite barem jedan kriterij pretraživanja.");
            return;
        }

        Integer finalYear = y;
        var result = allStudents.stream()
                .filter(s -> fn.isEmpty() || s.getFirstName().toLowerCase().contains(fn.toLowerCase()))
                .filter(s -> ln.isEmpty() || s.getLastName().toLowerCase().contains(ln.toLowerCase()))
                .filter(s -> jmbg.isEmpty() || s.getJmbag().contains(jmbg))
                .filter(s -> crs == null || s.getCourse().equals(crs))
                .filter(s -> finalYear == null || s.getYear() == finalYear)
                .toList();

        tableStudents.setItems(javafx.collections.FXCollections.observableArrayList(result));
    }

    @FXML
    private void onClear() {
        firstName.clear();
        lastName.clear();
        jmbag.clear();
        year.clear();
        course.getSelectionModel().clearSelection();
        tableStudents.getItems().clear();
    }


}
