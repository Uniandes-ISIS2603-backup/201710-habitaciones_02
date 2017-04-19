/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.habitaciones.dtos;

import co.edu.uniandes.csw.habitaciones.entities.HabitacionEntity;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author b.gamba10
 */
@XmlRootElement
public class HabitacionDTO implements Serializable
{

    private Long id;
    private Double area;
    private Double valorAlquiler;
    private String rutaImagen;
    private String descripcion;
    private Integer capacidad;

    /**
     * Constructor
     */
    public HabitacionDTO()
    {

    }

    /**
     * Constructor con una habitacion por parametro
     *
     * @param entity
     */
    public HabitacionDTO(HabitacionEntity entity)
    {
        if (entity != null)
        {
            this.id = entity.getId();
            this.area = entity.getArea();
            this.valorAlquiler = entity.getValorAlquiler();
            this.descripcion = entity.getDescripcion();
            this.rutaImagen = entity.getRutaImagen();
            this.capacidad = entity.getCapacidad();
        }
    }

    /**
     * Pasa el objeto de DTO a Entidad
     *
     * @return
     */
    public HabitacionEntity toEntity()
    {
        HabitacionEntity entity = new HabitacionEntity();
        entity.setId(this.getId());
        entity.setArea(this.getArea());
        entity.setValorAlquiler(this.getValorAlquiler());
        entity.setDescripcion(this.getDescripcion());
        entity.setRutaImagen(this.getRutaImagen());
        entity.setCapacidad(this.getCapacidad());
        return entity;
    }

    /**
     * Retorna el id de la habitacion
     *
     * @return
     */
    public Long getId()
    {
        return id;
    }

    /**
     * Coloca el id de la habitacion
     *
     * @param id
     */
    public void setId(Long id)
    {
        this.id = id;
    }

    /**
     * Retorna el area de la habitacion
     *
     * @return
     */
    public Double getArea()
    {
        return area;
    }

    /**
     * Coloca el area de la habitacion
     *
     * @param area
     */
    public void setArea(Double area)
    {
        this.area = area;
    }

    /**
     * Retorna el valor del alquiler de la habitacion
     *
     * @return
     */
    public Double getValorAlquiler()
    {
        return valorAlquiler;
    }

    /**
     * Coloca el valor del alquiler de la habitacion
     *
     * @param valorAlquiler
     */
    public void setValorAlquiler(Double valorAlquiler)
    {
        this.valorAlquiler = valorAlquiler;
    }

    /**
     * Retorna la descripcion de la habitacion
     *
     * @return
     */
    public String getDescripcion()
    {
        return descripcion;
    }

    /**
     * Coloca la descripcion de la habitacion
     *
     * @param descripcion
     */
    public void setDescripcion(String descripcion)
    {
        this.descripcion = descripcion;
    }

    /**
     * Retorna la ruta de la imagen de la habitacion
     *
     * @return
     */
    public String getRutaImagen()
    {
        return rutaImagen;
    }

    /**
     * Coloca la ruta de la imagen de la habitacion
     *
     * @param rutaImagen
     */
    public void setRutaImagen(String rutaImagen)
    {
        this.rutaImagen = rutaImagen;
    }

    /**
     * Retorna la capacidad de la habitacion
     *
     * @return
     */
    public Integer getCapacidad()
    {
        return capacidad;
    }

    /**
     * Coloca la capacidad de la habitacion
     *
     * @param capacidad
     */
    public void setCapacidad(Integer capacidad)
    {
        this.capacidad = capacidad;
    }

}
