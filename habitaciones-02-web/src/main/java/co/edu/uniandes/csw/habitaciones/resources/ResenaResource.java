/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.habitaciones.resources;

import co.edu.uniandes.csw.habitaciones.dtos.ResenaDetailDTO;
import co.edu.uniandes.csw.habitaciones.ejbs.ResenaLogic;
import co.edu.uniandes.csw.habitaciones.entities.ResenaEntity;
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
 * @author s.cortes
 */
@Path("/resenas")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ResenaResource
{
    @Inject private ResenaLogic logic;
    @Context private HttpServletResponse response;
    @QueryParam("page") private Integer page;
    @QueryParam("limit") private Integer maxRecords;
    
   
    private List<ResenaDetailDTO> listEntity2DTO(List<ResenaEntity> listEntity) {
        List<ResenaDetailDTO> lista = new ArrayList<>();
        for (ResenaEntity entity : listEntity) {

            lista.add(new ResenaDetailDTO(entity));
        }
        return lista;
    }

    @GET
    public List<ResenaDetailDTO> getResenas() {

        return listEntity2DTO(logic.findResenas());
    }

    @GET
    @Path("{id: \\d+}")
    public ResenaDetailDTO getResena(@PathParam("id") Long id) {
        return new ResenaDetailDTO(logic.findResena(id));
    }

    @POST
    public ResenaDetailDTO createResena(ResenaDetailDTO dto) throws BusinessLogicException 
    {
        return new ResenaDetailDTO(logic.createResena(dto.toEntity()));
    }

    @PUT
    @Path("{id: \\d+}")
    public ResenaDetailDTO updateResena(@PathParam("id") Long id, ResenaDetailDTO dto) throws BusinessLogicException {
        ResenaEntity entity = dto.toEntity();
        entity.setId(id);
        return new ResenaDetailDTO(logic.updateResena(entity));
    }

    @DELETE
    @Path("{id: \\d+}")
    public void deleteResena(@PathParam("id") Long id) {
        logic.delete(id);
    }

    
}
