/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.habitaciones.resources;

import co.edu.uniandes.csw.habitaciones.ejbs.HabitacionLogic;
import co.edu.uniandes.csw.habitaciones.dtos.HabitacionDetailDTO;
import co.edu.uniandes.csw.habitaciones.entities.HabitacionEntity;
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
@Path("/habitaciones")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class HabitacionResource {

    @Inject
    private HabitacionLogic habitacionLogic;
    @Context
    private HttpServletResponse response;
    @QueryParam("page")
    private Integer page;
    @QueryParam("limit")
    private Integer maxRecords;

    private List<HabitacionDetailDTO> listEntity2DTO(List<HabitacionEntity> entityList) {

        List<HabitacionDetailDTO> list = new ArrayList<>();

        for (HabitacionEntity entity : entityList) {

            list.add(new HabitacionDetailDTO(entity));
        }
        return list;
    }

    @GET
    public List<HabitacionDetailDTO> getHabitaciones() {

        return listEntity2DTO(habitacionLogic.getHabitaciones());
    }

    @GET
    @Path("{id: \\d+}")
    public HabitacionDetailDTO getHabitacion(@PathParam("id") Long id) {

        return new HabitacionDetailDTO(habitacionLogic.getHabitacion(id));
    }

    @POST
    public HabitacionDetailDTO createHabitacion(HabitacionDetailDTO dto) {

        HabitacionDetailDTO nuevaH = new HabitacionDetailDTO(habitacionLogic.createHabitacion(dto.toEntity()));
        
        return nuevaH ;
    }

    @PUT
    @Path("{id: \\d+}")
    public HabitacionDetailDTO updateHabitacion(@PathParam("id") Long id, HabitacionDetailDTO dto) {

        HabitacionEntity entity = dto.toEntity();
        entity.setId(id);

        return new HabitacionDetailDTO(habitacionLogic.updateHabitacion(entity));
    }

    @DELETE
    @Path("{id: \\d+}")
    public void deleteHabitacion(@PathParam("id") Long id) {

        habitacionLogic.deleteHabitacion(id);
    }
}
