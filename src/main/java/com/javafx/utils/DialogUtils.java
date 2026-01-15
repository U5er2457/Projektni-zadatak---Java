package com.javafx.utils;

import javafx.scene.control.Alert;

public class DialogUtils {

    private DialogUtils(){}

    public static void showDisplayScreenErrorDialog(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Pogreška prilikom prikaza ekrana");
        alert.setHeaderText("Odabrani ekran nije moguće prikazati");
        alert.setContentText("Dogodila se pogreška prilikom inicijalizacije ekrana!");
        alert.showAndWait();
    }

    public static void showDisplayScreenInputErrorDialog(String msg) {
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setTitle("Pogreška prilikom unosa");
        a.setHeaderText("Neispravan unos");
        a.setContentText(msg);
        a.showAndWait();
    }

}
