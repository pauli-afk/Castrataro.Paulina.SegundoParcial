/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package sistemaproductos;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class VentanaTextoController {

    @FXML
    private TextArea txtContenido;

    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setTexto(String texto) {
        txtContenido.setText(texto);
    }

    @FXML
    private void cerrar() {
        stage.close();
    }
}
