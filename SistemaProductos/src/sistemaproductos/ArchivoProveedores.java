/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemaproductos;

import java.io.*;
import java.util.*;

public class ArchivoProveedores {

    private static final String ARCHIVO = "src/sistemaproductos/proveedores.json";

    public static List<Proveedor> cargar() {
        List<Proveedor> lista = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO))) {
            String linea;
            int id = 0;
            String razonSocial = "";
            String telefono = "";
            String email = "";
            String ciudad = "";

            while ((linea = br.readLine()) != null) {
                linea = linea.trim();

                if (linea.startsWith("\"id\"")) {
                    id = Integer.parseInt(linea.split(":")[1].replace(",", "").trim());
                }

                if (linea.startsWith("\"razonSocial\"")) {
                    razonSocial = obtenerValor(linea);
                }

                if (linea.startsWith("\"telefono\"")) {
                    telefono = obtenerValor(linea);
                }

                if (linea.startsWith("\"email\"")) {
                    email = obtenerValor(linea);
                }

                if (linea.startsWith("\"ciudad\"")) {
                    ciudad = obtenerValor(linea);
                    lista.add(new Proveedor(id, razonSocial, telefono, email, ciudad));
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return lista;
    }

    private static String obtenerValor(String linea) {
        return linea.split(":")[1]
                .replace(",", "")
                .replace("\"", "")
                .trim();
    }
}
