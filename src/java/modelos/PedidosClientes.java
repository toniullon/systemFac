/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.sql.Date;

/**
 *
 * @author Administrator
 */
public class PedidosClientes {
    private int id_pedidocliente;
    private Date fecha_pedidov;
    private String estado_pedidov;

    public PedidosClientes() {
    }

    public PedidosClientes(int id_pedidocliente, Date fecha_pedidov, String estado_pedidov) {
        this.id_pedidocliente = id_pedidocliente;
        this.fecha_pedidov = fecha_pedidov;
        this.estado_pedidov = estado_pedidov;
    }

    public int getId_pedidocliente() {
        return id_pedidocliente;
    }

    public void setId_pedidocliente(int id_pedidocliente) {
        this.id_pedidocliente = id_pedidocliente;
    }

    public Date getFecha_pedidov() {
        return fecha_pedidov;
    }

    public void setFecha_pedidov(Date fecha_pedidov) {
        this.fecha_pedidov = fecha_pedidov;
    }

    public String getEstado_pedidov() {
        return estado_pedidov;
    }

    public void setEstado_pedidov(String estado_pedidov) {
        this.estado_pedidov = estado_pedidov;
    }

    
}
