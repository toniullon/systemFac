/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import modelos.Tipopersonas;
import utiles.Conexion;
import utiles.Utiles;

/**
 *
 * @author Administrator
 */
public class TipopersonasControlador {
    public static boolean agregar (Tipopersonas tipopersona){
    boolean valor= false;
    if(Conexion.conectar ()){
        
        String sql = "insert into tipopersonas(nombre_tipopersona)"
                + "values('" + tipopersona.getNombre_tipopersona() + "')";

        try {
            Conexion.getSt().executeUpdate(sql);
            valor = true;
        } catch (SQLException ex) {
            System.err.println("Error:" + ex);
        }
    }
    return valor;
    
    
}
    public static Tipopersonas buscarId(Tipopersonas tipopersona){
        if (Conexion.conectar()){
            String sql = "select * from tipopersonas where id_tipopersona='" + tipopersona.getId_tipopersona() + "'";
            System.out.println("sql "+sql);
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()){
                    tipopersona.setId_tipopersona(rs.getInt("id_tipopersona"));
                    tipopersona.setNombre_tipopersona(rs.getString("nombre_tipopersona"));
                     }else{
                    tipopersona.setId_tipopersona(0);
                    tipopersona.setNombre_tipopersona("");
                    //return null;
                    //return tipopersona;
                }
            } catch (SQLException ex) {
                System.out.println("ERROR...." + ex);
            }
        }
        return tipopersona;
    }
    public static String buscarNombre(String nombre, int pagina){
        int offset = (pagina - 1)*Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()){
            try {
                String sql = "select * from tipopersonas where upper(nombre_tipopersona) like '%" +
                nombre.toUpperCase() + "%'" + "order by id_tipopersona offset " + offset + " limit " +
                        Utiles.REGISTROS_PAGINA;
                System.out.println("--->" + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)){
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_tipopersona") + "</td>"
                                + "<td>" + rs.getString("nombre_tipopersona") + "</td>"
                               
                                + "</tr>";
                    }
                    if (tabla.equals("")){
                        tabla = "<tr><td> colspan=2>No exixten registros...</td></tr>";
                    }
                    ps.close();
                    valor = tabla;
                } catch (SQLException ex) {
                    System.err.println("ERROR: " + ex);
                }
                Conexion.cerrar();
            } catch (Exception ex) {
                System.err.println("ERROR: " + ex);
            }
        }
        Conexion.cerrar();
        return valor;
    }
    public static boolean modificar(Tipopersonas tipopersona){
    boolean valor = false;
        if (Conexion.conectar()) {
       
             String sql= "update tipopersonas set nombre_tipopersona = '"+tipopersona.getNombre_tipopersona()+"'"
                    +" where id_tipopersona = "+ tipopersona.getId_tipopersona();
            System.out.println("sql"+sql);
            try {
           
            
            Conexion.getSt().executeUpdate(sql);
            valor= true;
        } catch (SQLException ex) {
            Logger.getLogger(TipopersonasControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        }
     return valor;
    }
    
    
     public static boolean eliminar(Tipopersonas tipopersona){
    boolean valor = false;
        if (Conexion.conectar()) {
         String sql="delete from tipopersonas where id_tipopersona = "+tipopersona.getId_tipopersona();
            
            try {
           
            
            
            Conexion.getSt().executeUpdate(sql);
            valor= true;
        } catch (SQLException ex) {
            Logger.getLogger(TipopersonasControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        }
     return valor;
    }
      public static Tipopersonas buscarTipopersona(Tipopersonas tipopersona) {

        if (Conexion.conectar()) {
            String sql = "select * from tipopersonas where nombre_tipopersona='" + tipopersona.getNombre_tipopersona() + "'";
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()) {

                    tipopersona.setId_tipopersona(0);

                } else {

                    tipopersona.setId_tipopersona(-1);

                    //return null;
                    //return tipopersona;
                }
            } catch (SQLException ex) {
                System.err.println("Error:" + ex);
            }
        }
        return tipopersona;
    }
}

