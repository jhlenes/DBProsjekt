package gui;

import database.Database;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import treningsdagbok.Maal;
import treningsdagbok.Ovelse;

import java.util.List;

public class MaalPane extends GridPane
{
    private Database database;
    private Stage window;
    private Scene main;
    private TabPane tabPane;
    private Ovelse ovelse;

    public MaalPane(Database database, Stage window, Scene main, TabPane tabPane, Ovelse ovelse)
    {
        this.database = database;
        this.window = window;
        this.main = main;
        this.tabPane = tabPane;
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
        Label label = new Label("Mål for " + ovelse.getNavn() + ":");
        label.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
        add(label, 0, 0, 3, 1);

        // Setup list of maal for given ovelse
        List<Maal> maalList = database.getMaalManager().getAlleMaalFor(ovelse.getOvelseNr());
        ObservableList<Maal> maalObservableList = FXCollections.observableArrayList(maalList);
        ListView<Maal> maalListView = new ListView<>(maalObservableList);
        maalListView.setPrefWidth(DBApp.SIZE_X / 2);
        add(maalListView, 0, 1, 3, 4);

        // Setup buttons
        Button buttonLeggTil = new Button("Legg til");
        buttonLeggTil.setOnAction(e -> {
            Scene scene = new Scene(new AddMaalPane(database, window, main, tabPane, maalObservableList, ovelse), DBApp.SIZE_X, DBApp.SIZE_Y);
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

        add(tileButtons, 0, 5, 4, 1);
    }

}
