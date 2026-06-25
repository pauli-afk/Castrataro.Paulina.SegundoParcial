/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package sistemaproductos;

import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ViewController {

    @FXML
    private ListView<Producto> listaProductos;

    private ObservableList<Producto> productos =
            FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        listaProductos.setItems(productos);
        productos.addAll(ArchivoProductos.cargar());
    }

    @FXML
    private void agregarProducto() {
        Producto nuevo = abrirModal(null);

        if (nuevo != null) {
            try {
                validarCodigoRepetido(nuevo);
                productos.add(nuevo);
                ArchivoProductos.guardar(productos);
            } catch (CodigoRepetidoException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    @FXML
    private void modificarProducto() {
        Producto seleccionado =
            listaProductos.getSelectionModel().getSelectedItem();

        if (seleccionado != null) {
            Producto modificado = abrirModal(seleccionado);

            if (modificado != null) {
                int indice = productos.indexOf(seleccionado);
                productos.set(indice, modificado);
                ArchivoProductos.guardar(productos);
            }
        }
    }

    @FXML
    private void eliminarProducto() {
        Producto seleccionado =
            listaProductos.getSelectionModel().getSelectedItem();

        if (seleccionado != null) {
            productos.remove(seleccionado);
            ArchivoProductos.guardar(productos);
        }
    }

    @FXML
    private void exportarCaros() {
        ArchivoProductos.exportarCaros(productos);
    }

    @FXML
    private void verExportados() {
        String contenido = ArchivoProductos.leerExportados();
        abrirVentanaTexto("Productos exportados", contenido);
    }

    @FXML
    private void verTotalInventario() {
        double total = 0;

        for (Producto p : productos) {
            total += p.valorTotalStock();
        }

        abrirVentanaTexto(
                "Total inventario",
                "El valor total del inventario es: $" + total
        );
    }
    
    private void validarCodigoRepetido(Producto nuevo)
        throws CodigoRepetidoException {

    for (Producto p : productos) {
        if (p.getCodigo().equalsIgnoreCase(nuevo.getCodigo())) {
            throw new CodigoRepetidoException(
                    "Ya existe un producto con ese código.");
        }
    }
    
}

    private Producto abrirModal(Producto producto) {
        try {
            FXMLLoader loader =
                    new FXMLLoader(getClass().getResource("modalProducto.fxml"));

            Scene scene = new Scene(loader.load());

            Stage stage = new Stage();

            ModalProductoController controller = loader.getController();
            controller.setStage(stage);
            
            if (producto != null) {
                controller.cargarProducto(producto);
            }

            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();

            return controller.getProducto();

        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    private void abrirVentanaTexto(String titulo, String contenido) {
        try {
            FXMLLoader loader =
                    new FXMLLoader(getClass().getResource("ventanaTexto.fxml"));

            Scene scene = new Scene(loader.load());

            Stage stage = new Stage();

            VentanaTextoController controller = loader.getController();
            controller.setStage(stage);
            controller.setTexto(contenido);

            stage.setTitle(titulo);
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
