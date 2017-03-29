/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.habitaciones.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author ne.cabrera
 */
@Entity
public class ViviendaEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /**
     * El id de la vivienda
     */
    private Long id;

    /**
     * Ciudad en la que esta ubicada la vivienda
     */
    private String ciudad;

    /**
     * Direccion de la vivienda
     */
    private String direccion;
    
    /**
     * ruta de la imagen
     */
    private String rutaImagen;

    /**
     * Relacion one to many entre vivienda y habitacion
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "vivienda")
    private List<HabitacionEntity> habitacion;

    /**
     * Relacion many to one entre vivienda y anfitrion
     */
    @ManyToOne
    private AnfitrionEntity anfitrion;

    /**
     * Retorna la lista de habitaciones de la vivienda
     *
     * @return habitacion
     */
    public List<HabitacionEntity> getHabitaciones() {
        return habitacion;
    }

    /**
     * Asigna una lista de habitaciones
     *
     * @param pHabitaciones
     */
    public void setHabitaciones(List<HabitacionEntity> pHabitaciones) {
        this.habitacion = pHabitaciones;
    }
    
    public AnfitrionEntity getAnfitrion()
    {
        return anfitrion;
    }
    
    public void setAnfitrion( AnfitrionEntity pAnfitrion)
    {
        anfitrion = pAnfitrion;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the ciudad
     */
    public String getCiudad() {
        return ciudad;
    }

    /**
     * @param ciudad the ciudad to set
     */
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    /**
     * @return the direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * @param direccion the direccion to set
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public boolean equals(Object obj) 
    {
        if (this.getId() != null) {
            return this.getId().equals(((ViviendaEntity) obj).getId());
        }
        return super.equals(obj);
    }

    @Override
    public int hashCode() 
    {
        if (this.getId() != null) {
            return this.getId().hashCode();
        }
        return super.hashCode();
    }

    /**
     * Verfica que los atributos String no sean ni null ni vacios
     *
     * @return true si no sin null ni vacios, false de lo contrario
     */
    public Boolean informacionCompleta() 
    {
        return (stringUtilizable(ciudad) && stringUtilizable(direccion));
    }

    /**
     * Verifica si un String es null o vacio
     *
     * @param palabra el string a verificar
     * @return true si no es ni vacio ni null, false de lo contrario
     */
    private Boolean stringUtilizable(String palabra) 
    {
        return (palabra != null) ? !palabra.isEmpty() : false;
    }

    /**
     * @return the rutaImagen
     */
    public String getRutaImagen() {
        return rutaImagen;
    }

    /**
     * @param rutaImagen the rutaImagen to set
     */
    public void setRutaImagen(String rutaImagen) {
        this.rutaImagen = rutaImagen;
    }
}
