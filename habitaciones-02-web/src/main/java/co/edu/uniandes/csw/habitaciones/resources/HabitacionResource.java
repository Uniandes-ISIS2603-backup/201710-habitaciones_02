/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.habitaciones.resources;

import co.edu.uniandes.csw.habitaciones.ejbs.HabitacionLogic;
import co.edu.uniandes.csw.habitaciones.dtos.HabitacionDetailDTO;
import co.edu.uniandes.csw.habitaciones.entities.HabitacionEntity;
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
import javax.ws.rs.WebApplicationException;
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

    /**
     * Metodo encargado de convertir la entidad a DTO
     *
     * @param listEntity la lista con las entidades
     * @return una lista con DTOs
     */
    private List<HabitacionDetailDTO> listEntity2DTO(List<HabitacionEntity> entityList) {

        List<HabitacionDetailDTO> list = new ArrayList<>();

        for (HabitacionEntity entity : entityList) {

            list.add(new HabitacionDetailDTO(entity));
        }
        return list;
    }

    /**
     * Retorna todas las habitaciones
     *
     * @return lista con habitaciones
     */
    @GET
    public List<HabitacionDetailDTO> getHabitaciones() {

        return listEntity2DTO(habitacionLogic.getHabitaciones());
    }

    /**
     * Retorna una habitacion
     *
     * @param id de la habitacion
     * @return Habitacion
     */
    @GET
    @Path("{id: \\d+}")
    public HabitacionDetailDTO getHabitacion(@PathParam("id") Long id) {
        // TODO Si el id no existe debe disparar WebApplicationException 404
        if (habitacionLogic.getHabitacion(id) == null) {
            throw new WebApplicationException(404);
        }
        return new HabitacionDetailDTO(habitacionLogic.getHabitacion(id));
    }
    
    /**
     * Metodo encargado de llamar a la clase ResenaResourse para retornar las
     * resenas de una habitacion respectiva
     * @return la clase ResenaResourse encargada de retornar las resenas de
     * la habitacion.
     */
    @Path("{habitacionId: \\d+}/resenas")
    public Class<ResenaResource> getResenasHabitacion()
    {
        return  ResenaResource.class;
    }

    /**
     * Crea una nueva habitacion
     *
     * @param dto
     * @return
     * @throws BusinessLogicException
     */
    @POST
    public HabitacionDetailDTO createHabitacion(HabitacionDetailDTO dto) throws BusinessLogicException {

        HabitacionDetailDTO nuevaH = new HabitacionDetailDTO(habitacionLogic.createHabitacion(dto.toEntity()));

        return nuevaH;
    }

    /**
     * Modifiac una habitacion ya existente
     *
     * @param id
     * @param dto
     * @return
     * @throws BusinessLogicException
     */
    @PUT
    @Path("{id: \\d+}")
    public HabitacionDetailDTO updateHabitacion(@PathParam("id") Long id, HabitacionDetailDTO dto) throws BusinessLogicException {
        // TODO Si el id no existe debe disparar WebApplicationException 404
        if (habitacionLogic.getHabitacion(id) == null) {
            throw new WebApplicationException(404);
        }
        HabitacionEntity entity = dto.toEntity();
        entity.setId(id);

        return new HabitacionDetailDTO(habitacionLogic.updateHabitacion(entity));
    }

    /**
     * Elimina una habitacion ya existente
     *
     * @param id
     */
    @DELETE
    @Path("{id: \\d+}")
    // TODO Si el id no existe debe disparar WebApplicationException 404
    public void deleteHabitacion(@PathParam("id") Long id) {

        if (habitacionLogic.getHabitacion(id) == null) {
            throw new WebApplicationException(404);
        }
        habitacionLogic.deleteHabitacion(id);
    }
}
