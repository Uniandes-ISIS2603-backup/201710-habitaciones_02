/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.habitaciones.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

/**
 *
 * @author s.cortes
 */
@Entity
@Table
@Inheritance (strategy = InheritanceType.JOINED)
public class UsuarioEntity implements Serializable
{
    //----------------------------------------------------------------------------------------------------
    // ATRIBUTOS
    //----------------------------------------------------------------------------------------------------

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /**
     * El ID del usuario (el de todos los usuarios)
     */
    private Long idUsuario;
    
    /**
     * Nombre del usuario
     */
    private String nombre;
    
    /**
     * Contrasena del usuario
     */
    private String contrasena;
    
    /**
     * Correo electronico del usuario
     */
    private String correoElectronico;
    
    /**
     * tipo de documento de identificacion
     */
    private String tipoDocumento;
    
    /**
     * numero del documento de identificacion
     */
    private Integer numeroDocumento;
    
    /**
     * direccion del usuario
     */
    private String direccion;
    
    /**
     * telefono del usuario
     */
    private Integer telefono;


    //----------------------------------------------------------------------------------------------------
    // METODOS
    //----------------------------------------------------------------------------------------------------
    
    
    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public Integer getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(Integer numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }
    
}
