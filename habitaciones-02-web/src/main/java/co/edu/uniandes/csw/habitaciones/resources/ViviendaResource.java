/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.habitaciones.resources;

import co.edu.uniandes.csw.habitaciones.dtos.ViviendaDTO;
import co.edu.uniandes.csw.habitaciones.dtos.ViviendaDetailDTO;
import co.edu.uniandes.csw.habitaciones.ejbs.ViviendaLogic;
import co.edu.uniandes.csw.habitaciones.entities.ViviendaEntity;
import co.edu.uniandes.csw.habitaciones.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author ne.cabrera
 */
@Path("/viviendas")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ViviendaResource 
{
    @Inject private ViviendaLogic viviendaLogic;
    @Context private HttpServletResponse response;
    @QueryParam("page") private Integer page;
    @QueryParam("limit") private Integer maxRecords;
    
    private List<ViviendaDTO> listEntity2DTO(List<ViviendaEntity> entityList)
    {
        List<ViviendaDTO> list = new ArrayList<>();
        for (ViviendaEntity entity : entityList) 
        {
            list.add(new ViviendaDTO(entity));
        }
        return list;
    }
    
    @GET
    public List<ViviendaDTO> getViviendas() 
    {
        return listEntity2DTO(viviendaLogic.getViviendas());
    }
    
    @GET
    @Path("{id: \\d+}")
    public ViviendaDetailDTO getVivienda(@PathParam("id") Long id) 
    {
        return new ViviendaDetailDTO(viviendaLogic.getVivienda(id));
    }
    
    @POST
    public ViviendaDetailDTO createVivienda(ViviendaDetailDTO dto) throws  BusinessLogicException
    {
        //System.out.println("aaaa");
        return new ViviendaDetailDTO(viviendaLogic.createVivienda(dto.toEntity()));
    }
    
    @PUT
    @Path("{id: \\d+}")
    public ViviendaDetailDTO updateVivienda(@PathParam("id") Long id, ViviendaDetailDTO dto) 
    {
        ViviendaEntity entity = dto.toEntity();
        entity.setId(id);
        return new ViviendaDetailDTO(viviendaLogic.updateVivienda(entity));
    }
    
    @DELETE
    @Path("{id: \\d+}")
    public void deleteVivienda(@PathParam("id") Long id) 
    {
        viviendaLogic.deleteVivienda(id);
    }
}
