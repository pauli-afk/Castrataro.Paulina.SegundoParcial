/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package sistemaproductos;

import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ModalProductoController {

    @FXML private ComboBox<String> cmbTipo;
    @FXML private ComboBox<Proveedor> cmbProveedor;

    @FXML private TextField txtCodigo;
    @FXML private TextField txtMarca;
    @FXML private TextField txtModelo;
    @FXML private TextField txtPrecio;
    @FXML private TextField txtStock;
    @FXML private TextField txtExtra;

    @FXML private Label lblExtra;

    private Stage stage;
    private Producto producto;

    public void setStage(Stage stage) {
        this.stage = stage;
    }
    
    public void cargarProducto(Producto producto) {
        this.producto = producto;

        txtCodigo.setText(producto.getCodigo());
        txtCodigo.setDisable(true);

        txtMarca.setText(producto.getMarca());
        txtModelo.setText(producto.getModelo());
        txtPrecio.setText(String.valueOf(producto.getPrecio()));
        txtStock.setText(String.valueOf(producto.getStock()));

        cmbProveedor.setValue(producto.getProveedor());

        if (producto instanceof Electronico e) {
            cmbTipo.setValue("Electronico");
            lblExtra.setText("Garantía (meses)");
            txtExtra.setText(String.valueOf(e.getGarantiaMeses()));
        }

        if (producto instanceof Alimenticio a) {
            cmbTipo.setValue("Alimenticio");
            lblExtra.setText("Fecha vencimiento");
            txtExtra.setText(a.getFechaVencimiento());
        }
    }

    public Producto getProducto() {
        return producto;
    }

    @FXML
    public void initialize() {

        cmbTipo.getItems().addAll("Electronico", "Alimenticio");

        List<Proveedor> proveedores = ArchivoProveedores.cargar();
        cmbProveedor.getItems().addAll(proveedores);

        cmbTipo.setOnAction(e -> {

            if ("Electronico".equals(cmbTipo.getValue())) {
                lblExtra.setText("Garantía (meses)");
            } else {
                lblExtra.setText("Fecha vencimiento");
            }

        });

    }

    @FXML
    private void guardar() {

        String codigo = txtCodigo.getText();
        String marca = txtMarca.getText();
        String modelo = txtModelo.getText();

        double precio = Double.parseDouble(txtPrecio.getText());
        int stock = Integer.parseInt(txtStock.getText());

        Proveedor proveedor = cmbProveedor.getValue();

        if ("Electronico".equals(cmbTipo.getValue())) {

            int garantia = Integer.parseInt(txtExtra.getText());

            producto = new Electronico(
                    codigo,
                    marca,
                    modelo,
                    precio,
                    stock,
                    proveedor,
                    garantia);

        } else {

            producto = new Alimenticio(
                    codigo,
                    marca,
                    modelo,
                    precio,
                    stock,
                    proveedor,
                    txtExtra.getText());

        }

        stage.close();

    }

    @FXML
    private void cancelar() {

        producto = null;
        stage.close();

    }
}
