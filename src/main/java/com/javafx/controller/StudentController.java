package com.javafx.controller;

import com.javafx.database.StudentRepository;
import com.javafx.entity.Course;
import com.javafx.entity.Student;
import com.javafx.utils.DialogUtils;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

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
    @FXML private Label lastInsertedLabel;

    @FXML private TableColumn<Student, String> colFirstName;
    @FXML private TableColumn<Student, String> colLastName;
    @FXML private TableColumn<Student, String> colEmail;
    @FXML private TableColumn<Student, String> colJmbag;
    @FXML private TableColumn<Student, String> colCourse;
    @FXML private TableColumn<Student, Integer> colYear;
    @FXML private TableColumn<Student, Integer> colEcts;

    private final StudentRepository repo = new StudentRepository();
    private List<Student> allStudents;

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
        colJmbag    .setCellValueFactory(param -> new ReadOnlyStringWrapper(
                param.getValue().getJmbag()
        ));
        colCourse   .setCellValueFactory(param -> new ReadOnlyStringWrapper(
                String.valueOf(param.getValue().getCourse())
        ));
        colYear     .setCellValueFactory(param -> new SimpleObjectProperty<>(
                param.getValue().getYear()
        ));
        colEcts     .setCellValueFactory(param -> new SimpleObjectProperty<>(
                param.getValue().getEcts()
        ));

        course.setItems(FXCollections.observableArrayList(Course.values()));
       refreshAll();

       Thread.startVirtualThread(() -> {
           try{
               allStudents = repo.getAll();
               Platform.runLater(() ->
                   tableStudents.setItems(FXCollections.observableArrayList(allStudents))
               );
           }
           catch (Exception _){
               Platform.runLater(DialogUtils::showDisplayScreenErrorDialog);
           }
       });

       Thread.startVirtualThread(() -> {
           try {
               var last = repo.getLastInserted();
               Platform.runLater(() -> {
                   String text = last
                           .map(s -> s.getFirstName() + " " + s.getLastName() + " | JMBAG: " + s.getJmbag())
                           .orElse("Nema studenata u bazi podataka.");
                   lastInsertedLabel.setText("Zadnje uneseni student: " + text);
               });
           }
           catch (Exception _){
               Platform.runLater(() -> lastInsertedLabel.setText("Zadnje uneseni student: GREŠKA pri dohvaćanju"));
           }
       });
    }

    private void refreshAll() {
        try {
            tableStudents.setItems(FXCollections.observableArrayList(repo.getAll()));
        } catch (Exception _) {
            DialogUtils.showDisplayScreenErrorDialog();
        }
    }

    @FXML
    private void onSearch() {
        String fn = firstName.getText().trim();
        String ln = lastName.getText().trim();
        String jm = jmbag.getText().trim();
        String em = email.getText().trim();
        Course crs = course.getValue();

        Integer y = null;
        if (!year.getText().trim().isEmpty()) {
            try { y = Integer.parseInt(year.getText().trim()); }
            catch (NumberFormatException _) {
                DialogUtils.showDisplayScreenInputErrorDialog("Godina mora biti cijeli broj.");
                return;
            }
        }

        Integer ectsMin = null;
        if (!ects.getText().trim().isEmpty()) {
            try { ectsMin = Integer.parseInt(ects.getText().trim()); }
            catch (NumberFormatException _) {
                DialogUtils.showDisplayScreenInputErrorDialog("ECTS mora biti cijeli broj.");
                return;
            }
        }

        if (fn.isEmpty() && ln.isEmpty() && jm.isEmpty() && em.isEmpty() && crs == null && y == null && ectsMin == null) {
            DialogUtils.showDisplayScreenInputErrorDialog("Unesite barem jedan kriterij pretraživanja.");
            return;
        }

        try {
            var result = repo.search(fn, ln, jm, em, crs, y, ectsMin);
            tableStudents.setItems(FXCollections.observableArrayList(result));
        } catch (Exception _) {
            DialogUtils.showDisplayScreenErrorDialog();
        }
    }

    @FXML
    private void onClear() {
        firstName.clear();
        lastName.clear();
        jmbag.clear();
        email.clear();
        year.clear();
        ects.clear();
        course.getSelectionModel().clearSelection();
        tableStudents.getItems().clear();
        refreshAll();
    }

    @FXML
    private void onBackupStudents() {
        Thread.startVirtualThread(() -> {
            try {
                repo.backupStudentsTable();
                Platform.runLater(() ->
                    DialogUtils.showDisplayScreenInputInfoDialog(
                            "Backup",
                            "Backup uspješan",
                            "Kreirana je tablica STUDENT_BACKUP te su kopirani svi podaci iz tablice STUDENT."
                    )
                );
            }
            catch (Exception ex){
                Platform.runLater(() ->
                        DialogUtils.showDisplayScreenInputErrorDialog("Backup nije uspio: " + ex.getMessage())
                        );
            }
        });
    }
}