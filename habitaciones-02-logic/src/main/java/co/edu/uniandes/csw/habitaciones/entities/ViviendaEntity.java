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
public class ViviendaEntity implements Serializable 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String ciudad;
    
    private String direccion;
    
    @OneToMany(fetch =FetchType.LAZY, mappedBy = "vivienda")
    private List<HabitacionEntity> habitacion;
    
    @ManyToOne
    private AnfitrionEntity anfitrion;
    
    public List<HabitacionEntity> getHabitaciones()
    {
        return habitacion;
    }
    
    public void setHabitaciones(List<HabitacionEntity> pHabitaciones)
    {
        this.habitacion = pHabitaciones;
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
    public boolean equals(Object obj) {
        if (this.getId() != null) {
            return this.getId().equals(((ViviendaEntity) obj).getId());
        }
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        if (this.getId() != null) {
            return this.getId().hashCode();
        }
        return super.hashCode();
    }
    
    public Boolean informacionCompleta()
    {
        return (stringUtilizable(ciudad) && stringUtilizable(direccion));
    }
    
    private Boolean stringUtilizable(String palabra)
    {
       return (palabra != null)? !palabra.isEmpty() : false;
    }
}