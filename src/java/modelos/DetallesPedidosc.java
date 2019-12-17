/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

/**
 *
 * @author usuario
 */
public class DetallesPedidosc {

    private int id_detallepedidop;
    private int cantidad_detallepedidop;
    private Productos producto;
    private PedidosProveedores pedidoproveedor;
    private int preciototal_detallepedidop;
     private int cantidad_entrante;
    private int cantidad_faltante;
    private String estado_pedidop;

    public DetallesPedidosc() {
    }

    public DetallesPedidosc(int id_detallepedidop, int cantidad_detallepedidop, Productos producto, PedidosProveedores pedidoproveedor, int preciototal_detallepedidop, int cantidad_entrante, int cantidad_faltante, String estado_pedidop) {
        this.id_detallepedidop = id_detallepedidop;
        this.cantidad_detallepedidop = cantidad_detallepedidop;
        this.producto = producto;
        this.pedidoproveedor = pedidoproveedor;
        this.preciototal_detallepedidop = preciototal_detallepedidop;
        this.cantidad_entrante = cantidad_entrante;
        this.cantidad_faltante = cantidad_faltante;
        this.estado_pedidop = estado_pedidop;
    }

    public int getId_detallepedidop() {
        return id_detallepedidop;
    }

    public void setId_detallepedidop(int id_detallepedidop) {
        this.id_detallepedidop = id_detallepedidop;
    }

    public int getCantidad_detallepedidop() {
        return cantidad_detallepedidop;
    }

    public void setCantidad_detallepedidop(int cantidad_detallepedidop) {
        this.cantidad_detallepedidop = cantidad_detallepedidop;
    }

    public Productos getProducto() {
        return producto;
    }

    public void setProducto(Productos producto) {
        this.producto = producto;
    }

    public PedidosProveedores getPedidoproveedor() {
        return pedidoproveedor;
    }

    public void setPedidoproveedor(PedidosProveedores pedidoproveedor) {
        this.pedidoproveedor = pedidoproveedor;
    }

    public int getPreciototal_detallepedidop() {
        return preciototal_detallepedidop;
    }

    public void setPreciototal_detallepedidop(int preciototal_detallepedidop) {
        this.preciototal_detallepedidop = preciototal_detallepedidop;
    }

    public int getCantidad_entrante() {
        return cantidad_entrante;
    }

    public void setCantidad_entrante(int cantidad_entrante) {
        this.cantidad_entrante = cantidad_entrante;
    }

    public int getCantidad_faltante() {
        return cantidad_faltante;
    }

    public void setCantidad_faltante(int cantidad_faltante) {
        this.cantidad_faltante = cantidad_faltante;
    }

    public String getEstado_pedidop() {
        return estado_pedidop;
    }

    public void setEstado_pedidop(String estado_pedidop) {
        this.estado_pedidop = estado_pedidop;
    }

    
}
