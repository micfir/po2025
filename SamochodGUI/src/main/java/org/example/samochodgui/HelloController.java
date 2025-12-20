package org.example.samochodgui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.StringConverter;

import org.example.*;

public class HelloController implements Listener{

    private Samochod aktualnySamochod;

    // Samochód
    @FXML private TextField modelTextField;
    @FXML private TextField registrationNumberTextField;
    @FXML private TextField weightTextField;
    @FXML private TextField speedTextField;

    // Skrzynia Biegów
    @FXML private TextField gearboxNameTextField;
    @FXML private TextField gearboxPriceTextField;
    @FXML private TextField gearboxWeightTextField;
    @FXML private TextField gearTextField;

    // Silnik
    @FXML private TextField engineNameTextField;
    @FXML private TextField enginePriceTextField;
    @FXML private TextField engineWeightTextField;
    @FXML private TextField rpmTextField;

    // Sprzęgło
    @FXML private TextField clutchNameTextField;
    @FXML private TextField clutchPriceTextField;
    @FXML private TextField clutchWeightTextField;
    @FXML private TextField clutchStateTextField;

    @FXML private ImageView carImageView;
    @FXML private ComboBox<Samochod> carComboBox;

    private ObservableList<Samochod> listaSamochodow = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        System.out.println("HelloController initialized");

        carComboBox.setItems(listaSamochodow);
        carComboBox.setConverter(new StringConverter<Samochod>() {
            @Override
            public String toString(Samochod car) {
                if (car == null) return "";
                return car.getNrRejest();
            }

            @Override
            public Samochod fromString(String string) {
                return null;
            }
        });

        carComboBox.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
            if (oldValue != null) {
                oldValue.removeListener(this);
            }
            if (newValue != null) {
                aktualnySamochod = newValue;
                aktualnySamochod.addListener(this);
                refresh();
                System.out.println("Wybrano samochód: " + newValue.getNrRejest());
            }
        });
        try {
            Image carImage = new Image(getClass().getResource("/images/car.png").toExternalForm());

            carImageView.setImage(carImage);
            carImageView.setFitWidth(120);
            carImageView.setFitHeight(80);
            carImageView.setTranslateX(0);
            carImageView.setTranslateY(0);
        } catch (Exception e) {
            System.err.println("Błąd obrazka: " + e.getMessage());
        }
        stworzSamochodTestowy();
        refresh();
    }
    private void stworzSamochodTestowy() {
        Silnik silnikBMW = new Silnik("BMW S58 3.0L I6", 195, 100000, "BMW", "S58", 7000);
        Sprzeglo sprzegloFord = new Sprzeglo("Exedy Stage 1 Clutch Kit", 10, 2000, "Exedy", "Stage 1", false);
        SkrzyniaBiegow skrzyniaFord = new SkrzyniaBiegow("6-bieg Ford MT-82", 60, 8000, "Ford", "MT-82", 6, sprzegloFord);
        Pozycja pozycjaStartowa = new Pozycja(0, 0);
        Samochod autoTestowe = new Samochod("TEST BMW","BMW M3 Competition",290,1600.0,silnikBMW, skrzyniaFord, pozycjaStartowa);
        dodajSamochodDoListy(autoTestowe);
    }

    private void refresh() {
        if (aktualnySamochod == null) {
            modelTextField.setText("");
            registrationNumberTextField.setText("");
            weightTextField.setText("");
            speedTextField.setText("");

            engineNameTextField.setText("");
            enginePriceTextField.setText("");
            engineWeightTextField.setText("");
            rpmTextField.setText("");

            gearboxNameTextField.setText("");
            gearboxPriceTextField.setText("");
            gearboxWeightTextField.setText("");
            gearTextField.setText("");

            clutchNameTextField.setText("");
            clutchPriceTextField.setText("");
            clutchWeightTextField.setText("");
            clutchStateTextField.setText("");
            return;
        }

        aktualnySamochod.przeliczPredkosc();

        // Samochód
        modelTextField.setText(aktualnySamochod.getModel());
        registrationNumberTextField.setText(aktualnySamochod.getNrRejest());
        weightTextField.setText(String.format("%.1f kg", aktualnySamochod.getWaga()));
        speedTextField.setText(aktualnySamochod.getAktPredkosc() + " km/h");

        Silnik silnik = aktualnySamochod.getSilnik();
        SkrzyniaBiegow skrzynia = aktualnySamochod.getSkrzynia();

        // Silnik
        if (silnik != null) {
            engineNameTextField.setText(silnik.getNazwa());
            enginePriceTextField.setText(String.format("%.2f", silnik.getCena()));
            engineWeightTextField.setText(String.format("%.1f", silnik.getWaga()));
            rpmTextField.setText(String.valueOf(silnik.getObroty()));
        }

        // Skrzynia biegów
        if (skrzynia != null) {
            gearboxNameTextField.setText(skrzynia.getNazwa());
            gearboxPriceTextField.setText(String.format("%.2f", skrzynia.getCena()));
            gearboxWeightTextField.setText(String.format("%.1f", skrzynia.getWaga()));
            gearTextField.setText(String.valueOf(skrzynia.getAktBieg()));
        }

        // Sprzęgło
        if (skrzynia != null && skrzynia.getSprzeglo() != null) {
            Sprzeglo sprzeglo = skrzynia.getSprzeglo();
            clutchNameTextField.setText(sprzeglo.getNazwa());
            clutchPriceTextField.setText(String.format("%.2f", sprzeglo.getCena()));
            clutchWeightTextField.setText(String.format("%.1f", sprzeglo.getWaga()));
            boolean wcisniete = sprzeglo.isStanSprzegla();
            clutchStateTextField.setText(wcisniete ? "WCIŚNIĘTE" : "ZWOLNIONE");
        }

        Platform.runLater(() -> {
            if (aktualnySamochod != null && carImageView != null) {
                try {
                    Pozycja aktualnaPozycja = aktualnySamochod.getAktPozycja();
                    carImageView.setTranslateX(aktualnaPozycja.getX());
                    carImageView.setTranslateY(aktualnaPozycja.getY());

                } catch (Exception e) {
                    System.out.println("Błąd: Nie można pobrać pozycji samochodu lub ikony: " + e.getMessage());
                }
            }
        });
    }

    // Samochód
    @FXML
    private void onCarStartButton() {
        if (aktualnySamochod != null) {
            aktualnySamochod.wlacz();
            System.out.println("Samochód: Włączam silnik.");
        }
    }

    @FXML
    private void onCarStopButton() {
        if (aktualnySamochod != null) {
            aktualnySamochod.wylacz();
            System.out.println("Samochód: Wyłączam silnik.");
        }
    }

    // Skrzynia Biegów
    @FXML
    private void onGearUpButton() {
        if (aktualnySamochod != null && aktualnySamochod.getSkrzynia() != null) {
            try {
                aktualnySamochod.zwiekszBieg();
                System.out.println("Skrzynia Biegów: Zwiększam bieg.");
            } catch (SamochodException e) {
                pokazBlad(e.getMessage());
            }
        }
    }

    @FXML
    private void onGearDownButton() {
        if (aktualnySamochod != null && aktualnySamochod.getSkrzynia() != null) {
            try {
                aktualnySamochod.zmniejszBieg();
                System.out.println("Skrzynia Biegów: Zmniejszam bieg.");
            } catch (SamochodException e) {
                pokazBlad(e.getMessage());
            }
        }
    }

    // Silnik
    @FXML
    private void onAccelerateButton() {
        if (aktualnySamochod != null && aktualnySamochod.getSilnik() != null) {
            aktualnySamochod.zwiekszObroty();
            System.out.println("Silnik: Dodaję gazu. Obroty: " + aktualnySamochod.getSilnik().getObroty());
        }
    }

    @FXML
    private void onDecelerateButton() {
        if (aktualnySamochod != null && aktualnySamochod.getSilnik() != null) {
            aktualnySamochod.zmniejszObroty();
            System.out.println("Silnik: Ujmuję gazu. Obroty: " + aktualnySamochod.getSilnik().getObroty());
        }
    }

    // Sprzęgło
    @FXML
    private void onClutchEngageButton() {
        if (aktualnySamochod != null && aktualnySamochod.getSkrzynia() != null && aktualnySamochod.getSkrzynia().getSprzeglo() != null) {
            try {
                aktualnySamochod.wcisnijSprzeglo();
                System.out.println("Sprzęgło: Naciśnięte.");
            } catch (SamochodException e) {
                pokazBlad(e.getMessage());
            }
        }
    }

    @FXML
    private void onClutchReleaseButton() {
        if (aktualnySamochod != null && aktualnySamochod.getSkrzynia() != null && aktualnySamochod.getSkrzynia().getSprzeglo() != null) {
            try {
                aktualnySamochod.zwolnijSprzeglo();
                System.out.println("Sprzęgło: Zwolnione.");
            } catch (SamochodException e) {
                pokazBlad(e.getMessage());
            }
        }
    }

    // Górny Panel
    public void dodajSamochodDoListy(Samochod nowySamochod) {
        listaSamochodow.add(nowySamochod);
        carComboBox.getSelectionModel().select(nowySamochod);
        System.out.println("Otrzymano nowy samochód i dodano do listy: " + nowySamochod.getModel());
        refresh();
    }

    @FXML
    private void onCarAddButton() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("DodajSamochod.fxml"));
            Scene scene = new Scene(loader.load());

            DodajSamochodController controller = loader.getController();

            controller.setMainController(this);

            Stage stage = new Stage();
            stage.setTitle("Dodaj nowy samochód");
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onCarDeleteButton() {
        Samochod doUsuniecia = carComboBox.getSelectionModel().getSelectedItem();
        if (doUsuniecia != null) {
            listaSamochodow.remove(doUsuniecia);
            System.out.println("Usunięto samochód: " + doUsuniecia.getNrRejest());

            if (listaSamochodow.isEmpty()) {
                aktualnySamochod = null;
                refresh();
            } else {
                carComboBox.getSelectionModel().selectFirst();
            }
        } else {
            System.out.println("Nie wybrano samochodu do usunięcia.");
        }
    }

    @FXML
    private void onGenericCarButton() {
        System.out.println("Samochód: Wykonuję ogólną akcję.");
    }

    public void pokazBlad(String wiadomosc) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Błąd");
        alert.setHeaderText(null);
        alert.setContentText(wiadomosc);
        alert.showAndWait();
    }

    @Override
    public void update(){
        javafx.application.Platform.runLater(this::refresh);
    }


}
