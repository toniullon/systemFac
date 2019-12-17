/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelos.Depositos;
import utiles.Conexion;
import utiles.Utiles;

/**
 *
 * @author usuario
 */
public class DepositosControlador {
    public static boolean agregar(Depositos deposito){
        boolean valor=false;
        if (Conexion.conectar()) {
            String sql = "insert into depositos(nombre_deposito) " + "values('" + deposito.getNombre_deposito() + "')" ;
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch(SQLException ex) {
                System.err.println("Error:"+ex);
            }
        }
        return valor;
    }
    
    public static Depositos buscarId(Depositos deposito){
        
        if (Conexion.conectar()) {
            String sql = "select * from depositos where id_deposito ='" + deposito.getId_deposito() + "'";
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()) {
                    deposito.setId_deposito(rs.getInt("id_deposito"));
                    deposito.setNombre_deposito(rs.getString("nombre_deposito"));
                }else {
                    deposito.setId_deposito(0);
                    deposito.setNombre_deposito("");
                    //return null;
                    //return deposito;
                }
            } catch(SQLException ex) {
                System.err.println("Error:"+ex);
            }
        }
        return deposito;
    }
    
     public static String buscarNombre(String nombre, int pagina) {
      
        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            
            try {
                  System.out.println(nombre);
                String sql = "select * from depositos where upper(nombre_deposito) like '%" +
                        nombre.toUpperCase() + "%'"
                        + "order by id_deposito offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;
              
                System.out.println("--->" + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_deposito") + "</td>"
                                + "<td>" + rs.getString("nombre_deposito") + "</td>"
                                + "</tr>";
                    }   
                    if (tabla.equals("")) {
                        tabla = "<tr><td colspan=2> No existen registros...</td></tr>";
                    }
                    ps.close();
                    valor = tabla;
                } catch (SQLException ex) {
                    System.err.println("Error: " + ex);
                }
                Conexion.cerrar();
            } catch (Exception ex) {
                System.err.println("Error: " + ex);
            }
        }
        Conexion.cerrar();
        return valor;
    }
    
    public static boolean modificar(Depositos deposito) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update depositos set nombre_deposito= '" + deposito.getNombre_deposito() + "'" + " where id_deposito=" + deposito.getId_deposito();
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch (SQLException ex) {
                System.out.println("Error: " + ex);
            }
        }
        return valor;
    }
    
    public static boolean eliminar(Depositos deposito) {
        boolean valor =false;
        if (Conexion.conectar()) {
            String sql = "delete from depositos where id_deposito=" + deposito.getId_deposito();
            try {
                Conexion.getSt().executeUpdate(sql);
                valor=true;
            } catch (SQLException ex) {
                System.out.println("Error:" + ex);
            }
        }
        return valor;
    }
    
     public static Depositos buscarDepositos(Depositos deposito) {

        if (Conexion.conectar()) {
            String sql = "select * from depositos where nombre_deposito='" + deposito.getNombre_deposito() + "'";
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()) {

                    deposito.setId_deposito(0);

                } else {

                    deposito.setId_deposito(-1);

                    //return null;
                    //return deposito;
                }
            } catch (SQLException ex) {
                System.err.println("Error:" + ex);
            }
        }
        return deposito;
    }
}
