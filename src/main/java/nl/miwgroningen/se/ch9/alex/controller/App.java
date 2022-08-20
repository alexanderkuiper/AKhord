package nl.miwgroningen.se.ch9.alex.controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import nl.miwgroningen.se.ch9.alex.database.DBaccess;

import java.io.IOException;
import java.util.Objects;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private static final DBaccess DBACCESS = new DBaccess(
            "akkoord", "user", "password");
    private final Image ICON = new Image(Objects.requireNonNull(getClass().getResource(
            "/images/AKhordIcon.png")).toExternalForm());

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("primary"), 640, 480);
        stage.getIcons().add(ICON);
        stage.setTitle("AKhord");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();

    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

//    private void submitDbaccess() throws IOException {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/nl/miwgroningen/se/ch9/alex/primary.fxml"));
//        loader.load();
//        ((SecondaryController)loader.getController()).setdBaccess(dBaccess);
//    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/view/" + fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static DBaccess getdBaccess() {
        return DBACCESS;
    }

    public static void main(String[] args) {
        launch();
    }

}

