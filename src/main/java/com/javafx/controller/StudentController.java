package com.javafx.controller;

import com.javafx.entity.Course;
import com.javafx.entity.Student;
import com.javafx.input.JSONStorage;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import com.javafx.utils.DialogUtils;

import java.util.List;

public class StudentController {

    @FXML private TextField firstName;
    @FXML private TextField lastName;
    @FXML private TextField jmbag;
    @FXML private TextField email;
    @FXML private TextField year;
    @FXML private ComboBox<Course> course;
    @FXML private TextField ects;
    @FXML private TableView<Student> tableStudents;

    private List<Student> allStudents;

    @FXML
    private void initialize() {
        allStudents = JSONStorage.loadStudents();
        course.setItems(javafx.collections.FXCollections.observableArrayList(Course.values()));
    }

    @FXML
    private void onSearch() {
        String fn = firstName.getText().trim();
        String ln = lastName.getText().trim();
        String jmbg = jmbag.getText().trim();
        Course crs = course.getValue();

        Integer y = null;
        if (!year.getText().trim().isEmpty()) {
            try { y = Integer.parseInt(year.getText().trim()); }
            catch (NumberFormatException _) {
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
                .filter(s -> crs == null)
                .filter(s -> finalYear == null || s.getYear().equals(finalYear))
                .toList();

        tableStudents.setItems(javafx.collections.FXCollections.observableArrayList(result));
    }

    @FXML
    private void onClear() {
        firstName.clear();
        lastName.clear();
        jmbag.clear();
        year.clear();
        email.clear();
        ects.clear();
        course.getSelectionModel().clearSelection();
        tableStudents.getItems().clear();
    }


}
