/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.habitaciones.dtos;

import co.edu.uniandes.csw.habitaciones.entities.UsuarioEntity;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author s.cortes
 */
@XmlRootElement
public class UsuarioDTO implements Serializable
{
    
    //----------------------------------------------------------------------------------------------------
    // ATRIBUTOS
    //----------------------------------------------------------------------------------------------------

    
    /**
     * El ID del usuario (el de todos los usuarios)
     */
    protected Long idUsuario;
    
    /**
     * Nombre del usuario
     */
    protected String nombre;

    /**
     * Contrasena del usuario
     */
    protected String contrasena;
    
    /**
     * Correo electronico del usuario
     */
    protected String correoElectronico;
    
    /**
     * tipo de documento de identificacion
     */
    protected String tipoDocumento;
    
    /**
     * numero del documento de identificacion
     */
    protected Integer numeroDocumento;
    
    /**
     * direccion del usuario
     */
    protected String direccion;
    
    /**
     * telefono del usuario
     */
    protected Integer telefono;
    
    //----------------------------------------------------------------------------------------------------
    // CONTRUCTORES
    //----------------------------------------------------------------------------------------------------
    
    /**
     * Metodo Constructor por defecto de la clase
     
    public UsuarioDTO()
    {
        
    }
    */
    /**
     * Metodo contructor que inicializa los atributos a partir de la información contenida en el objeto UsuarioEntity
     * @param entity Entidad con la que se crea el objeto DTO
     
    public UsuarioDTO(UsuarioEntity entity)
    {
        if(entity != null)
        {
            this.nombre = entity.getNombre();
            this.correoElectronico = entity.getCorreoElectronico();
            this.contrasena = entity.getContrasena();
            this.idUsuario = entity.getIdUsuario();
            this.tipoDocumento = entity.getTipoDocumento();
            this.numeroDocumento = entity.getNumeroDocumento();
            this.direccion  =entity.getDireccion();
            this.telefono = entity.getTelefono();
        }
    }
    */
    
    //----------------------------------------------------------------------------------------------------
    // METODOS
    //----------------------------------------------------------------------------------------------------
    /**
    public UsuarioEntity toEntity()
    {
        UsuarioEntity entity = new UsuarioEntity();
        
        entity.setContrasena(this.getContrasena());
        entity.setCorreoElectronico(this.getCorreoElectronico());
        entity.setDireccion(this.getDireccion());
        entity.setIdUsuario(this.getIdUsuario());
        entity.setNombre(this.getNombre());
        entity.setNumeroDocumento(this.getNumeroDocumento());
        entity.setTelefono(this.getTelefono());
        entity.setTipoDocumento(this.getTipoDocumento());
       
        
        return entity;
    }
    **/

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
