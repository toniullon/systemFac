/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

/**
 *
 * @author Administrator
 */
public class DetallesPedidos {
    private int id_detallepedidoc;
    private int cantidad_detallepedidoc;
    private Productos producto;
    private PedidosClientes pedidocliente;
    private int preciototal_detallepedidoc;

    public DetallesPedidos() {
    }

    public DetallesPedidos(int id_detallepedidoc, int cantidad_detallepedidoc, Productos producto, PedidosClientes pedidocliente, int preciototal_detallepedidoc) {
        this.id_detallepedidoc = id_detallepedidoc;
        this.cantidad_detallepedidoc = cantidad_detallepedidoc;
        this.producto = producto;
        this.pedidocliente = pedidocliente;
        this.preciototal_detallepedidoc = preciototal_detallepedidoc;
    }

    public int getId_detallepedidoc() {
        return id_detallepedidoc;
    }

    public void setId_detallepedidoc(int id_detallepedidoc) {
        this.id_detallepedidoc = id_detallepedidoc;
    }

    public int getCantidad_detallepedidoc() {
        return cantidad_detallepedidoc;
    }

    public void setCantidad_detallepedidoc(int cantidad_detallepedidoc) {
        this.cantidad_detallepedidoc = cantidad_detallepedidoc;
    }

    public Productos getProducto() {
        return producto;
    }

    public void setProducto(Productos producto) {
        this.producto = producto;
    }

    public PedidosClientes getPedidocliente() {
        return pedidocliente;
    }

    public void setPedidocliente(PedidosClientes pedidocliente) {
        this.pedidocliente = pedidocliente;
    }

    public int getPreciototal_detallepedidoc() {
        return preciototal_detallepedidoc;
    }

    public void setPreciototal_detallepedidoc(int preciototal_detallepedidoc) {
        this.preciototal_detallepedidoc = preciototal_detallepedidoc;
    }
   
    
}

