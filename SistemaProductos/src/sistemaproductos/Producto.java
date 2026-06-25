/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemaproductos;


public abstract class Producto {
    
    protected String codigo;
    protected String marca;
    protected String modelo;
    protected double precio;
    protected int stock;
    protected Proveedor proveedor;

    public Producto(String codigo, String marca, String modelo, double precio, int stock, Proveedor proveedor) {
        this.codigo = codigo;
        this.marca = marca;
        this.modelo = modelo;
        this.precio = precio;
        this.stock = stock;
        this.proveedor = proveedor;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public double getPrecio() {
        return precio;
    }

    public int getStock() {
        return stock;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }
    
    public double valorTotalStock(){
        return precio * stock;
    }

    @Override
    public String toString() {
        return codigo + " - " + marca + " " + modelo + " - $ " + precio + " - Stock: " + stock + " - Proveedor: " + proveedor;
    }
    
    
    
}
