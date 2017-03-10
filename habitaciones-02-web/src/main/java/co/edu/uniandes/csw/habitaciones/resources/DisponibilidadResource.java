/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.habitaciones.resources;

import co.edu.uniandes.csw.habitaciones.ejbs.DisponibilidadLogic;
import co.edu.uniandes.csw.habitaciones.entities.DisponibilidadEntity;
import co.edu.uniandes.csw.habitaciones.dtos.DisponibilidadDTO;
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
 * @author b.gamba10
 */
@Path("/disponibilidad")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class DisponibilidadResource {

    @Inject
    private DisponibilidadLogic disponibilidadLogic;
    @Context
    private HttpServletResponse response;
    @QueryParam("page")
    private Integer page;
    @QueryParam("limit")
    private Integer maxRecords;

    private List<DisponibilidadDTO> listEntity2DTO(List<DisponibilidadEntity> entityList) {

        List<DisponibilidadDTO> list = new ArrayList<>();

        for (DisponibilidadEntity entity : entityList) {

            list.add(new DisponibilidadDTO(entity));
        }
        return list;
    }

    @GET
    public List<DisponibilidadDTO> getDisponibilidad() {

        return listEntity2DTO(disponibilidadLogic.getDisponibilidades());
    }

    @GET
    @Path("{id: \\d+}")
    public DisponibilidadDTO getDisponibilidad(@PathParam("id") Long id) {

        return new DisponibilidadDTO(disponibilidadLogic.getDisponibilidad(id));
    }

    @POST
    public DisponibilidadDTO createDisponibilidad(DisponibilidadDTO dto) {

        return new DisponibilidadDTO(disponibilidadLogic.createDisponibilidad(dto.toEntity()));
    }

    @PUT
    @Path("{id: \\d+}")
    public DisponibilidadDTO updateDisponibilidad(@PathParam("id") Long id, DisponibilidadDTO dto) {

        DisponibilidadEntity entity = dto.toEntity();
        entity.setId(id);
        return new DisponibilidadDTO(disponibilidadLogic.updateDisponibilidad(entity));

    }

    @DELETE
    @Path("{id: \\d+}")
    public void deleteDisponibilidad(@PathParam("id") Long id) {

        disponibilidadLogic.deleteDisponibilidad(id);
    }

}
