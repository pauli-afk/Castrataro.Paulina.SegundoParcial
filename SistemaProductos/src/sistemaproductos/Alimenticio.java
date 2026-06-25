/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemaproductos;


public class Alimenticio extends Producto {
    private String fechaVencimiento;
    
    public Alimenticio(String codigo, String marca, String modelo, double precio, int stock, Proveedor proveedor, String fechaVencimiento) {
        super(codigo, marca, modelo, precio, stock, proveedor);
        this.fechaVencimiento = fechaVencimiento;
    }
    
    public String getFechaVencimiento(){
        return fechaVencimiento;
    }
    
}
