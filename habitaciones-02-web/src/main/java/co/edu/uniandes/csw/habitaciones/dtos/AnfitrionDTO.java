/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.habitaciones.dtos;

import co.edu.uniandes.csw.habitaciones.entities.AnfitrionEntity;
import co.edu.uniandes.csw.habitaciones.entities.ViviendaEntity;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author df.sanabria761
 */
@XmlRootElement
public class AnfitrionDTO extends UsuarioDTO
{

    /*
    Constructor por defecto
     */
    public AnfitrionDTO()
    {

    }

    /**
     * Constructor a partir de un Entity, inicializa los atributos a partir de
     * un entity que llega por parámetro
     *
     * @param entity Entidad a partir de la cual se genera el DTO
     */
    public AnfitrionDTO(AnfitrionEntity entity)
    {
        if (entity != null)
        {
            this.nombre = entity.getNombre();
            this.imagen = entity.getImagen();
            this.correoElectronico = entity.getCorreoElectronico();
            this.contrasena = entity.getContrasena();
            this.idUsuario = entity.getIdUsuario();
            this.tipoDocumento = entity.getTipoDocumento();
            this.numeroDocumento = entity.getNumeroDocumento();
            this.direccion = entity.getDireccion();
            this.telefono = entity.getTelefono();

        }

    }

    public AnfitrionEntity toEntity()
    {
        AnfitrionEntity entity = new AnfitrionEntity();
        entity.setCorreoElectronico(this.getCorreoElectronico());
        entity.setContrasena(this.getContrasena());
        entity.setIdUsuario(this.getIdUsuario());
        entity.setNombre(this.getNombre());
        entity.setTipoDocumento(this.getTipoDocumento());
        entity.setNumeroDocumento(this.getNumeroDocumento());
        entity.setTelefono(this.getTelefono());
        entity.setDireccion(this.getDireccion());
        entity.setImagen(this.getImagen());
        return entity;
    }
}
