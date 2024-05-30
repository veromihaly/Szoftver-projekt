package bishopexchange;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Ez az osztály felelős az alkalmazás indításáért és az inicializációért.
 *
 */
public class BishopExchangeApplication extends Application {
    /**
     * Ebben a metódusban történik az alkalmazás ablakának létrehozása,
     * a felhasználói felület betöltése és az ablak megjelenítése.
     * @param stage a megjelenítendő alkalmazás ablak
     * @throws IOException egy kivétel (exception) osztály
     */
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/ui.fxml"));
        stage.setTitle("Bishop Exchange");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}
