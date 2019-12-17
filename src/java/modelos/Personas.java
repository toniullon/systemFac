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
public class Personas {
    private int id_persona;
    private String nombre_persona;
    private String apellido_persona;
    private String direccion_persona;
    private String telefono_persona;
    private String correo_persona;
    private String ruc_persona;
    private Ciudades ciudad;
    private Tipopersonas tipopersona;
    private Estados_Civiles estadocivil;

    public Personas() {
    }

    public Personas(int id_persona, String nombre_persona, String apellido_persona, String direccion_persona, String telefono_persona, String correo_persona, String ruc_persona, Ciudades ciudad, Tipopersonas tipopersona, Estados_Civiles estadocivil) {
        this.id_persona = id_persona;
        this.nombre_persona = nombre_persona;
        this.apellido_persona = apellido_persona;
        this.direccion_persona = direccion_persona;
        this.telefono_persona = telefono_persona;
        this.correo_persona = correo_persona;
        this.ruc_persona = ruc_persona;
        this.ciudad = ciudad;
        this.tipopersona = tipopersona;
        this.estadocivil = estadocivil;
    }

    public int getId_persona() {
        return id_persona;
    }

    public void setId_persona(int id_persona) {
        this.id_persona = id_persona;
    }

    public String getNombre_persona() {
        return nombre_persona;
    }

    public void setNombre_persona(String nombre_persona) {
        this.nombre_persona = nombre_persona;
    }

    public String getApellido_persona() {
        return apellido_persona;
    }

    public void setApellido_persona(String apellido_persona) {
        this.apellido_persona = apellido_persona;
    }

    public String getDireccion_persona() {
        return direccion_persona;
    }

    public void setDireccion_persona(String direccion_persona) {
        this.direccion_persona = direccion_persona;
    }

    public String getTelefono_persona() {
        return telefono_persona;
    }

    public void setTelefono_persona(String telefono_persona) {
        this.telefono_persona = telefono_persona;
    }

    public String getCorreo_persona() {
        return correo_persona;
    }

    public void setCorreo_persona(String correo_persona) {
        this.correo_persona = correo_persona;
    }

    public String getRuc_persona() {
        return ruc_persona;
    }

    public void setRuc_persona(String ruc_persona) {
        this.ruc_persona = ruc_persona;
    }

    public Ciudades getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudades ciudad) {
        this.ciudad = ciudad;
    }

    public Tipopersonas getTipopersona() {
        return tipopersona;
    }

    public void setTipopersona(Tipopersonas tipopersona) {
        this.tipopersona = tipopersona;
    }

    public Estados_Civiles getEstadocivil() {
        return estadocivil;
    }

    public void setEstadocivil(Estados_Civiles estadocivil) {
        this.estadocivil = estadocivil;
    }
    
    
}
