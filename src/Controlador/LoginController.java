package Controlador;

import Modelo.CredencialesModelo;
import Vista.Dialogo;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class LoginController {

    Dialogo d = new Dialogo();

    @FXML
    private TextField campoUsuario;

    @FXML
    private PasswordField campoPassword;

    @FXML
    private TextField passwordShow;

    @FXML
    private AnchorPane anchor;

    @FXML
    private RadioButton radioBtn;

    private int contador = 0;

    @FXML
    void LoginWithFile() throws IOException {
        CredencialesModelo c = new CredencialesModelo();
        List<String> list = c.obtenerDatosCredenciales();

        if (!(list.get(0).equals("Error"))){
            String user = campoUsuario.getText();
            String password = campoPassword.getText();
            String passField = passwordShow.getText();

            if (user.equals(list.get(0)) && (password.equals(list.get(1)) || passField.equals((list.get(1))))) {
                try {
                    campoPassword.setText("");
                    campoUsuario.setText("");
                    passwordShow.setText("");

                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../Vista/NotePad.fxml"));
                    Parent root = loader.load();
                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    stage.setTitle("MiniLab Notepad");
                    stage.setResizable(false);
                    stage.setScene(scene);
                    stage.getIcons().add(new Image("Assets/web-programming.png"));
                    stage.show();
                    ((Stage)anchor.getScene().getWindow()).close();
                }
                catch (Exception e) {
                    System.out.println("Error al iniciar Sesion");
                }
            }
            else {
                contador = contador + 1;
                if (contador <= 2) {
                    Alert dialogo = d.crearDialogoError();
                    dialogo.setContentText("Usuario o contraseña incorrecta");
                    dialogo.showAndWait();
                }
                else {
                    Alert dialogo = d.crearDialogoError();
                    dialogo.setContentText("Excepdio el numero de intentos");
                    dialogo.showAndWait();
                    ((Stage)anchor.getScene().getWindow()).close();
                }
            }
        }
        else {
            Alert dialogoError = d.crearDialogoError();
            dialogoError.setContentText("Error al leer los credenciales del archivo");
            dialogoError.showAndWait();
        }
    }

    @FXML
    void cerrarAplicacion() {
        Alert dialogo = d.crearDialogoConfirmacion("¿Desea salir del Login?");
        Optional<ButtonType> result = dialogo.showAndWait();
        if (result.get() == ButtonType.OK) {
            try {
                ((Stage)anchor.getScene().getWindow()).close();
            } catch (Exception e) {
                System.out.println("Error al cerrar ventan");
            }
        }
    }

    @FXML
    void verPassword() {
        if (radioBtn.isSelected()) {
            passwordShow.setText(campoPassword.getText());
            passwordShow.setVisible(true);
            campoPassword.setVisible(false);
        }
        else {
            campoPassword.setText(passwordShow.getText());
            campoPassword.setVisible(true);
            passwordShow.setVisible(false);
        }
    }
}