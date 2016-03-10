package gui;

import database.Database;
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

import java.util.List;

public class EditOvelsePane extends GridPane
{
    private Database database;
    private Stage window;
    private Scene main;
    private TabPane tabPane;
    private ObservableList<Ovelse> ovelseObservableList;
    private Ovelse ovelse;

    public EditOvelsePane(Database database, Stage window, Scene main, TabPane tabPane, ObservableList<Ovelse> ovelseObservableList, Ovelse ovelse)
    {
        this.database = database;
        this.window = window;
        this.main = main;
        this.tabPane = tabPane;
        this.ovelseObservableList = ovelseObservableList;
        this.ovelse = ovelse;
        setup();
    }

    private void setup()
    {
        // Paddings
        setAlignment(Pos.CENTER);
        setHgap(10);
        setVgap(10);
        setPadding(new Insets(25, 25, 25, 25));

        // Label on top
        Label label = new Label("Endre øvelse:");
        label.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        add(label, 0, 0, 2, 1);

        // Column properties
        ColumnConstraints column1 = new ColumnConstraints();
        column1.setHalignment(HPos.RIGHT);
        getColumnConstraints().add(column1);

        ColumnConstraints column2 = new ColumnConstraints();
        column2.setHalignment(HPos.LEFT);
        getColumnConstraints().add(column2);

        // Setup components
        Label labelNavn = new Label("Navn:");
        TextField tfNavn = new TextField(ovelse.getNavn());
        Label labelBeskrivelse = new Label("Beskrivelse:");
        TextField tfBeskrivelse = new TextField(ovelse.getBeskrivelse());

        // Setup buttons
        Button endre = new Button("Endre");
        endre.setOnAction(event -> {

            // Get data from textfields
            String nyttNavn = tfNavn.getText();
            String nyBeskrivelse = tfBeskrivelse.getText();

            // If editing the database was successful, edit locally
            if (database.getOvelseManager().editOvelse(ovelse.getOvelseNr(), nyttNavn, nyBeskrivelse))
            {
                ovelseObservableList.clear();
                List<Ovelse> ovelser = database.getOvelseManager().getOvelser();
                ovelseObservableList.addAll(ovelser);

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
        hbButtons.getChildren().addAll(endre, avbryt);

        // Add components to grid
        add(labelNavn, 0, 1);
        add(tfNavn, 1, 1);
        add(labelBeskrivelse, 0, 2);
        add(tfBeskrivelse, 1, 2);
        add(hbButtons, 1, 3, 1, 1);
    }
}
