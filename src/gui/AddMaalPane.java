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
import treningsdagbok.Maal;
import treningsdagbok.Ovelse;

import java.sql.Date;
import java.sql.Time;

public class AddMaalPane extends GridPane
{
    private Database database;
    private Stage window;
    private Scene main;
    private TabPane tabPane;
    private ObservableList<Maal> maalObservableList;
    private Ovelse ovelse;

    public AddMaalPane(Database database, Stage window, Scene main, TabPane tabPane, ObservableList<Maal> maalObservableList, Ovelse ovelse)
    {
        this.database = database;
        this.window = window;
        this.main = main;
        this.tabPane = tabPane;
        this.maalObservableList = maalObservableList;
        this.ovelse = ovelse;
        setup();
    }

    private void setup()
    {
        // Paddings
        setAlignment(Pos.CENTER);
        setHgap(10);
        setVgap(10);

        // Show name of ovelse on top
        Label ovelseNavn = new Label(ovelse.getNavn() + ":");
        add(ovelseNavn, 1, 0, 1, 1);

        // Column properties
        ColumnConstraints column1 = new ColumnConstraints();
        column1.setHalignment(HPos.RIGHT);
        getColumnConstraints().add(column1);

        // Setup components
        Label labelSett = new Label("Sett:");
        TextField tfSett = new TextField();
        Label labelRepetisjoner = new Label("Repetisjoner:");
        TextField tfRepetisjoner = new TextField();
        Label labelBelastning = new Label("Belastning:");
        TextField tfBelastning = new TextField();

        // Setup buttons
        Button buttonLeggTil = new Button("Legg til");
        buttonLeggTil.setOnAction(e -> {

            // Get data from textfields
            Date dato = new Date(System.currentTimeMillis());
            Time tidspunkt = new Time(System.currentTimeMillis());
            int sett = Integer.parseInt(tfSett.getText());
            int repetisjoner = Integer.parseInt(tfRepetisjoner.getText());
            int belastning = Integer.parseInt(tfBelastning.getText());
            int ovelseNr = ovelse.getOvelseNr();

            // If addition to database is successfull, add locally
            if (database.getMaalManager().addMaal(dato, tidspunkt, sett, repetisjoner, belastning, ovelseNr))
            {
                maalObservableList.add(database.getMaalManager().getLatest());

                // Go back to previous scene
                Scene scene = new Scene(new MaalPane(database, window, main, tabPane, ovelse), DBApp.SIZE_X, DBApp.SIZE_Y);
                window.setScene(scene);
            }
        });

        Button buttonAvbryt = new Button("Avbryt");
        buttonAvbryt.setOnAction(event -> {

            // Go back to previous screen
            Scene scene = new Scene(new MaalPane(database, window, main, tabPane, ovelse), DBApp.SIZE_X, DBApp.SIZE_Y);
            window.setScene(scene);
        });

        // Button properties
        HBox hbButtons = new HBox();
        hbButtons.setSpacing(10);
        hbButtons.setAlignment(Pos.CENTER_LEFT);
        hbButtons.getChildren().addAll(buttonLeggTil, buttonAvbryt);

        // Add components to grid
        add(labelSett, 0, 1);
        add(tfSett, 1, 1);
        add(labelRepetisjoner, 0, 2);
        add(tfRepetisjoner, 1, 2);
        add(labelBelastning, 0, 3);
        add(tfBelastning, 1, 3);
        add(hbButtons, 1, 4, 1, 1);
    }

}
