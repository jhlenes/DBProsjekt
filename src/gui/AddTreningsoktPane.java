package gui;

import database.Database;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import treningsdagbok.Ovelse;
import treningsdagbok.Treningsokt;

import java.util.List;

public class AddTreningsoktPane extends GridPane
{
    private Database database;
    private Stage window;
    private Scene main;
    private TabPane tabPane;
    private ObservableList<Treningsokt> treningsoktObservableList;

    public AddTreningsoktPane(Database database, Stage window, Scene main, TabPane tabPane, ObservableList<Treningsokt> treningsoktObservableList)
    {
        this.database = database;
        this.window = window;
        this.main = main;
        this.tabPane = tabPane;
        this.treningsoktObservableList = treningsoktObservableList;
        setup();
    }

    public void setup()
    {
        // Paddings
        setAlignment(Pos.CENTER);
        setHgap(10);
        setVgap(10);
        setPadding(new Insets(25, 25, 25, 25));

        // Label on top
        Label label = new Label("Legg til treningsøkt:");
        label.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        add(label, 0, 0, 2, 1);

        Label label2 = new Label("Velg øvelser:");
        label2.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        add(label2, 4, 0, 2, 1);

        // Column properties
        ColumnConstraints column1 = new ColumnConstraints();
        column1.setHalignment(HPos.RIGHT);
        getColumnConstraints().add(column1);


        // Setup components
        Label labelVarighet = new Label("Varighet:");
        TextField tfVarighet = new TextField();
        tfVarighet.setPromptText("Antall minutter");
        Label labelForm = new Label("Form:");
        TextField tfForm = new TextField();
        tfForm.setPromptText("1-10");
        Label labelPrestasjon = new Label("Prestasjon:");
        TextField tfPrestasjon = new TextField();
        tfPrestasjon.setPromptText("1-10");
        Label labelLuftkvalitet = new Label("Luftkvalitet:");
        TextField tfLuftkvalitet = new TextField();
        tfLuftkvalitet.setPromptText("1-10");
        Label labelTemperatur = new Label("Temperatur:");
        TextField tfTemperatur = new TextField();
        tfTemperatur.setPromptText("Heltall");
        Label labelNotat = new Label("Notat:");
        TextField tfNotat = new TextField();
        tfNotat.setPromptText("Beskriv din treningsøkt");

        List<Ovelse> ovelser = database.getOvelseManager().getOvelser();
        ObservableList<Ovelse> ovelseObservableList = FXCollections.observableArrayList(ovelser);
        ListView<Ovelse> ovelseListView = new ListView<>(ovelseObservableList);
        ovelseListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        ovelseListView.setMaxSize(Double.MAX_VALUE, DBApp.SIZE_Y / 2);
        add(ovelseListView, 4, 1, 3, 8);

        // Setup buttons
        Button buttonLeggTil = new Button("Legg til");
        buttonLeggTil.setOnAction(e -> {

            // Get data from textfields
            int varighet = Integer.parseInt(tfVarighet.getText());
            int form = Integer.parseInt(tfForm.getText());
            int prestasjon = Integer.parseInt(tfPrestasjon.getText());
            String notat = tfNotat.getText();
            int luftkvalitet = Integer.parseInt(tfLuftkvalitet.getText());
            int temperatur = Integer.parseInt(tfTemperatur.getText());

            // If addition to database was successful, add locally
            if (database.getTreningsoktManager().addTreningsokt(varighet, form, prestasjon, notat, luftkvalitet, temperatur))
            {

                // Add ovelser to treningsokt
                List<Ovelse> selectedOvelser = ovelseListView.getSelectionModel().getSelectedItems();
                database.getTreningsoktManager().addOvelserToLatest(selectedOvelser);

                treningsoktObservableList.clear();
                List<Treningsokt> treningsokter = database.getTreningsoktManager().getTreningsokter();
                treningsoktObservableList.addAll(treningsokter);

                // Go back to previous scene
                tabPane.getSelectionModel().select(0);
                window.setScene(main);
            }
        });

        Button buttonAvbryt = new Button("Avbryt");
        buttonAvbryt.setOnAction(event -> {

            // Go back to previous screen
            tabPane.getSelectionModel().select(0);
            window.setScene(main);
        });

        // Button properties
        HBox hbButtons = new HBox();
        hbButtons.setSpacing(10);
        hbButtons.setAlignment(Pos.CENTER_LEFT);
        hbButtons.getChildren().addAll(buttonLeggTil, buttonAvbryt);

        // Add components to grid
        add(labelVarighet, 0, 1);
        add(tfVarighet, 1, 1);
        add(labelForm, 0, 2);
        add(tfForm, 1, 2);
        add(labelPrestasjon, 0, 3);
        add(tfPrestasjon, 1, 3);
        add(labelTemperatur, 0, 4);
        add(tfTemperatur, 1, 4);
        add(labelLuftkvalitet, 0, 5);
        add(tfLuftkvalitet, 1, 5);
        add(labelNotat, 0, 6);
        add(tfNotat, 1, 6);
        add(hbButtons, 1, 7, 3, 1);
    }
}