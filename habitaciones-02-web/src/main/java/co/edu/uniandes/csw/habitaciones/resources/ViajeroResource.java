/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.habitaciones.resources;

import co.edu.uniandes.csw.habitaciones.dtos.ViajeroDTO;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import co.edu.uniandes.csw.habitaciones.ejbs.ViajeroLogic;
import co.edu.uniandes.csw.habitaciones.entities.ViajeroEntity;
import co.edu.uniandes.csw.habitaciones.dtos.ViajeroDetailDTO;
import co.edu.uniandes.csw.habitaciones.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;

/**
 *
 * @author s.cortes
 */
@Path("/viajeros")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ViajeroResource 
{
    @Inject private ViajeroLogic viajeroLogic;
    @Context private HttpServletResponse response;
    @QueryParam("page") private Integer page;
    @QueryParam("limit") private Integer maxRecords;
    
    
    public List<ViajeroDTO> listEntity2DTO(List<ViajeroEntity> listEntity)
    {
        List<ViajeroDTO> listDto = new ArrayList<>();
        for(ViajeroEntity entity : listEntity)
        {
            ViajeroDTO ndto = new ViajeroDTO(entity);
            listDto.add(ndto);
        }
        
        return listDto;
    }
    
    @GET
    public List<ViajeroDTO> getViajeros()
    {
        return listEntity2DTO(viajeroLogic.getViajeros());
    }
    
    @GET
    @Path("{id: \\d+}")
    public ViajeroDetailDTO getViajero(@PathParam("id") Long id)
    {
        return new ViajeroDetailDTO(viajeroLogic.getViajero(id));
    }
    
    @POST
    public ViajeroDetailDTO crearViajero(ViajeroDetailDTO dto)throws BusinessLogicException
    {
        ViajeroEntity entity = viajeroLogic.createViajero(dto.toEntity());
        return new ViajeroDetailDTO(entity);
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
        viajeroLogic.deleteViajero(id);
    }
}
