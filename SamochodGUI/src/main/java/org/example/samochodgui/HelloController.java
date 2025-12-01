/*package org.example.samochodgui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}*/


package org.example.samochodgui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class HelloController {

    private Samochod aktualnySamochod;

    // Samochód
    @FXML private TextField modelTextField;
    @FXML private TextField registrationNumberTextField;
    @FXML private TextField weightTextField;
    @FXML private TextField speedTextField;
    @FXML private Button carStartButton;
    @FXML private Button carStopButton;
    @FXML private Button genericCarButton;

    // Skrzynia Biegów
    @FXML private TextField gearboxNameTextField;
    @FXML private TextField gearboxPriceTextField;
    @FXML private TextField gearboxWeightTextField;
    @FXML private TextField gearTextField;
    @FXML private Button gearUpButton;
    @FXML private Button gearDownButton;

    // Silnik
    @FXML private TextField engineNameTextField;
    @FXML private TextField enginePriceTextField;
    @FXML private TextField engineWeightTextField;
    @FXML private TextField rpmTextField;
    @FXML private Button accelerateButton;
    @FXML private Button decelerateButton;

    // Sprzęgło
    @FXML private TextField clutchNameTextField;
    @FXML private TextField clutchPriceTextField;
    @FXML private TextField clutchWeightTextField;
    @FXML private TextField clutchStateTextField;
    @FXML private Button clutchEngageButton;
    @FXML private Button clutchReleaseButton;

    // Górny Pane;
    @FXML private ComboBox<String> carComboBox;
    @FXML private Button carAddButton;
    @FXML private Button carDeleteButton;


    @FXML
    public void initialize() {
        initializeCarDetails();
        updateCarStateDisplay();
    }

    private void initializeCarDetails() {
        if (aktualnySamochod == null) return;

        // Samochód
        modelTextField.setText(aktualnySamochod.getModel());
        registrationNumberTextField.setText(aktualnySamochod.getNrRejest());
        weightTextField.setText(String.format("%.1f kg", aktualnySamochod.getWaga()));

        // Silnik
        if (aktualnySamochod.getSilnik() != null) {
            Silnik silnik = aktualnySamochod.getSilnik();
            engineNameTextField.setText(silnik.getNazwa());
            enginePriceTextField.setText(String.format("%.2f", silnik.getCena()));
            engineWeightTextField.setText(String.format("%.1f", silnik.getWaga()));
        }

        // Skrzynia Biegów
        if (aktualnySamochod.getSkrzynia() != null) {
            SkrzyniaBiegow skrzynia = aktualnySamochod.getSkrzynia();
            gearboxNameTextField.setText(skrzynia.getNazwa());
            gearboxPriceTextField.setText(String.format("%.2f", skrzynia.getCena()));
            gearboxWeightTextField.setText(String.format("%.1f", skrzynia.getWaga()));
        }

        // Sprzęgło
        if (aktualnySamochod.getSkrzynia() != null && aktualnySamochod.getSkrzynia().getSprzeglo() != null) {
            Sprzeglo sprzeglo = aktualnySamochod.getSkrzynia().getSprzeglo();
            clutchNameTextField.setText(sprzeglo.getNazwa());
            clutchPriceTextField.setText(String.format("%.2f", sprzeglo.getCena()));
            clutchWeightTextField.setText(String.format("%.1f", sprzeglo.getWaga()));
        }
    }

    private void updateCarStateDisplay() {
        if (aktualnySamochod == null) return;

        aktualnySamochod.przeliczPredkosc();

        // Samochód (Prędkość)
        speedTextField.setText(aktualnySamochod.getAktPredkosc() + " km/h");

        // Silnik (Obroty)
        if (aktualnySamochod.getSilnik() != null) {
            rpmTextField.setText(String.valueOf(aktualnySamochod.getSilnik().getObroty()));
        }

        // Skrzynia Biegów (Bieg)
        if (aktualnySamochod.getSkrzynia() != null) {
            gearTextField.setText(String.valueOf(aktualnySamochod.getSkrzynia().getAktBieg()));
        }

        // Sprzęgło (Stan)
        if (aktualnySamochod.getSkrzynia() != null && aktualnySamochod.getSkrzynia().getSprzeglo() != null) {
            boolean wcisniete = aktualnySamochod.getSkrzynia().getSprzeglo().isStanSprzegla();
            clutchStateTextField.setText(wcisniete ? "WCIŚNIĘTE" : "ZWOLNIONE");
        }
    }

    // Samochód
    @FXML
    private void onCarStartButton() {
        if (aktualnySamochod != null) {
            aktualnySamochod.wlacz();
            System.out.println("Samochód: Włączam silnik.");
            updateCarStateDisplay();
        }
    }

    @FXML
    private void onCarStopButton() {
        if (aktualnySamochod != null) {
            aktualnySamochod.wylacz();
            System.out.println("Samochód: Wyłączam silnik.");
            updateCarStateDisplay();
        }
    }

    // Skrzynia Biegów
    @FXML
    private void onGearUpButton() {
        if (aktualnySamochod != null && aktualnySamochod.getSkrzynia() != null) {
            int stareObroty = aktualnySamochod.getSilnik().getObroty();
            int staryBieg = aktualnySamochod.getSkrzynia().getAktBieg();
            aktualnySamochod.getSkrzynia().zwiekszBieg();
            int nowyBieg = aktualnySamochod.getSkrzynia().getAktBieg();
            if (aktualnySamochod.getSkrzynia().getAktBieg() > 1) {
                int noweObroty = (int) (stareObroty * 0.7);
                aktualnySamochod.getSilnik().setObroty(noweObroty);
            }
            System.out.println("Skrzynia Biegów: Zwiększam bieg.");
            updateCarStateDisplay();
        }
    }

    @FXML
    private void onGearDownButton() {
        if (aktualnySamochod != null && aktualnySamochod.getSkrzynia() != null) {
            int stareObroty = aktualnySamochod.getSilnik().getObroty();
            aktualnySamochod.getSkrzynia().zmniejszBieg();
            if (aktualnySamochod.getSkrzynia().getAktBieg() > 0) {
                int noweObroty = (int) (stareObroty * 1.5);
                aktualnySamochod.getSilnik().setObroty(noweObroty);
            }
            System.out.println("Skrzynia Biegów: Zmniejszam bieg.");
            updateCarStateDisplay();
        }
    }

    // Silnik
    @FXML
    private void onAccelerateButton() {
        if (aktualnySamochod != null && aktualnySamochod.getSilnik() != null) {
            aktualnySamochod.getSilnik().zwiekszObroty();
            System.out.println("Silnik: Dodaję gazu. Obroty: " + aktualnySamochod.getSilnik().getObroty());
            updateCarStateDisplay();
        }
    }

    @FXML
    private void onDecelerateButton() {
        if (aktualnySamochod != null && aktualnySamochod.getSilnik() != null) {
            aktualnySamochod.getSilnik().zmniejszObroty();
            System.out.println("Silnik: Ujmuję gazu. Obroty: " + aktualnySamochod.getSilnik().getObroty());
            updateCarStateDisplay();
        }
    }

    // Sprzęgło
    @FXML
    private void onClutchEngageButton() {
        if (aktualnySamochod != null && aktualnySamochod.getSkrzynia() != null && aktualnySamochod.getSkrzynia().getSprzeglo() != null) {
            aktualnySamochod.getSkrzynia().getSprzeglo().wcisnij();
            System.out.println("Sprzęgło: Naciśnięte.");
            updateCarStateDisplay();
        }
    }

    @FXML
    private void onClutchReleaseButton() {
        if (aktualnySamochod != null && aktualnySamochod.getSkrzynia() != null && aktualnySamochod.getSkrzynia().getSprzeglo() != null) {
            aktualnySamochod.getSkrzynia().getSprzeglo().zwolnij();
            System.out.println("Sprzęgło: Zwolnione.");
            updateCarStateDisplay();
        }
    }

    // Górny Panel
    public void dodajSamochodDoListy(Samochod nowySamochod) {
        System.out.println("Otrzymano nowy samochód: " + nowySamochod.getModel());
        this.aktualnySamochod = nowySamochod;
        this.aktualnySamochod.wlacz();

        initializeCarDetails();
        updateCarStateDisplay();
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
        System.out.println("Panel: Usuwam wybrany samochód (implementacja usuwania).");
    }

    @FXML
    private void onGenericCarButton() {
        System.out.println("Samochód: Wykonuję ogólną akcję.");
    }


}
