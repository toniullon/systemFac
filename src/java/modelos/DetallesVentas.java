/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

public class DetallesVentas {

    private int id_detalleventa;
    private Ventas venta;
    private Productos producto;
    private int cantidad_detalleventa;
    private int preciototal_detalleventa;

    public DetallesVentas(int id_detalleventa, Ventas venta, Productos producto, int cantidad_detalleventa, int preciototal_detalleventa) {
        this.id_detalleventa = id_detalleventa;
        this.venta = venta;
        this.producto = producto;
        this.cantidad_detalleventa = cantidad_detalleventa;
        this.preciototal_detalleventa = preciototal_detalleventa;
    }

    public DetallesVentas() {
    }

    public int getId_detalleventa() {
        return id_detalleventa;
    }

    public void setId_detalleventa(int id_detalleventa) {
        this.id_detalleventa = id_detalleventa;
    }

    public Ventas getVenta() {
        return venta;
    }

    public void setVenta(Ventas venta) {
        this.venta = venta;
    }

    public Productos getProducto() {
        return producto;
    }

    public void setProducto(Productos producto) {
        this.producto = producto;
    }

    public int getCantidad_detalleventa() {
        return cantidad_detalleventa;
    }

    public void setCantidad_detalleventa(int cantidad_detalleventa) {
        this.cantidad_detalleventa = cantidad_detalleventa;
    }

    public int getPreciototal_detalleventa() {
        return preciototal_detalleventa;
    }

    public void setPreciototal_detalleventa(int preciototal_detalleventa) {
        this.preciototal_detalleventa = preciototal_detalleventa;
    }

  
}
