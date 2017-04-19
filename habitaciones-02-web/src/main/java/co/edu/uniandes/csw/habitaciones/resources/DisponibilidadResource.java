/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.habitaciones.resources;

import co.edu.uniandes.csw.habitaciones.ejbs.DisponibilidadLogic;
import co.edu.uniandes.csw.habitaciones.entities.DisponibilidadEntity;
import co.edu.uniandes.csw.habitaciones.dtos.DisponibilidadDetailDTO;
import co.edu.uniandes.csw.habitaciones.ejbs.HabitacionLogic;
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

@Path("/habitaciones/{idHabitacion: \\d+}/disponibilidades")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class DisponibilidadResource {

    @Inject
    private DisponibilidadLogic disponibilidadLogic;

    @Inject
    private HabitacionLogic habitacionLogic;

    @Context
    private HttpServletResponse response;
    @QueryParam("page")
    private Integer page;
    @QueryParam("limit")
    private Integer maxRecords;

    /**
     * Convierte una lista de Entidades en DTO
     *
     * @param entityList
     * @return
     */
    private List<DisponibilidadDetailDTO> listEntity2DTO(List<DisponibilidadEntity> entityList) {

        List<DisponibilidadDetailDTO> list = new ArrayList<>();

        for (DisponibilidadEntity entity : entityList) {

            list.add(new DisponibilidadDetailDTO(entity));
        }
        return list;
    }

    /**
     * Retorna las disponibilidades de una habitacion
     *
     * @param idHabitacion
     * @return
     */
    @GET
    public List<DisponibilidadDetailDTO> getDisponibilidades(@PathParam("idHabitacion") Long idHabitacion) {
        // TODO Si la habitación no existe debe disparar WebApplicationException 404
        if (habitacionLogic.getHabitacion(idHabitacion) == null) {

            throw new WebApplicationException(404);
        }
        return listEntity2DTO(disponibilidadLogic.getDisponibilidades(idHabitacion));

    }

    /**
     * Retorna una disponibilidad de una habitacion especifica
     *
     * @param idHabitacion
     * @param id
     * @return
     */
    @GET
    @Path("{id: \\d+}")
    public DisponibilidadDetailDTO getDisponibilidad(@PathParam("idHabitacion") Long idHabitacion, @PathParam("id") Long id) {
// TODO Si la habitación no existe debe disparar WebApplicationException 404
// TODO Si la disponibilidad no existe debe disparar WebApplicationException 404
        if (habitacionLogic.getHabitacion(idHabitacion) == null) {

            throw new WebApplicationException(404);
        }
        if (disponibilidadLogic.getDisponibilidad(idHabitacion, id) == null) {

            throw new WebApplicationException(404);
        }
        return new DisponibilidadDetailDTO(disponibilidadLogic.getDisponibilidad(idHabitacion, id));

    }

    /**
     * Crear una nueva disponibilidad para una habitacion
     *
     * @param dto
     * @return
     * @throws BusinessLogicException
     */
    @POST
    public DisponibilidadDetailDTO createDisponibilidad(DisponibilidadDetailDTO dto) throws BusinessLogicException {
// TODO Si la habitación no existe debe disparar WebApplicationException 404
        if (habitacionLogic.getHabitacion(dto.getHabitacion().getId()) == null) {

            throw new WebApplicationException(404);
        }
        return new DisponibilidadDetailDTO(disponibilidadLogic.createDisponibilidad(dto.toEntity()));
    }

    /**
     * Modidfica una disponibilidad ya existente
     *
     * @param id
     * @param dto
     * @return
     * @throws BusinessLogicException
     */
    @PUT
    @Path("{id: \\d+}")
    public DisponibilidadDetailDTO updateDisponibilidad(@PathParam("id") Long id,
            DisponibilidadDetailDTO dto) throws BusinessLogicException {
// TODO Si la habitación no existe debe disparar WebApplicationException 404
// TODO Si la disponibilidad no existe debe disparar WebApplicationException 404
        if (habitacionLogic.getHabitacion(dto.getHabitacion().getId()) == null) {

            throw new WebApplicationException(404);
        }
        if (disponibilidadLogic.getDisponibilidad(dto.getHabitacion().getId(), id) == null) {

            throw new WebApplicationException(404);
        }

        DisponibilidadEntity entity = dto.toEntity();
        entity.setId(id);
        return new DisponibilidadDetailDTO(disponibilidadLogic.updateDisponibilidad(entity));

    }

    /**
     * Elimina una disponibilidad asociada a una habitacion
     *
     * @param idHabitacion
     * @param id
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteDisponibilidad(@PathParam("idHabitacion") Long idHabitacion, @PathParam("id") Long id) {
// TODO Si la habitación no existe debe disparar WebApplicationException 404
// TODO Si la disponibilidad no existe debe disparar WebApplicationException 404
        if (habitacionLogic.getHabitacion(idHabitacion) == null) {

            throw new WebApplicationException(404);
        }
        if (disponibilidadLogic.getDisponibilidad(idHabitacion, id) == null) {

            throw new WebApplicationException(404);
        }
        disponibilidadLogic.deleteDisponibilidad(id);
    }
}
