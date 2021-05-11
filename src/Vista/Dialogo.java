package Vista;

import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import javafx.stage.StageStyle;

public class Dialogo {

    public Alert crearDialogoConfirmacion(String context) {
        Alert dialogo = new Alert(Alert.AlertType.CONFIRMATION);
        dialogo.setTitle("Confirmacion");
        dialogo.initStyle(StageStyle.DECORATED);
        dialogo.setHeaderText(null);
        dialogo.setContentText(context);
        return dialogo;
    }

    public Alert crearDialogoInformacion() {
        Alert dialogo = new Alert(Alert.AlertType.INFORMATION);
        dialogo.setHeaderText(null);
        dialogo.setTitle("Informacion");
        dialogo.initStyle(StageStyle.DECORATED);

        return dialogo;
    }

    public Alert crearDialogoError() {
        Alert dialogo = new Alert(Alert.AlertType.ERROR);
        dialogo.setHeaderText(null);
        dialogo.setTitle("Error");
        dialogo.initStyle(StageStyle.DECORATED);
        return dialogo;
    }

    public TextInputDialog crearDialogoInput(String title, String context) {
        TextInputDialog dialogo = new TextInputDialog();
        dialogo.setTitle(title);
        dialogo.setContentText(context);
        dialogo.setHeaderText(null);
        dialogo.initStyle(StageStyle.DECORATED);
        return dialogo;
    }
}
