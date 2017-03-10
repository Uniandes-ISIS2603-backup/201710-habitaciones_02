/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.habitaciones.resources;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import co.edu.uniandes.csw.habitaciones.ejbs.ViajeroLogic;
import co.edu.uniandes.csw.habitaciones.entities.ViajeroEntity;
import co.edu.uniandes.csw.habitaciones.dtos.ViajeroDetailDTO;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;

/**
 *
 * @author s.cortes
 */
@Path("/viajeros")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ViajeroResource 
{
    @Inject
    private ViajeroLogic viajeroLogic;
    
    
    public List<ViajeroDetailDTO> listEntity2DTO(List<ViajeroEntity> listEntity)
    {
        List<ViajeroDetailDTO> listDto = new ArrayList<ViajeroDetailDTO>();
        for(ViajeroEntity entity : listEntity)
        {
            ViajeroDetailDTO ndto = new ViajeroDetailDTO(entity);
            listDto.add(ndto);
        }
        
        return listDto;
    }
    
    @GET
    public List<ViajeroDetailDTO> getViajeros()
    {
        return listEntity2DTO(viajeroLogic.getViajeros());
    }
    
    @GET
    @Path("{id: \\d+}")
    public ViajeroDetailDTO getViajero(@PathParam("id") Long id)
    {
        return new ViajeroDetailDTO(viajeroLogic.getViajero(id));
    }
    
    @PUT
    @Path("{id: \\d+}")
    public ViajeroDetailDTO updateViajero(@PathParam("id") Long id, ViajeroDetailDTO dto)
    {
        ViajeroEntity entity = dto.toEntity();
        entity.setIdUsuario(id);
        return new ViajeroDetailDTO(viajeroLogic.updateViajero(entity));
    }
    
    @DELETE
    @Path("{id: \\d+}")
    public void deleteViajero(@PathParam("id") Long id) 
    {
        viajeroLogic.removeViajero(id);
    }
}
