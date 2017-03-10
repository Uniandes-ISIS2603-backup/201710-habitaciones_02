/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.habitaciones.resources;

import co.edu.uniandes.csw.habitaciones.dtos.PagoDetailDTO;
import co.edu.uniandes.csw.habitaciones.ejbs.PagoLogic;
import co.edu.uniandes.csw.habitaciones.entities.PagoEntity;
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
@Path("/pagos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PagoResource 
{
    @Inject private PagoLogic pagoLogic;
    @Context private HttpServletResponse response;
    @QueryParam("page") private Integer page;
    @QueryParam("limit") private Integer maxRecords;
    
    private List<PagoDetailDTO> listEntity2DTO(List<PagoEntity> entityList)
    {
        List<PagoDetailDTO> list = new ArrayList<>();
        for (PagoEntity entity : entityList) 
        {
            list.add(new PagoDetailDTO(entity));
        }
        return list;
    }
    
    @GET
    public List<PagoDetailDTO> getPagos() 
    {
        return listEntity2DTO(pagoLogic.getPagos());
    }
    
    @GET
    @Path("{id: \\d+}")
    public PagoDetailDTO getPago(@PathParam("id") Long id) 
    {
        return new PagoDetailDTO(pagoLogic.getPago(id));
    }
    
    @POST
    public PagoDetailDTO createPago(PagoDetailDTO dto) 
    {
        return new PagoDetailDTO(pagoLogic.createPago(dto.toEntity()));
    }
    
    @PUT
    @Path("{id: \\d+}")
    public PagoDetailDTO updatePago(@PathParam("id") Long id, PagoDetailDTO dto) 
    {
        PagoEntity entity = dto.toEntity();
        entity.setId(id);
        return new PagoDetailDTO(pagoLogic.updatePago(entity));
    }
    
    @DELETE
    @Path("{id: \\d+}")
    public void deletePago(@PathParam("id") Long id) 
    {
        pagoLogic.deletePago(id);
    }
}