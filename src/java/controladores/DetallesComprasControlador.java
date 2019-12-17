/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelos.DetallesCompras;
import modelos.Productos;
import modelos.Compras;
import modelos.DetallesCompras;
import utiles.Conexion;

/**
 *
 * @author Administrador
 */
public class DetallesComprasControlador {

    public static DetallesCompras buscarId(int id) {
        DetallesCompras detallecompra = null;
        if (Conexion.conectar()) {
            try {
                String sql = "select * from detallecompras dv "
                        + "left join facturacompras v on v.id_compra=dv.id_compra "
                        + "left join productos p on p.id_producto=dv.id_producto "
                        + "where dv.id_detallecompra=?";
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ps.setInt(1, id);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        detallecompra = new DetallesCompras();
                        detallecompra.setId_detallecompra(rs.getInt("id_detallecompra"));
                        detallecompra.setCantidad_detallecompra(rs.getInt("cantidad_detallecompra"));
                        detallecompra.setPreciototal_detallecompra(rs.getInt("preciototal_detallecompra"));

                        Productos producto = new Productos();
                        producto.setId_producto(rs.getInt("id_producto"));
                        producto.setNombre_producto(rs.getString("nombre_producto"));
                        producto.setCodigo_producto(rs.getString("codigo_producto"));
                        producto.setPreciocompra_producto(rs.getInt("preciocompra_producto"));
                        producto.setIva_producto(rs.getInt("iva_producto"));

                        detallecompra.setProducto(producto);

                        Compras compra = new Compras();
                        compra.setId_compra(rs.getInt("id_compra"));
                        detallecompra.setCompra(compra);

                    }
                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return detallecompra;
    }


    public static boolean modificar(DetallesCompras detallecompra) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update detallecompras set id_producto= '" + detallecompra.getProducto().getId_producto()
               //     + "', id_compra= '" + detallecompra.getCompra().getId_compra()
                    + "', cantidad_detallecompra= '" + detallecompra.getCantidad_detallecompra()
                    + "', preciototal_detallecompra= '" + detallecompra.getPreciototal_detallecompra()
                    + "'" + " where id_detallecompra=" + detallecompra.getId_detallecompra();
           System.out.println("sql" + sql);
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch (SQLException ex) {
                System.out.println("Error: " + ex);
            }
        }
        return valor;
    }
    
    
     public static boolean eliminar(DetallesCompras detallecompra) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from detallecompras where id_detallecompra=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, detallecompra.getId_detallecompra());
                ps.executeUpdate();
                ps.close();
                Conexion.getConn().commit();
                valor = true;
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
                try {
                    Conexion.getConn().rollback();
                } catch (SQLException ex1) {
                    System.out.println("--> " + ex1.getLocalizedMessage());
                }
            }
        }
        Conexion.cerrar();
        return valor;
    }
}
