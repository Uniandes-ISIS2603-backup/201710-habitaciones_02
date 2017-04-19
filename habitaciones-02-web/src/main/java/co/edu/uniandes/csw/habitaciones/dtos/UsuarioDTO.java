/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.habitaciones.dtos;

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
     * Imagen del usuario
     */
    protected String imagen;

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
    protected String numeroDocumento;

    /**
     * direccion del usuario
     */
    protected String direccion;

    /**
     * telefono del usuario
     */
    protected String telefono;

    //----------------------------------------------------------------------------------------------------
    // CONTRUCTORES
    //----------------------------------------------------------------------------------------------------
    /**
     * Metodo Constructor por defecto de la clase
     *
     * public UsuarioDTO() {
     *
     * }
     */
    /**
     * Metodo contructor que inicializa los atributos a partir de la información
     * contenida en el objeto UsuarioEntity
     *
     * @param entity Entidad con la que se crea el objeto DTO
     *
     * public UsuarioDTO(UsuarioEntity entity) { if(entity != null) {
     * this.nombre = entity.getNombre(); this.correoElectronico =
     * entity.getCorreoElectronico(); this.contrasena = entity.getContrasena();
     * this.idUsuario = entity.getIdUsuario(); this.tipoDocumento =
     * entity.getTipoDocumento(); this.numeroDocumento =
     * entity.getNumeroDocumento(); this.direccion =entity.getDireccion();
     * this.telefono = entity.getTelefono(); } }
     */
    //----------------------------------------------------------------------------------------------------
    // METODOS
    //----------------------------------------------------------------------------------------------------
    /**
     * public UsuarioEntity toEntity() { UsuarioEntity entity = new
     * UsuarioEntity();
     *
     * entity.setContrasena(this.getContrasena());
     * entity.setCorreoElectronico(this.getCorreoElectronico());
     * entity.setDireccion(this.getDireccion());
     * entity.setIdUsuario(this.getIdUsuario());
     * entity.setNombre(this.getNombre());
     * entity.setNumeroDocumento(this.getNumeroDocumento());
     * entity.setTelefono(this.getTelefono());
     * entity.setTipoDocumento(this.getTipoDocumento());
     *
     *
     * return entity; }
    *
     */
    /**
     * retorna el atributo ID de el DTO
     *
     * @return el id
     */
    public Long getIdUsuario()
    {
        return idUsuario;
    }

    /**
     * Le asigna a el DTO el valor del atibuto ID
     *
     * @param idUsuario el id a asignar
     */
    public void setIdUsuario(Long idUsuario)
    {
        this.idUsuario = idUsuario;
    }

    /**
     * retorna el atributo nombre de el DTO
     *
     * @return el nombre
     */
    public String getNombre()
    {
        return nombre;
    }

    /**
     * Retorna el atributo Imagen del DTO
     *
     * @return Imagen del usuario
     */
    public String getImagen()
    {
        return imagen;
    }

    /**
     * Asigna al DTO la imagen
     *
     * @param imagen La imagen a asignar
     */
    public void setImagen(String imagen)
    {
        this.imagen = imagen;
    }

    /**
     * Le asigna a el DTO el valor del atibuto nombre
     *
     * @param nombre el nombre a asignar
     */
    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    /**
     * retorna el atributo contraseña de el DTO
     *
     * @return la contraseña
     */
    public String getContrasena()
    {
        return contrasena;
    }

    /**
     * Le asigna a el DTO el valor del atibuto contraseña
     *
     * @param contrasena la contraseña a asignar
     */
    public void setContrasena(String contrasena)
    {
        this.contrasena = contrasena;
    }

    /**
     * retorna el atributo correoElectronico de el DTO
     *
     * @return el correo electronico
     */
    public String getCorreoElectronico()
    {
        return correoElectronico;
    }

    /**
     * Le asigna a el DTO el valor del atibuto correoElectronico
     *
     * @param correoElectronico el correo electronico a asignar
     */
    public void setCorreoElectronico(String correoElectronico)
    {
        this.correoElectronico = correoElectronico;
    }

    /**
     * retorna el atributo tipoDocumento de el DTO
     *
     * @return el tipo de documento
     */
    public String getTipoDocumento()
    {
        return tipoDocumento;
    }

    /**
     * Le asigna a el DTO el valor del atibuto tipoDocumento
     *
     * @param tipoDocumento el tipo de documento a asignar
     */
    public void setTipoDocumento(String tipoDocumento)
    {
        this.tipoDocumento = tipoDocumento;
    }

    /**
     * retorna el atributo numeroDocumento de el DTO
     *
     * @return el numero de documento
     */
    public String getNumeroDocumento()
    {
        return numeroDocumento;
    }

    /**
     * Le asigna a el DTO el valor del atibuto numeroDocumento
     *
     * @param numeroDocumento el numero de documento a asignar
     */
    public void setNumeroDocumento(String numeroDocumento)
    {
        this.numeroDocumento = numeroDocumento;
    }

    /**
     * retorna el atributo direccion de el DTO
     *
     * @return la direccion
     */
    public String getDireccion()
    {
        return direccion;
    }

    /**
     * Le asigna a el DTO el valor del atibuto direccion
     *
     * @param direccion la direccion a asignar
     */
    public void setDireccion(String direccion)
    {
        this.direccion = direccion;
    }

    /**
     * retorna el atributo telefono de el DTO
     *
     * @return el telefono
     */
    public String getTelefono()
    {
        return telefono;
    }

    /**
     * Le asigna a el DTO el valor del atibuto telefono
     *
     * @param telefono el telefono a asignar
     */
    public void setTelefono(String telefono)
    {
        this.telefono = telefono;
    }

}
