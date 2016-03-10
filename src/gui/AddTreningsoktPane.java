package gui;

import database.Database;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
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

        // Column properties
        ColumnConstraints column1 = new ColumnConstraints();
        column1.setHalignment(HPos.RIGHT);
        getColumnConstraints().add(column1);


        // Setup components
        Label labelVarighet = new Label("Varighet:");
        TextField tfVarighet = new TextField();
        Label labelForm = new Label("Form:");
        TextField tfForm = new TextField();
        Label labelPrestasjon = new Label("Prestasjon:");
        TextField tfPrestasjon = new TextField();
        Label labelNotat = new Label("Notat:");
        TextField tfNotat = new TextField();
        Label labelLuftkvalitet = new Label("Luftkvalitet:");
        TextField tfLuftkvalitet = new TextField();
        Label labelTemperatur = new Label("Temperatur:");
        TextField tfTemperatur = new TextField();

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
        add(labelNotat, 0, 4);
        add(tfNotat, 1, 4);
        add(labelLuftkvalitet, 0, 5);
        add(tfLuftkvalitet, 1, 5);
        add(labelTemperatur, 0, 6);
        add(tfTemperatur, 1, 6);
        add(hbButtons, 1, 7, 3, 1);
    }
}