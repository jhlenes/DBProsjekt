package gui;

import database.Database;
import treningsdagbok.Maal;
import treningsdagbok.Ovelse;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;
import treningsdagbok.Treningsokt;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

public class DBApp extends Application
{
    private Database database;
    private Stage window;

    private TabPane tabPane;

    private Scene main;
    private Scene ovelserScene;

    private ListView<Ovelse> ovelseListView;
    private ObservableList<Ovelse> ovelseObservableList;

    private Button buttonLeggTil = new Button("Legg til");
    private Button buttonEndre = new Button("Endre");
    private Button buttonSlett = new Button("Slett");
    private Button buttonMaal = new Button("Mål");

    private MaalSceneMaker maalSceneMaker = new MaalSceneMaker();

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage)
    {
        // Setup database connection
        database = new Database();

        // Create scenes
        GridPane layout = setupOvelseScene();
        ovelserScene = new Scene(layout, 500, 500);

        GridPane oktLayout = setupOktScene();

        // Use tabPane
        tabPane = new TabPane();
        Tab tabOkter = new Tab();
        tabOkter.setText("Treningsøkter");
        tabOkter.setContent(oktLayout);

        Tab tabOvelser = new Tab();
        tabOvelser.setText("Øvelser");
        tabOvelser.setContent(layout);

        tabPane.getTabs().addAll(tabOkter, tabOvelser);

        main = new Scene(tabPane, 500, 500);

        // Setup Window
        window = primaryStage;
        window.setTitle("Treningsdagbok");
        window.setScene(main);
        window.show();
    }

    private GridPane setupOktScene()
    {
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        // Setup list of maal for given ovelse
        List<Treningsokt> okter = database.getTreningsoktManager().getTreningsokter();
        ObservableList<Treningsokt> treningsoktObservableList = FXCollections.observableArrayList(okter);
        ListView<Treningsokt> treningsoktListView = new ListView<>(treningsoktObservableList);
        grid.add(treningsoktListView, 0, 1, 3, 4);

        return grid;

    }

    private GridPane setupOvelseScene()
    {
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        setupOvelseListView(grid);
        setupButtonRow(grid);
        return grid;
    }

    private void setupOvelseListView(GridPane grid)
    {
        List<Ovelse> ovelser = database.getOvelseManager().getOvelser();
        ovelseObservableList = FXCollections.observableArrayList(ovelser);
        ovelseListView = new ListView<>(ovelseObservableList);
        grid.add(ovelseListView, 0, 0, 3, 4);
    }

    private void setupButtonRow(GridPane grid)
    {
        buttonLeggTil.setOnAction(e -> {
            Scene scene = new Scene(setupAddScene(), 500, 500);
            window.setScene(scene);
        });

        buttonEndre.setOnAction(e -> {
            Ovelse ovelse = ovelseListView.getSelectionModel().getSelectedItem();
            Scene scene = new Scene(setupEditScene(ovelse), 500, 500);
            window.setScene(scene);
        });

        buttonSlett.setOnAction(e -> {
            Ovelse ovelse = ovelseListView.getSelectionModel().getSelectedItem();
            if (database.getOvelseManager().deleteOvelse(ovelse.getOvelseNr()))
            {
                ovelseObservableList.remove(ovelse);
            }
        });

        buttonMaal.setOnAction(e -> {
            Ovelse ovelse = ovelseListView.getSelectionModel().getSelectedItem();
            Scene scene = new Scene(maalSceneMaker.make(ovelse), 500, 500);
            window.setScene(scene);
        });

        // Let buttons grow, otherwise they will be different sizes based
        // on the length of the label
        buttonLeggTil.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        buttonEndre.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        buttonSlett.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        buttonMaal.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        TilePane tileButtons = new TilePane(Orientation.HORIZONTAL);
        tileButtons.setPadding(new Insets(20, 10, 20, 0));
        tileButtons.setHgap(10);
        tileButtons.setVgap(10);
        tileButtons.getChildren().addAll(buttonLeggTil, buttonEndre, buttonSlett, buttonMaal);

        grid.add(tileButtons, 0, 4, 4, 1);
    }

    private GridPane setupAddScene()
    {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(12);

        ColumnConstraints column1 = new ColumnConstraints();
        column1.setHalignment(HPos.RIGHT);
        grid.getColumnConstraints().add(column1);

        ColumnConstraints column2 = new ColumnConstraints();
        column2.setHalignment(HPos.LEFT);
        grid.getColumnConstraints().add(column2);

        Label labelNavn = new Label("Navn:");
        TextField tfNavn = new TextField();
        Label labelBeskrivelse = new Label("Beskrivelse:");
        TextField tfBeskrivelse = new TextField();

        Button leggTil = new Button("Legg til");
        leggTil.setOnAction(event -> {     // Update database and listview
            if (database.getOvelseManager().addOvelse(tfNavn.getText(), tfBeskrivelse.getText()))
            {
                ovelseObservableList.add(database.getOvelseManager().getOvelse(tfNavn.getText()));
                tfNavn.clear();
                tfBeskrivelse.clear();
                tabPane.getSelectionModel().select(1);
                window.setScene(main);
            }
        });
        Button avbryt = new Button("Avbryt");
        avbryt.setOnAction(event -> {   // Go back to previous screen
            tfNavn.clear();
            tfBeskrivelse.clear();
            tabPane.getSelectionModel().select(1);
            window.setScene(main);
        });

        HBox hbButtons = new HBox();
        hbButtons.setSpacing(10.0);
        hbButtons.setAlignment(Pos.CENTER_LEFT);
        hbButtons.getChildren().addAll(leggTil, avbryt);

        grid.add(labelNavn, 0, 0);
        grid.add(tfNavn, 1, 0);
        grid.add(labelBeskrivelse, 0, 1);
        grid.add(tfBeskrivelse, 1, 1);
        grid.add(hbButtons, 1, 2, 1, 1);

        return grid;
    }

    private GridPane setupEditScene(Ovelse ovelse)
    {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(12);

        ColumnConstraints column1 = new ColumnConstraints();
        column1.setHalignment(HPos.RIGHT);
        grid.getColumnConstraints().add(column1);

        ColumnConstraints column2 = new ColumnConstraints();
        column2.setHalignment(HPos.LEFT);
        grid.getColumnConstraints().add(column2);

        Label labelNavn = new Label("Navn:");
        TextField tfNavn = new TextField(ovelse.getNavn());
        Label labelBeskrivelse = new Label("Beskrivelse:");
        TextField tfBeskrivelse = new TextField(ovelse.getBeskrivelse());

        Button endre = new Button("Endre");
        endre.setOnAction(event -> {     // Update database and listview
            String nyttNavn = tfNavn.getText();
            String nyBeskrivelse = tfBeskrivelse.getText();
            if (database.getOvelseManager().editOvelse(ovelse.getOvelseNr(), nyttNavn, nyBeskrivelse))
            {
                ovelse.setNavn(nyttNavn);
                ovelse.setBeskrivelse(nyBeskrivelse);
                ovelseListView.refresh();
                tfNavn.clear();
                tfBeskrivelse.clear();
                tabPane.getSelectionModel().select(1);
                window.setScene(main);
            }
        });
        Button avbryt = new Button("Avbryt");
        avbryt.setOnAction(event -> {   // Go back to previous screen
            tfNavn.clear();
            tfBeskrivelse.clear();
            tabPane.getSelectionModel().select(1);
            window.setScene(main);
        });

        HBox hbButtons = new HBox();
        hbButtons.setSpacing(10.0);
        hbButtons.setAlignment(Pos.CENTER_LEFT);
        hbButtons.getChildren().addAll(endre, avbryt);

        grid.add(labelNavn, 0, 0);
        grid.add(tfNavn, 1, 0);
        grid.add(labelBeskrivelse, 0, 1);
        grid.add(tfBeskrivelse, 1, 1);
        grid.add(hbButtons, 1, 2, 1, 1);

        return grid;
    }

    private class MaalSceneMaker
    {

        public GridPane make(Ovelse ovelse)
        {
            // Set paddings and margins
            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);
            grid.setPadding(new Insets(25, 25, 25, 25));

            // Show name of ovelse on top
            Label ovelseNavn = new Label(ovelse.getNavn() + ":");
            grid.add(ovelseNavn, 0, 0, 3, 1);

            // Setup list of maal for given ovelse
            List<Maal> maalList = database.getMaalManager().getAlleMaalFor(ovelse.getOvelseNr());
            ObservableList<Maal> maalObservableList = FXCollections.observableArrayList(maalList);
            ListView<Maal> maalListView = new ListView<>(maalObservableList);
            grid.add(maalListView, 0, 1, 3, 4);

            // Setup buttons
            setupButtonRow(grid, maalObservableList, maalListView, ovelse);

            return grid;
        }

        private void setupButtonRow(GridPane grid, ObservableList<Maal> maalObservableList, ListView<Maal> maalListView, Ovelse ovelse)
        {
            Button buttonLeggTil = new Button("Legg til");
            buttonLeggTil.setOnAction(e -> {
                Scene scene = new Scene(setupMaalAddScene(maalObservableList, ovelse), 500, 500);
                window.setScene(scene);
            });

            Button buttonSlett = new Button("Slett");
            buttonSlett.setOnAction(e -> {
                Maal maal = maalListView.getSelectionModel().getSelectedItem();
                if (database.getMaalManager().deleteMaal(maal.getMaalNr()))
                {
                    maalObservableList.remove(maal);
                }
            });

            Button buttonTilbake = new Button("Tilbake");
            buttonTilbake.setOnAction(e -> {
                tabPane.getSelectionModel().select(1);
                window.setScene(main);
            });

            // Equal sized buttons
            buttonTilbake.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
            buttonLeggTil.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
            buttonSlett.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

            TilePane tileButtons = new TilePane(Orientation.HORIZONTAL);
            tileButtons.setPadding(new Insets(20, 10, 20, 0));
            tileButtons.setHgap(10);
            tileButtons.setVgap(10);
            tileButtons.getChildren().addAll(buttonLeggTil, buttonSlett, buttonTilbake);

            grid.add(tileButtons, 0, 5, 4, 1);
        }

        private GridPane setupMaalAddScene(ObservableList<Maal> maalObservableList, Ovelse ovelse)
        {
            GridPane grid = new GridPane();
            grid.setAlignment(Pos.CENTER);
            grid.setHgap(10);
            grid.setVgap(12);

            // Show name of ovelse on top
            Label ovelseNavn = new Label(ovelse.getNavn() + ":");
            grid.add(ovelseNavn, 1, 0, 1, 1);

            ColumnConstraints column1 = new ColumnConstraints();
            column1.setHalignment(HPos.RIGHT);
            grid.getColumnConstraints().add(column1);

            Label labelSett = new Label("Sett:");
            TextField tfSett = new TextField();
            Label labelRepetisjoner = new Label("Repetisjoner:");
            TextField tfRepetisjoner = new TextField();
            Label labelBelastning = new Label("Belastning:");
            TextField tfBelastning = new TextField();

            Button buttonLeggTil = new Button("Legg til");
            buttonLeggTil.setOnAction(e -> {     // Update database and listview
                Date dato = new Date(System.currentTimeMillis());
                Time tidspunkt = new Time(System.currentTimeMillis());
                int sett = Integer.parseInt(tfSett.getText());
                int repetisjoner = Integer.parseInt(tfRepetisjoner.getText());
                int belastning = Integer.parseInt(tfBelastning.getText());
                int ovelseNr = ovelse.getOvelseNr();

                if (database.getMaalManager().addMaal(dato, tidspunkt, sett, repetisjoner, belastning, ovelseNr))
                {
                    maalObservableList.add(database.getMaalManager().getLatest());
                    Scene scene = new Scene(make(ovelse), 500, 500);
                    window.setScene(scene);
                }
            });

            Button buttonAvbryt = new Button("Avbryt");
            buttonAvbryt.setOnAction(event -> {   // Go back to previous screen
                Scene scene = new Scene(make(ovelse), 500, 500);
                window.setScene(scene);
            });

            HBox hbButtons = new HBox();
            hbButtons.setSpacing(10.0);
            hbButtons.setAlignment(Pos.CENTER_LEFT);
            hbButtons.getChildren().addAll(buttonLeggTil, buttonAvbryt);

            grid.add(labelSett, 0, 1);
            grid.add(tfSett, 1, 1);
            grid.add(labelRepetisjoner, 0, 2);
            grid.add(tfRepetisjoner, 1, 2);
            grid.add(labelBelastning, 0, 3);
            grid.add(tfBelastning, 1, 3);

            grid.add(hbButtons, 1, 4, 1, 1);

            return grid;
        }

    }

}
