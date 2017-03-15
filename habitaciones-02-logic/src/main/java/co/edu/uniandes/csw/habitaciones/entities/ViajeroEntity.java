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

    /**
     * Relacion One-to-Many con las reseñas
     */
    @OneToMany(mappedBy = "viajero")
    private List<ResenaEntity> resenas;
    
    //----------------------------------------------------------------------------------------------------
    // METODO
    //----------------------------------------------------------------------------------------------------

    /**
     * Retorna las resevas del viajero
     * @return lista de reservas
     */
    public List<ReservaEntity> getReservas() {
        return reservas;
    }

    /**
     * asigna las reservas del viajero
     * @param reservas la lista de reservas a asignar
     */
    public void setReservas(List<ReservaEntity> reservas) {
        this.reservas = reservas;
    }

    /**
     * retorna la lista de reseñas
     * @return lista de reseñas
     */
    public List<ResenaEntity> getResenas() {
        return resenas;
    }

    /**
     * asigna las reseñas del viajero
     * @param resenas la lista de las reseñas a asignar
     */
    public void setResenas(List<ResenaEntity> resenas) {
        this.resenas = resenas;
    }
    
    
}
