/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.habitaciones.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


/**
 *
 * @author b.gamba10
 */
@Entity
public class HabitacionEntity implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Double area;
    
    private Double valorAlquiler;
    
    private String rutaImagen;
    
    private String descripcion;
    
    private Integer capacidad;
    
    @OneToMany(mappedBy = "habitacion")
    private List<DisponibilidadEntity> disponibilidades;
    
    @OneToMany(mappedBy = "habitacion")
    private List<ReservaEntity> reservas;
    
    @OneToMany(mappedBy = "habitacion")
    private List<ResenaEntity> resenas;
    
    @ManyToOne
    private ViviendaEntity vivienda;

    /**
     * Retorna el id de la habitacion
     * @return 
     */
    public Long getId() {
        return id;
    }

    /**
     * Coloca el id de la habitacion
     * @param id 
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Retorna el area de la habitacion
     * @return 
     */
    public Double getArea() {
        return area;
    }

    /**
     * Coloca el area de la habitacion
     * @param area 
     */
    public void setArea(Double area) {
        this.area = area;
    }

    /**
     * Retorna el valor del alquiler de la habitacion
     * @return 
     */
    public Double getValorAlquiler() {
        return valorAlquiler;
    }

    /**
     * Coloca el valor del alquiler de la habitacion
     * @param valorAlquiler 
     */
    public void setValorAlquiler(Double valorAlquiler) {
        this.valorAlquiler = valorAlquiler;
    }

    /**
     * Retorna la descripcion de la habitacion 
     * @return 
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Coloca la descripcion de la habitacion     
     * @param descripcion 
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Retorna la ruta de la imagen de la habitacion
     * @return 
     */
    public String getRutaImagen() {
        return rutaImagen;
    }

    /**
     * Coloca la ruta de la imagen de la habitacion
     * @param rutaImagen 
     */
    public void setRutaImagen(String rutaImagen) {
        this.rutaImagen = rutaImagen;
    }

    /**
     * Retorna una lista con las disponibilidades 
     * @return 
     */
    public List<DisponibilidadEntity> getDisponibilidades() {
        return disponibilidades;
    }

    /**
     * Coloca una lista con las disponibilidades 
     * @param disponibilidades 
     */
    public void setDisponibilidades(List<DisponibilidadEntity> disponibilidades) {
        this.disponibilidades = disponibilidades;
    }

    /**
     * Retorna una lista con las reservas 
     * @return 
     */
    public List<ReservaEntity> getReservas() {
        return reservas;
    }

    /**
     * Coloca una lista con las reservas
     * @param reservas 
     */
    public void setReservas(List<ReservaEntity> reservas) {
        this.reservas = reservas;
    }

    /**
     * Retorna una vivienda
     * @return 
     */
    public ViviendaEntity getVivienda() {
        return vivienda;
    }

    /**
     * Coloca una vivienda
     * @param vivienda 
     */
    public void setVivienda(ViviendaEntity vivienda) {
        this.vivienda = vivienda;
    }

    /**
     * Retorna las resenas de una habitacion
     * @return 
     */
    public List<ResenaEntity> getResenas() {
        return resenas;
    }

    /**
     * Coloca las resenas de una habitacion
     * @param resenas 
     */
    public void setResenas(List<ResenaEntity> resenas) {
        this.resenas = resenas;
    }

    /**
     * Retorna la capacidad de una habitacion
     * @return 
     */
    public Integer getCapacidad() {
        return capacidad;
    }

    /**
     * Coloca la capacidad de una habitacion
     * @param capacidad 
     */
    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }
    
    /**
     * Verifica si la informacion es completa
     * @return 
     */
    public Boolean informacionCompleta()
    {
        return (stringUtilizable(area.toString()) && stringUtilizable(valorAlquiler.toString()) 
                && stringUtilizable(descripcion) && stringUtilizable(rutaImagen) && stringUtilizable(capacidad.toString()));
    }
    
    /**
     * Verifia si un String puede ser utilizado
     * @param palabra
     * @return 
     */
    private Boolean stringUtilizable(String palabra)
    {
       return (palabra != null)? !palabra.isEmpty() : false;
    }
    
    /**
     * 
     * @param obj
     * @return 
     */
     @Override
    public boolean equals(Object obj) {
        if (this.getId() != null) {
            return this.getId().equals(((HabitacionEntity) obj).getId());
        }
        return super.equals(obj);
    }

    /**
     * 
     * @return 
     */
    @Override
    public int hashCode() {
        if (this.getId() != null) {
            return this.getId().hashCode();
        }
        return super.hashCode();
    }
    
}
