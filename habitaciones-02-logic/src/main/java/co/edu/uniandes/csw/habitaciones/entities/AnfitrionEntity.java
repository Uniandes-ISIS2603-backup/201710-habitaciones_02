/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.habitaciones.entities;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
/**
 *
 * @author df.sanabria761
 */
@Entity
@PrimaryKeyJoinColumn(referencedColumnName="idUsuario")
public class AnfitrionEntity extends UsuarioEntity 
{
    @OneToMany( mappedBy="anfitrion" )
    private List<ViviendaEntity> viviendas;
    
    @OneToMany( mappedBy="anfitrion" )
    private List<ReservaEntity> reservas;
    
    @OneToMany(mappedBy = "viajero")
    private List<ResenaEntity> resenas;

    public List<ReservaEntity> getReservas() {
        return reservas;
    }

    public void setReservas(List<ReservaEntity> reservas) {
        this.reservas = reservas;
    }


    public List<ViviendaEntity> getViviendas() {
        return viviendas;
    }
    public void setViviendas(List<ViviendaEntity> viviendas) {
        this.viviendas = viviendas;
    }

    public List<ResenaEntity> getResenas() {
        return resenas;
    }

    public void setResenas(List<ResenaEntity> resenas) {
        this.resenas = resenas;
    }
    
    
   
}
