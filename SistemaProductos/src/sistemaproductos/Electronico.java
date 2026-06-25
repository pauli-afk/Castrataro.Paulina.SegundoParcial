/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemaproductos;


public class Electronico extends Producto{
    private int garantiaMeses;

    public Electronico(String codigo, String marca, String modelo, double precio, int stock, Proveedor proveedor, int garantiaMeses) {
        super(codigo, marca, modelo, precio, stock, proveedor);
        this.garantiaMeses = garantiaMeses;
    }
    
    public int getGarantiaMeses(){
        return garantiaMeses;
    }
       
}
