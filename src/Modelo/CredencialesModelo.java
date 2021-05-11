package Modelo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CredencialesModelo {

    final String myFile = "src/Credenciales/credencial.txt";

    public List<String> obtenerDatosCredenciales() {
        List<String> credenciales = new ArrayList<>();
        try( BufferedReader reader = new BufferedReader(new FileReader(myFile))) {
            String line = reader.readLine();
            while (line != null) {
                credenciales.add(line);
                line = reader.readLine();
            }

        } catch (Exception e) {
             credenciales.add("Error");
        }
        return credenciales;
    }

}
