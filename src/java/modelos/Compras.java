/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.sql.Date;

/**
 *
 * @author Administrador
 */
public class Compras {
     private int id_compra;
    private Date fecha_compra;
    private int numero_compra;
    private String estado_compra;
    private String condicion_compra;
    private int total;
    private PedidosProveedores pedidoproveedor;
    private int iva5;
    private int iva10;
    private int ivaexenta;

    public Compras() {
    }

    public Compras(int id_compra, Date fecha_compra, int numero_compra, String estado_compra, String condicion_compra, int total, PedidosProveedores pedidoproveedor, int iva5, int iva10, int ivaexenta) {
        this.id_compra = id_compra;
        this.fecha_compra = fecha_compra;
        this.numero_compra = numero_compra;
        this.estado_compra = estado_compra;
        this.condicion_compra = condicion_compra;
        this.total = total;
        this.pedidoproveedor = pedidoproveedor;
        this.iva5 = iva5;
        this.iva10 = iva10;
        this.ivaexenta = ivaexenta;
    }

    public int getId_compra() {
        return id_compra;
    }

    public void setId_compra(int id_compra) {
        this.id_compra = id_compra;
    }

    public Date getFecha_compra() {
        return fecha_compra;
    }

    public void setFecha_compra(Date fecha_compra) {
        this.fecha_compra = fecha_compra;
    }

    public int getNumero_compra() {
        return numero_compra;
    }

    public void setNumero_compra(int numero_compra) {
        this.numero_compra = numero_compra;
    }

    public String getEstado_compra() {
        return estado_compra;
    }

    public void setEstado_compra(String estado_compra) {
        this.estado_compra = estado_compra;
    }

    public String getCondicion_compra() {
        return condicion_compra;
    }

    public void setCondicion_compra(String condicion_compra) {
        this.condicion_compra = condicion_compra;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public PedidosProveedores getPedidoproveedor() {
        return pedidoproveedor;
    }

    public void setPedidoproveedor(PedidosProveedores pedidoproveedor) {
        this.pedidoproveedor = pedidoproveedor;
    }

    public int getIva5() {
        return iva5;
    }

    public void setIva5(int iva5) {
        this.iva5 = iva5;
    }

    public int getIva10() {
        return iva10;
    }

    public void setIva10(int iva10) {
        this.iva10 = iva10;
    }

    public int getIvaexenta() {
        return ivaexenta;
    }

    public void setIvaexenta(int ivaexenta) {
        this.ivaexenta = ivaexenta;
    }

 
}
