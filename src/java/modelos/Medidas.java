/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

/**
 *
 * @author ALUMNO1
 */
public class Medidas {
    private int id_medida;
    private String nombre_medida;

    public Medidas() {
    }

    public Medidas(int id_medida, String nombre_medida) {
        this.id_medida = id_medida;
        this.nombre_medida = nombre_medida;
    }

    public int getId_medida() {
        return id_medida;
    }

    public void setId_medida(int id_medida) {
        this.id_medida = id_medida;
    }

    public String getNombre_medida() {
        return nombre_medida;
    }

    public void setNombre_medida(String nombre_medida) {
        this.nombre_medida = nombre_medida;
    }

  
    
    
}
