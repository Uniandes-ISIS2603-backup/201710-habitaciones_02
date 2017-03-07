/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.habitaciones.dtos;

import co.edu.uniandes.csw.habitaciones.entities.UsuarioEntity;
import co.edu.uniandes.csw.habitaciones.entities.ViajeroEntity;

/**
 *
 * @author s.cortes
 */
public class ViajeroDTO extends UsuarioDTO
{

    //----------------------------------------------------------------------------------------------------
    // ATRIBUTOS
    //----------------------------------------------------------------------------------------------------

    /**
     * Relacion One-to-Many con las Reservas 
     */
    
    //----------------------------------------------------------------------------------------------------
    // METODOS CONSTRUCTORES
    //----------------------------------------------------------------------------------------------------
    
    /**
     * Metodo Constructor por defecto de la clase
     */
    public ViajeroDTO()
    {
        
    }

    /**
     * Metodo contructor que inicializa los atributos a partir de la información contenida en el objeto UsuarioEntity
     * @param entity Entidad con la que se crea el objeto DTO
     */
    public ViajeroDTO(UsuarioEntity entity)
    {
        super(entity);
    }
    
    
    //----------------------------------------------------------------------------------------------------
    // METODOS
    //----------------------------------------------------------------------------------------------------

    public ViajeroEntity toEntity()
    {
        ViajeroEntity entity  = new ViajeroEntity();
        
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
    
}
