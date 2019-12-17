/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.sql.Date;

/**
 *
 * @author ALUMNO1
 */
public class Ventas {

    private int id_venta;
    private Date fecha_venta;
    private int numero_venta;
    private Personas persona;
    private String estado_venta;
    private String condicion_venta;
    private int total;
    private int iva5;
    private int iva10;
    private int ivaexenta;
    private PedidosClientes pedidocliente;

    public Ventas() {
    }

    public Ventas(int id_venta, Date fecha_venta, int numero_venta, Personas persona, String estado_venta, String condicion_venta, int total, int iva5, int iva10, int ivaexenta, PedidosClientes pedidocliente) {
        this.id_venta = id_venta;
        this.fecha_venta = fecha_venta;
        this.numero_venta = numero_venta;
        this.persona = persona;
        this.estado_venta = estado_venta;
        this.condicion_venta = condicion_venta;
        this.total = total;
        this.iva5 = iva5;
        this.iva10 = iva10;
        this.ivaexenta = ivaexenta;
        this.pedidocliente = pedidocliente;
    }

    public int getId_venta() {
        return id_venta;
    }

    public void setId_venta(int id_venta) {
        this.id_venta = id_venta;
    }

    public Date getFecha_venta() {
        return fecha_venta;
    }

    public void setFecha_venta(Date fecha_venta) {
        this.fecha_venta = fecha_venta;
    }

    public int getNumero_venta() {
        return numero_venta;
    }

    public void setNumero_venta(int numero_venta) {
        this.numero_venta = numero_venta;
    }

    public Personas getPersona() {
        return persona;
    }

    public void setPersona(Personas persona) {
        this.persona = persona;
    }

    public String getEstado_venta() {
        return estado_venta;
    }

    public void setEstado_venta(String estado_venta) {
        this.estado_venta = estado_venta;
    }

    public String getCondicion_venta() {
        return condicion_venta;
    }

    public void setCondicion_venta(String condicion_venta) {
        this.condicion_venta = condicion_venta;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
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

    public PedidosClientes getPedidocliente() {
        return pedidocliente;
    }

    public void setPedidocliente(PedidosClientes pedidocliente) {
        this.pedidocliente = pedidocliente;
    }

   

}
