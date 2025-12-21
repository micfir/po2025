package org.example.samochodgui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.*;

public class DodajSamochodController {

    @FXML
    private TextField modelTextField;
    @FXML
    private TextField registrationTextField;
    @FXML
    private TextField speedTextField;
    @FXML
    private TextField weightTextField;

    @FXML
    private ComboBox<Silnik> engineComboBox;
    @FXML
    private ComboBox<SkrzyniaBiegow> gearboxComboBox;

    @FXML
    private Button confirmButton;
    @FXML
    private Button cancelButton;

    private HelloController mainController;

    @FXML
    public void initialize() {

        ObservableList<Silnik> dostepneSilniki = FXCollections.observableArrayList(
                new Silnik("BMW S58 3.0L I6", 195, 100000, "BMW", "S58", 7000),
                new Silnik("Honda K20C1 2.0L I4", 157, 40000, "Honda", "K20C1", 7500),
                new Silnik("Ford EcoBoost 1.0L I3", 97, 24000, "EcoBoost 1.0", "TDI", 6000),
                new Silnik("Toyota 1JZ-GTE 2.5L I6", 215, 16000, "Toyota", "1JZ-GTE", 7000)
        );

        ObservableList<SkrzyniaBiegow> dostepneSkrzynie = FXCollections.observableArrayList(
                new SkrzyniaBiegow("5-bieg Getrag 265", 60, 8000, "Getrag", "265", 5, new Sprzeglo("Sachs Performance Clutch Kit", 10, 2000, "Sachs", "Performance Clutch Kit", false)),
                new SkrzyniaBiegow("6-bieg Ford MT-82", 60, 8000, "Ford", "MT-82", 6, new Sprzeglo("Exedy Stage 1 Clutch Kit", 10, 2000, "Exedy", "Stage 1", false)),
                new SkrzyniaBiegow("6-bieg Tremec T-56", 80, 12000, "Tremec", "T-56", 6, new Sprzeglo("McLeod RXT 1200", 12, 8000, "McLeod Racing", "RXT 1200", false))
        );

        engineComboBox.setItems(dostepneSilniki);
        gearboxComboBox.setItems(dostepneSkrzynie);

        engineComboBox.getSelectionModel().selectFirst();
        gearboxComboBox.getSelectionModel().selectFirst();
    }

    public void setMainController(HelloController mainController) {
        this.mainController = mainController;
    }

    @FXML
    private void onConfirmButton() {
        String model = modelTextField.getText();
        String registration = registrationTextField.getText();

        if (model.isEmpty() || registration.isEmpty()) {
            System.out.println("Błąd: Model i rejestracja są wymagane.");
            return;
        }

        double weight;
        int speed;
        try {
            speed = Integer.parseInt(speedTextField.getText());
            String weightText = weightTextField.getText();
            if (weightText.isEmpty()) {
                weight = 0.0;
            } else {
                weight = Double.parseDouble(weightText);
            }
        } catch (NumberFormatException e) {
            System.out.println("Błąd: Prędkość musi być liczbą całkowitą.");
            return;
        }

        Silnik wzorzecSilnika = engineComboBox.getValue();
        SkrzyniaBiegow wzorzecSkrzyni = gearboxComboBox.getValue();

        if (wzorzecSilnika == null || wzorzecSkrzyni == null) {
            System.out.println("Błąd: Musisz wybrać silnik i skrzynię.");
            return;
        }
        Silnik nowySilnik = getNowySilnik(wzorzecSilnika);

        SkrzyniaBiegow nowaSkrzynia = getSkrzyniaBiegow(wzorzecSkrzyni);

        Pozycja domyslnaPozycja = new Pozycja(0, 0);

        Samochod nowySamochod = new Samochod(
                registration,
                model,
                speed,
                weight,
                nowySilnik,
                nowaSkrzynia,
                domyslnaPozycja);

        if (mainController != null) {
            mainController.dodajSamochodDoListy(nowySamochod);
        }

        Stage stage = (Stage) confirmButton.getScene().getWindow();
        stage.close();
    }

    private static Silnik getNowySilnik(Silnik wzorzec) {
        return new Silnik(
                wzorzec.getNazwa(),
                wzorzec.getWaga(),
                wzorzec.getCena(),
                wzorzec.getProducent(),
                wzorzec.getModel(),
                wzorzec.getMaxObroty()
        );
    }

    private static SkrzyniaBiegow getSkrzyniaBiegow(SkrzyniaBiegow wzorzecSkrzyni) {
        Sprzeglo wzorzecSprzegla = wzorzecSkrzyni.getSprzeglo();

        Sprzeglo noweSprzeglo = new Sprzeglo(
                wzorzecSprzegla.getNazwa(),
                wzorzecSprzegla.getWaga(),
                wzorzecSprzegla.getCena(),
                wzorzecSprzegla.getProducent(),
                wzorzecSprzegla.getModel(),
                false);

        SkrzyniaBiegow nowaSkrzynia = new SkrzyniaBiegow(
                wzorzecSkrzyni.getNazwa(),
                wzorzecSkrzyni.getWaga(),
                wzorzecSkrzyni.getCena(),
                wzorzecSkrzyni.getProducent(),
                wzorzecSkrzyni.getModel(),
                wzorzecSkrzyni.getIloscBiegow(),
                noweSprzeglo);
        return nowaSkrzynia;
    }

    @FXML
    private void onCancelButton() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}