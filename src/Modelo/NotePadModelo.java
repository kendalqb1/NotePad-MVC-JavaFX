package Modelo;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class NotePadModelo {

    String myFile = "src/Archivos/file.txt";

    public void setMyFile(String nombreArchivo) {
        myFile = "src/Archivos/" + nombreArchivo + ".txt";
    }

    public String getData() throws IOException {
        if (!(new File(myFile).isFile())) {
            Files.createFile(Paths.get("src/Archivos/file.txt"));
        }
        String data;

        try( BufferedReader reader = new BufferedReader(new FileReader(myFile))) {
            StringBuilder str = new StringBuilder();
            String line = reader.readLine();
            while (line != null) {
                str.append(line);
                str.append("\n");
                line = reader.readLine();
            }
            data = str.toString();
        } catch (Exception e) {
            data = "No se encontro el archivo o existe algun error, intentelo nuevamente";
        }
        return data;
    }

    public boolean writeData(String data) {
        try ( BufferedWriter writer = new BufferedWriter(new FileWriter(myFile))){
            writer.write(data);
            return  true;
        }
        catch (Exception e) {
            return false;
        }
    }

}
