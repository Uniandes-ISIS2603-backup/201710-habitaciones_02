/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.habitaciones.entities;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;

/**
 *
 * @author s.cortes
 */
@Entity
@PrimaryKeyJoinColumn(referencedColumnName="idUsuario")
public class ViajeroEntity extends UsuarioEntity
{
    //----------------------------------------------------------------------------------------------------
    // ATRIBUTOS
    //----------------------------------------------------------------------------------------------------
      
    /**
     * Relacion One-to-Many con las Reservas
     */
    @OneToMany(mappedBy = "viajero")
    private List<ReservaEntity> reservas;

    @OneToMany(mappedBy = "viajero")
    private List<ResenaEntity> resenas;
    
    //----------------------------------------------------------------------------------------------------
    // METODO
    //----------------------------------------------------------------------------------------------------

    /**
     * 
     * @return 
     */
    public List<ReservaEntity> getReservas() {
        return reservas;
    }

    /**
     * 
     * @param reservas 
     */
    public void setReservas(List<ReservaEntity> reservas) {
        this.reservas = reservas;
    }

    /**
     * 
     * @return 
     */
    public List<ResenaEntity> getResenas() {
        return resenas;
    }

    /**
     * 
     * @param resenas 
     */
    public void setResenas(List<ResenaEntity> resenas) {
        this.resenas = resenas;
    }
    
    
}
