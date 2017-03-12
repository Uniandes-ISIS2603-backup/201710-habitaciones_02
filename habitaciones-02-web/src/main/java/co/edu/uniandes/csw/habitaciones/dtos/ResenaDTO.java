/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.habitaciones.dtos;

import co.edu.uniandes.csw.habitaciones.entities.ResenaEntity;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author s.cortes
 */
@XmlRootElement
public class ResenaDTO implements Serializable
{
    //----------------------------------------------------------------------------------------------------
    // ATRIBUTOS
    //----------------------------------------------------------------------------------------------------
    
    private Double calificacion;
    
    private String comentario;
    
    //----------------------------------------------------------------------------------------------------
    // METODOS CONSTRUCTORES
    //----------------------------------------------------------------------------------------------------

    public ResenaDTO() 
    {
        
    }
    
    public ResenaDTO(ResenaEntity entity) 
    {
        if(entity != null)
        {
            calificacion = entity.getCalificacion();
            comentario = entity.getComentario();
        }
    }

    public Double getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Double calificacion) {
        this.calificacion = calificacion;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
    
    public ResenaEntity toEntity()
    {
        ResenaEntity entity = new ResenaEntity();
        entity.setCalificacion(this.getCalificacion());
        entity.setComentario(this.getComentario());
        return entity;
    }
    
}
