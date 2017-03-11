/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.habitaciones.entities;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
    @OneToMany(mappedBy = "viajero", fetch = FetchType.EAGER, targetEntity = ReservaEntity.class)
    private List<ReservaEntity> reservas;

    
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
    
    
}
