package Controlador;

import Modelo.NotePadModelo;
import Vista.Dialogo;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Objects;
import java.util.Optional;

public class NotePadController {

    Dialogo d = new Dialogo();

    @FXML
    private TextArea textArea;

    @FXML
    private VBox Vbox;


    public String getMessage(String nombreArchivo) {
        try {
            NotePadModelo model = new NotePadModelo();
            model.setMyFile(nombreArchivo);
            return model.getData();
        } catch (Exception e) {
            return "Error";
        }
    }

    public boolean writeMessage(String message, String nombre) {
        try {
            NotePadModelo model = new NotePadModelo();
            model.setMyFile(nombre);
            return model.writeData(message);
        } catch (Exception e) {
            return false;
        }
    }

    @FXML
    void borrarTextArea() {
        Alert dialogo = d.crearDialogoConfirmacion("¿Desea Borrar el texto?");
        Optional<ButtonType> result = dialogo.showAndWait();
        if (result.get() == ButtonType.OK) {
            limpiarTextArea();
        }
    }

    public void limpiarTextArea() {
        try {
            textArea.setText("");
        } catch (Exception e) {
            System.out.println("Error al limpiar Text Area");
        }
    }

    @FXML
    void salirVentana() {
        Alert dialogo = d.crearDialogoConfirmacion("¿Desea salir del Notepad?");
        Optional<ButtonType> result = dialogo.showAndWait();
        if (result.get() == ButtonType.OK) {
            cerrarAplicacion();
        }
    }



    @FXML
    public void cerrarAplicacion() {
        try {
            ((Stage)Vbox.getScene().getWindow()).close();
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../Vista/Login.fxml")));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Login NotePad");
            stage.setResizable(false);
            stage.setScene(scene);
            stage.getIcons().add(new Image("Assets/web-programming.png"));
            stage.show();
        } catch (Exception e) {
            System.out.println("Error al cerrar la ventana");
        }
    }

    @FXML
    void abrirArchivo() {
        TextInputDialog dialogo = d.crearDialogoInput("Buscar Archivo", "Ingrese el nombre del archivo");
        Optional<String> result = dialogo.showAndWait();
        try {
            if (!(getMessage(result.get()).equals("Error"))) {
                textArea.setText(getMessage(result.get()));
                Platform.runLater(()->textArea.end());
            }
            else {
               Alert dialogoError = d.crearDialogoError();
               dialogoError.setContentText("Archivo no encontrado o existe algun error, intenta nuevamente");
               dialogoError.showAndWait();
            }

        } catch (Exception e) {
            Alert dialogoInfo = d.crearDialogoInformacion();
            dialogoInfo.setContentText("Se cancelo la accion");
            dialogoInfo.showAndWait();

        }
    }

    @FXML
    void guardarTexto() {
        try {
            TextInputDialog dialogoInput = d.crearDialogoInput("Guardar archivo", "Ingrese el nombre del archivo");
            Optional<String> resultInput = dialogoInput.showAndWait();
            Alert dialogoInformacion = d.crearDialogoInformacion();
            String r = resultInput.get().replaceAll(" ","");
            if(r.isEmpty()){
                dialogoInformacion.setContentText("Error nombre vacio");
            }
            else {
                boolean resultBool = writeMessage(textArea.getText(), resultInput.get());
                if (resultBool) {
                    dialogoInformacion.setContentText("Se guardo la informacion correctamente");
                } else {
                    dialogoInformacion.setContentText("Error! No se guardo la informacion");
                }
            }
            dialogoInformacion.showAndWait();
        }
        catch (Exception e) {
            Alert dialogoInfo = d.crearDialogoInformacion();
            dialogoInfo.setContentText("Se cancelo la accion");
            dialogoInfo.showAndWait();
            System.out.println("Error al guardar el archivo");
        }
    }

    @FXML
    void guardarTextoMenu() {
        TextInputDialog dialogoInput = d.crearDialogoInput("Guardar archivo", "Ingrese el nombre del archivo");
        Optional<String> resultInput = dialogoInput.showAndWait();
        boolean resultBool = writeMessage(textArea.getText(), resultInput.get());
        Alert dialogoInformacion = d.crearDialogoInformacion();
        String r = resultInput.get().replaceAll(" ","");
        if(r.isEmpty()){
            dialogoInformacion.setContentText("Error, nombre vacio");
        }
        else {
            if (resultBool) {
                dialogoInformacion.setContentText("Se guardo la informacion correctamente");
                textArea.setText("");
            } else {
                dialogoInformacion.setContentText("Error! No se guardo la informacion");
            }
        }
        dialogoInformacion.showAndWait();
    }
}

