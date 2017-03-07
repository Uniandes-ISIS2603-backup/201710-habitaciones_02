/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.habitaciones.dtos;

import co.edu.uniandes.csw.habitaciones.entities.UsuarioEntity;

/**
 *
 * @author s.cortes
 */
public class ViajeroDTO extends UsuarioDTO
{

    //----------------------------------------------------------------------------------------------------
    // ATRIBUTOS
    //----------------------------------------------------------------------------------------------------

    
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

    
}
