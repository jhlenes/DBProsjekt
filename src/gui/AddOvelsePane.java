package gui;

import database.Database;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import treningsdagbok.Ovelse;

public class AddOvelsePane extends GridPane
{
    private Database database;
    private Stage window;
    private Scene main;
    private TabPane tabPane;
    private ObservableList<Ovelse> ovelseObservableList;

    public AddOvelsePane(Database database, Stage window, Scene main, TabPane tabPane, ObservableList<Ovelse> ovelseObservableList)
    {
        this.database = database;
        this.window = window;
        this.main = main;
        this.tabPane = tabPane;
        this.ovelseObservableList = ovelseObservableList;
        setup();
    }

    private void setup()
    {
        // Paddings
        setAlignment(Pos.CENTER);
        setHgap(10);
        setVgap(10);

        // Column properties
        ColumnConstraints column1 = new ColumnConstraints();
        column1.setHalignment(HPos.RIGHT);
        getColumnConstraints().add(column1);

        // Setup components
        Label labelNavn = new Label("Navn:");
        TextField tfNavn = new TextField();
        Label labelBeskrivelse = new Label("Beskrivelse:");
        TextField tfBeskrivelse = new TextField();

        // Setup buttons
        Button leggTil = new Button("Legg til");
        leggTil.setOnAction(event -> {

            // Get data from textfields
            String navn = tfNavn.getText();
            String beskrivelse = tfBeskrivelse.getText();

            // If addition to database was successful, add locally
            if (database.getOvelseManager().addOvelse(navn, beskrivelse))
            {
                ovelseObservableList.add(database.getOvelseManager().getOvelse(tfNavn.getText()));

                // Go back to previous scene
                tabPane.getSelectionModel().select(1);
                window.setScene(main);
            }
        });

        Button avbryt = new Button("Avbryt");
        avbryt.setOnAction(event -> {

            // Go back to previous screen
            tabPane.getSelectionModel().select(1);
            window.setScene(main);
        });

        // Button properties
        HBox hbButtons = new HBox();
        hbButtons.setSpacing(10);
        hbButtons.setAlignment(Pos.CENTER_LEFT);
        hbButtons.getChildren().addAll(leggTil, avbryt);

        // Add components to grid
        add(labelNavn, 0, 0);
        add(tfNavn, 1, 0);
        add(labelBeskrivelse, 0, 1);
        add(tfBeskrivelse, 1, 1);
        add(hbButtons, 1, 2, 1, 1);
    }
}
