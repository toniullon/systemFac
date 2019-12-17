/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.sql.Date;

/**
 *
 * @author usuario
 */
public class PedidosProveedores {

    private int id_pedidoproveedor;
    private Date fecha_pedidop;
    private String estado_pedidop;
    private Personas persona;

    public PedidosProveedores() {
    }

    public PedidosProveedores(int id_pedidoproveedor, Date fecha_pedidop, String estado_pedidop, Personas persona) {
        this.id_pedidoproveedor = id_pedidoproveedor;
        this.fecha_pedidop = fecha_pedidop;
        this.estado_pedidop = estado_pedidop;
        this.persona = persona;
    }

    public int getId_pedidoproveedor() {
        return id_pedidoproveedor;
    }

    public void setId_pedidoproveedor(int id_pedidoproveedor) {
        this.id_pedidoproveedor = id_pedidoproveedor;
    }

    public Date getFecha_pedidop() {
        return fecha_pedidop;
    }

    public void setFecha_pedidop(Date fecha_pedidop) {
        this.fecha_pedidop = fecha_pedidop;
    }

    public String getEstado_pedidop() {
        return estado_pedidop;
    }

    public void setEstado_pedidop(String estado_pedidop) {
        this.estado_pedidop = estado_pedidop;
    }

    public Personas getPersona() {
        return persona;
    }

    public void setPersona(Personas persona) {
        this.persona = persona;
    }
    
    
}
