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
public class HabitacionDTO implements Serializable {
    
    private Long id;
    private Double area;
    private Double valorAlquiler;
    private String rutaImagen;
    private String descripcion;
    
    public HabitacionDTO()
    {
        
    }
    
    public HabitacionDTO(HabitacionEntity entity)
    {
        if(entity != null)
        {
            this.id = entity.getId();
            this.area = entity.getArea();
            this.valorAlquiler = entity.getValorAlquiler();
            this.descripcion = entity.getDescripcion();
            this.rutaImagen = entity.getRutaImagen();
        }
    }
    
    public HabitacionEntity toEntity()
    {
        HabitacionEntity entity = new HabitacionEntity();
        entity.setId(this.getId());
        entity.setArea(this.getArea());
        entity.setValorAlquiler(this.getValorAlquiler());
        entity.setDescripcion(this.getDescripcion());
        entity.setRutaImagen(this.getRutaImagen());
        return entity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public Double getValorAlquiler() {
        return valorAlquiler;
    }

    public void setValorAlquiler(Double valorAlquiler) {
        this.valorAlquiler = valorAlquiler;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getRutaImagen() {
        return rutaImagen;
    }

    public void setRutaImagen(String rutaImagen) {
        this.rutaImagen = rutaImagen;
    }
    
    
}
