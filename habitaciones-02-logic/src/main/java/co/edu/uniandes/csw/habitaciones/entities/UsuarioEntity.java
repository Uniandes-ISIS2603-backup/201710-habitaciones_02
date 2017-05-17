/* 
 * The MIT License
 *
 * Copyright 2017 Los Favoritos.
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
@Inheritance(strategy = InheritanceType.JOINED)
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
     * Ruta de la imagen del usuario
     */
    private String imagen;
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
    private String numeroDocumento;

    /**
     * direccion del usuario
     */
    private String direccion;

    /**
     * telefono del usuario
     */
    private String telefono;

    //----------------------------------------------------------------------------------------------------
    // METODOS
    //----------------------------------------------------------------------------------------------------
    /**
     * retorna el atributo ID de la entidad
     *
     * @return el id
     */
    public Long getIdUsuario()
    {
        return idUsuario;
    }

    /**
     * Le asigna a la entidad el valor del atibuto ID
     *
     * @param idUsuario el id a asignar
     */
    public void setIdUsuario(Long idUsuario)
    {
        this.idUsuario = idUsuario;
    }

    /**
     * retorna el atributo nombre de la entidad
     *
     * @return el nombre
     */
    public String getNombre()
    {
        return nombre;
    }

    /**
     * Le asigna a la entidad el valor del atibuto nombre
     *
     * @param nombre el nombre a asignar
     */
    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    /**
     * Retorna la ruta de la imagen de la entidad
     *
     * @return imagen
     */

    public String getImagen()
    {
        return imagen;
    }

    /**
     * Asigna a la imagen el valor correspondiente
     *
     * @param imagen Imagen que se va a agregar imagen != null imagen != ""
     */
    public void setImagen(String imagen)
    {

        this.imagen = imagen;
    }

    /**
     * retorna el atributo contraseña de la entidad
     *
     * @return la contraseña
     */
    public String getContrasena()
    {
        return contrasena;
    }

    /**
     * Le asigna a la entidad el valor del atibuto contraseña
     *
     * @param contrasena la contraseña a asignar
     */
    public void setContrasena(String contrasena)
    {
        this.contrasena = contrasena;
    }

    /**
     * retorna el atributo correoElectronico de la entidad
     *
     * @return el correo electronico
     */
    public String getCorreoElectronico()
    {
        return correoElectronico;
    }

    /**
     * Le asigna a la entidad el valor del atibuto correoElectronico
     *
     * @param correoElectronico el correo electronico a asignar
     */
    public void setCorreoElectronico(String correoElectronico)
    {
        this.correoElectronico = correoElectronico;
    }

    /**
     * retorna el atributo tipoDocumento de la entidad
     *
     * @return el tipo de documento
     */
    public String getTipoDocumento()
    {
        return tipoDocumento;
    }

    /**
     * Le asigna a la entidad el valor del atibuto tipoDocumento
     *
     * @param tipoDocumento el tipo de documento a asignar
     */
    public void setTipoDocumento(String tipoDocumento)
    {
        this.tipoDocumento = tipoDocumento;
    }

    /**
     * retorna el atributo numeroDocumento de la entidad
     *
     * @return el numero de documento
     */
    public String getNumeroDocumento()
    {
        return numeroDocumento;
    }

    /**
     * Le asigna a la entidad el valor del atibuto numeroDocumento
     *
     * @param numeroDocumento el numero de documento a asignar
     */
    public void setNumeroDocumento(String numeroDocumento)
    {
        this.numeroDocumento = numeroDocumento;
    }

    /**
     * retorna el atributo direccion de la entidad
     *
     * @return la direccion
     */
    public String getDireccion()
    {
        return direccion;
    }

    /**
     * Le asigna a la entidad el valor del atibuto direccion
     *
     * @param direccion la direccion a asignar
     */
    public void setDireccion(String direccion)
    {
        this.direccion = direccion;
    }

    /**
     * retorna el atributo telefono de la entidad
     *
     * @return el telefono
     */
    public String getTelefono()
    {
        return telefono;
    }

    /**
     * Le asigna a la entidad el valor del atibuto telefono
     *
     * @param telefono el telefono a asignar
     */
    public void setTelefono(String telefono)
    {
        this.telefono = telefono;
    }

    /**
     * Verfica que los atributos String no sean ni null ni vacios
     *
     * @return true si no sin null ni vacios, false de lo contrario
     */
    public Boolean informacionCompleta()
    {
        boolean info = (stringUtilizable(contrasena) && stringUtilizable(correoElectronico)
                && stringUtilizable(direccion) && stringUtilizable(nombre) && stringUtilizable(tipoDocumento)
                && stringUtilizable(telefono) && stringUtilizable(numeroDocumento));
        
        return info;
    }

    /**
     * Verifica si un String es null o vacio
     *
     * @param palabra el string a verificar
     * @return true si no es ni vacio ni null, false de lo contrario
     */
    private Boolean stringUtilizable(String palabra)
    {
        boolean resultado = false;
        if(palabra != null)
        {
            resultado = !palabra.isEmpty();
        }
        return resultado;
    }

}
