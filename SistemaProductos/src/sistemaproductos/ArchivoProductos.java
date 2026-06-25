/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemaproductos;

import java.io.*;
import java.util.List;
import javafx.collections.ObservableList;

public class ArchivoProductos {

    private static final String EXPORTADOS = "productos_caros.txt";

    public static void exportarCaros(ObservableList<Producto> productos) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(EXPORTADOS))) {

            for (Producto p : productos) {
                if (p.getPrecio() > 500000) {
                    pw.println(p);
                }
            }

            System.out.println("Productos exportados correctamente.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void verExportados() {
        File archivo = new File(EXPORTADOS);

        if (!archivo.exists()) {
            System.out.println("Todavía no se exportaron productos.");
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;

            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static final String ARCHIVO = "productos.csv";

    public static void guardar(ObservableList<Producto> productos) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(ARCHIVO))) {

            for (Producto p : productos) {
                if (p instanceof Electronico e) {
                    pw.println("Electronico;" + p.getCodigo() + ";" + p.getMarca()
                        + ";" + p.getModelo() + ";" + p.getPrecio()
                        + ";" + p.getStock() + ";" + p.getProveedor().getId()
                        + ";" + e.getGarantiaMeses());
                }

                if (p instanceof Alimenticio a) {
                    pw.println("Alimenticio;" + p.getCodigo() + ";" + p.getMarca()
                        + ";" + p.getModelo() + ";" + p.getPrecio()
                        + ";" + p.getStock() + ";" + p.getProveedor().getId()
                        + ";" + a.getFechaVencimiento());
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static java.util.List<Producto> cargar() {
        java.util.List<Producto> lista = new java.util.ArrayList<>();
        java.util.List<Proveedor> proveedores = ArchivoProveedores.cargar();

        File archivo = new File(ARCHIVO);

        if (!archivo.exists()) {
            return lista;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;

            while ((linea = br.readLine()) != null) {
                String[] d = linea.split(";");

                String tipo = d[0];
                String codigo = d[1];
                String marca = d[2];
                String modelo = d[3];
                double precio = Double.parseDouble(d[4]);
                int stock = Integer.parseInt(d[5]);
                int idProveedor = Integer.parseInt(d[6]);
                String extra = d[7];

                Proveedor proveedor = null;

                for (Proveedor p : proveedores) {
                    if (p.getId() == idProveedor) {
                        proveedor = p;
                    }
                }

                if ("Electronico".equals(tipo)) {
                    lista.add(new Electronico(
                        codigo, marca, modelo, precio, stock,
                        proveedor, Integer.parseInt(extra)));
                }

                if ("Alimenticio".equals(tipo)) {
                    lista.add(new Alimenticio(
                        codigo, marca, modelo, precio, stock,
                        proveedor, extra));
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return lista;
    }
    
    public static String leerExportados() {
        File archivo = new File(EXPORTADOS);

        if (!archivo.exists()) {
            return "Todavía no se exportaron productos.";
        }

        StringBuilder sb = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;

            while ((linea = br.readLine()) != null) {
                sb.append(linea).append("\n");
            }

        } catch (IOException e) {
            return "Error al leer el archivo.";
        }

        return sb.toString();
    }
}
