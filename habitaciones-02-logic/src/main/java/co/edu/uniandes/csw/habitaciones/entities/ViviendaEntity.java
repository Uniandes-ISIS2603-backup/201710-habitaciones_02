/* 
 * The MIT License
 *
 * Copyright 2017 ne.cabrera.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
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
