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
/**
 *
 * @author df.sanabria761
 */
@Entity
public class AnfitrionEntity extends UsuarioEntity 
{
    @OneToMany(fetch =FetchType.EAGER,  mappedBy="anfitrion" )
    private List<ViviendaEntity> viviendas;
    
    @OneToMany(fetch =FetchType.EAGER,  mappedBy="anfitrion" )
    private List<ReservaEntity> reservas;
    

    public List<ViviendaEntity> getViviendas() {
        return viviendas;
    }
    public void setViviendas(List<ViviendaEntity> viviendas) {
        this.viviendas = viviendas;
    }
    
    
   
}
