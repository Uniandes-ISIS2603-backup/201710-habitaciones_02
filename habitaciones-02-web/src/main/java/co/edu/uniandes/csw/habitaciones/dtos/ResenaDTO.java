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
    
    /**
     * El ID de la reseña
     */
    private Long id;
    
    /**
     * Calificacion de la reseña
     */
    private Double calificacion;
    
    /**
     * comentario de la reseña
     */
    private String comentario;
    
    //----------------------------------------------------------------------------------------------------
    // METODOS CONSTRUCTORES
    //----------------------------------------------------------------------------------------------------

    /**
     * Constructor default del DTO
     */
    public ResenaDTO() 
    {
        //constructor vacio
    }
    
    /**
     * Metodo constructor
     * @param entity entity de la reseña
     */
    public ResenaDTO(ResenaEntity entity) 
    {
        if(entity != null)
        {
            calificacion = entity.getCalificacion();
            comentario = entity.getComentario();
            id = entity.getId();
        }
    }

    /**
     * restorna el id de la reseña
     * @return id de la reseña
     */
    public Long getId() {
        return id;
    }

    /**
     * Le asigna un id a la reseña
     * @param id el id a asignar
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * retorna la calificacion de la reseña
     * @return calificacion de la reseña
     */
    public Double getCalificacion() {
        return calificacion;
    }

    /**
     * asiga una calificacion a la reseña
     * @param calificacion la nueva calificacion 
     */
    public void setCalificacion(Double calificacion) {
        this.calificacion = calificacion;
    }

    /**
     * retorna el comentario de la reseña
     * @return el comentario de la reseña
     */
    public String getComentario() {
        return comentario;
    }

     /**
     * asigna un comentario a la reseña
     * @param comentario el nuevo comentario
     */
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
    
    /**
     * Metodo que convierte un DTO a un Entity
     * @return la entidad de la reseña
     */
    public ResenaEntity toEntity()
    {
        ResenaEntity entity = new ResenaEntity();
        entity.setCalificacion(this.getCalificacion());
        entity.setComentario(this.getComentario());
        entity.setId(id);
        return entity;
    }
    
}
