/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.habitaciones.dtos;

import co.edu.uniandes.csw.habitaciones.entities.UsuarioEntity;
import co.edu.uniandes.csw.habitaciones.entities.ViajeroEntity;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author s.cortes
 */
@XmlRootElement
public class ViajeroDetailDTO extends ViajeroDTO {
    //----------------------------------------------------------------------------------------------------
    // ATRIBUTOS DEL DETAIL DTO
    //----------------------------------------------------------------------------------------------------

    //----------------------------------------------------------------------------------------------------
    // METODOS CONSTRUCTORES
    //----------------------------------------------------------------------------------------------------
    public ViajeroDetailDTO()
    {

    }

    public ViajeroDetailDTO(UsuarioEntity entity)
    {
        super(entity);
    }

    //----------------------------------------------------------------------------------------------------
    // METODOS
    //----------------------------------------------------------------------------------------------------

    @Override
    public ViajeroEntity toEntity()
    {
        ViajeroEntity entity = super.toEntity();
        
        return entity;
    }
}
