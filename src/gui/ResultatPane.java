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
import treningsdagbok.Ovelse;
import treningsdagbok.Resultater;

import java.util.List;

public class ResultatPane extends GridPane
{
    private Database database;
    private Stage window;
    private Scene main;
    private TabPane tabPane;
    private Ovelse ovelse;

    public ResultatPane(Database database, Stage window, Scene main, TabPane tabPane, Ovelse ovelse)
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
        Label label = new Label("Resultater for " + ovelse.getNavn() + ":");
        label.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
        add(label, 0, 0, 3, 1);

        // Setup list of resultater for given ovelse
        List<Resultater> resultatList = database.getResultatManager().getResultat(ovelse.getOvelseNr());
        ObservableList<Resultater> resultaterObservableList = FXCollections.observableArrayList(resultatList);
        ListView<Resultater> resultaterListView = new ListView<>(resultaterObservableList);
        resultaterListView.setPrefWidth(DBApp.SIZE_X / 2);
        add(resultaterListView, 0, 1, 3, 4);

        // Setup buttons
        Button buttonSlett = new Button("Slett");
        buttonSlett.setOnAction(e -> {
            Resultater resultat = resultaterListView.getSelectionModel().getSelectedItem();
            if (database.getResultatManager().deleteResultat(resultat.getOktNr(), resultat.getOvelseNr()))
            {
                resultaterObservableList.remove(resultat);
            }
        });

        Button buttonTilbake = new Button("Tilbake");
        buttonTilbake.setOnAction(e -> {
            tabPane.getSelectionModel().select(1);
            window.setScene(main);
        });

        CheckBox toppTi = new CheckBox("Vis kun topp 10");
        toppTi.setOnAction(event -> {
            if (toppTi.isSelected())
            {
                resultaterObservableList.clear();
                List<Resultater> resultater = database.getResultatManager().getToppTiResultat(ovelse.getOvelseNr());
                resultaterObservableList.addAll(resultater);
            } else
            {
                resultaterObservableList.clear();
                List<Resultater> resultater = database.getResultatManager().getResultat(ovelse.getOvelseNr());
                resultaterObservableList.addAll(resultater);
            }
        });

        // Equal sized buttons
        buttonSlett.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        buttonTilbake.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        TilePane tileButtons = new TilePane(Orientation.HORIZONTAL);
        tileButtons.setPadding(new Insets(20, 10, 20, 0));
        tileButtons.setHgap(10);
        tileButtons.setVgap(10);
        tileButtons.getChildren().addAll(buttonSlett, buttonTilbake, toppTi);

        add(tileButtons, 0, 5, 4, 1);
    }

}
