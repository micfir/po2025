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
        Silnik silnik = new Silnik("Silnik V4", 100.0, 5000.0, "A", "V4-200", 6000);
        Sprzeglo sprzeglo = new Sprzeglo("Standardowe sprzęgło", 10.0, 200.0, "A", "B100", false); // Zmienione na false
        SkrzyniaBiegow skrzynia = new SkrzyniaBiegow("Skrzynia 6 biegów", 50.0, 1500.0, "A", "GB", 6, sprzeglo);
        Pozycja start = new Pozycja(0, 0);

        this.aktualnySamochod = new Samochod("KR12345", "ModelX", 200, silnik, skrzynia, start);

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
            aktualnySamochod.getSkrzynia().zwiekszBieg();
            if (aktualnySamochod.getSkrzynia().getAktBieg() > 1) { // Zakładamy, że nie redukujemy obrotów ruszając z luzu
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
    @FXML
    private void onCarAddButton() {
        System.out.println("Panel: Dodaję nowy samochód (implementacja dodawania).");
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
