/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

/**
 *
 * @author Administrador
 */
public class DetallesCompras {
   private int id_detallecompra;
    private Compras compra;
    private Productos producto;
    private int cantidad_detallecompra;
    private int preciototal_detallecompra;

    public DetallesCompras(int id_detallecompra, Compras compra, Productos producto, int cantidad_detallecompra, int preciototal_detallecompra) {
        this.id_detallecompra = id_detallecompra;
        this.compra = compra;
        this.producto = producto;
        this.cantidad_detallecompra = cantidad_detallecompra;
        this.preciototal_detallecompra = preciototal_detallecompra;
    }

    public DetallesCompras() {
    }

    public int getId_detallecompra() {
        return id_detallecompra;
    }

    public void setId_detallecompra(int id_detallecompra) {
        this.id_detallecompra = id_detallecompra;
    }

    public Compras getCompra() {
        return compra;
    }

    public void setCompra(Compras compra) {
        this.compra = compra;
    }

    public Productos getProducto() {
        return producto;
    }

    public void setProducto(Productos producto) {
        this.producto = producto;
    }

    public int getCantidad_detallecompra() {
        return cantidad_detallecompra;
    }

    public void setCantidad_detallecompra(int cantidad_detallecompra) {
        this.cantidad_detallecompra = cantidad_detallecompra;
    }

    public int getPreciototal_detallecompra() {
        return preciototal_detallecompra;
    }

    public void setPreciototal_detallecompra(int preciototal_detallecompra) {
        this.preciototal_detallecompra = preciototal_detallecompra;
    }

   
}
