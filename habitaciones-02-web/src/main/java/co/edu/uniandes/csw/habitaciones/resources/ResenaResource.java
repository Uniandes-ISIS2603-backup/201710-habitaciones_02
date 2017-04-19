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
import javax.ws.rs.WebApplicationException;
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

    /**
     * Atributo del logic de la reserva
     */
    @Inject
    private ResenaLogic logic;
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
    private List<ResenaDetailDTO> listEntity2DTO(List<ResenaEntity> listEntity)
    {
        List<ResenaDetailDTO> lista = new ArrayList<>();
        for (ResenaEntity entity : listEntity)
        {

            lista.add(new ResenaDetailDTO(entity));
        }
        return lista;
    }

    //TODO traer todas las reseñas del sistema no parece tener mucho sentido. COMPLETADO
    // TODO deberia ser get /viajeros/:id/resenas las reseñas del viajero (en el recurso viajero) COMPLETADO
    // TODO deberia ser get /habitaciones/:id/resenas las reseñas de la habitacion (en el recurso habitación)
    // TODO deberia ser get /anfitriones/:id/resenas las reseñas del anfitrion (en el recurso anfitrion)
    /**
     * Metodo encargado de obtener todos los DTOs de los reseñas
     *
     * @return
     */
    /**
     * @GET public List<ResenaDetailDTO> getResenas() {
     *
     * return listEntity2DTO(logic.findResenas()); }
    *
     */
    /**
     *
     * @param idViajero
     * @return
     */
    @GET
    @Path("porViajero/{viajeroId: \\d+}")
    public List<ResenaDetailDTO> getResenasByViajero(@PathParam("viajeroId") Long idViajero)
    {
        return listEntity2DTO(logic.findResenasViajero(idViajero));
    }

    @GET
    @Path("porHabitacion/{habitacionId: \\d+}")
    public List<ResenaDetailDTO> getResenasByHabitacion(@PathParam("habitacionId") Long idHabitacion)
    {
        return listEntity2DTO(logic.findResenasHabitacion(idHabitacion));
    }

    /**
     * Metodo encargado de retornar un DTO reseña a partir de un id
     *
     * @param id el id de la entidad
     * @return
     */
    @GET
    @Path("{id: \\d+}")
    public ResenaDetailDTO getResena(@PathParam("id") Long id) throws WebApplicationException
    {
        ResenaDetailDTO resena = new ResenaDetailDTO(logic.findResena(id));
        if (resena == null)
        {
            throw new WebApplicationException(404);
        }
        return resena;
    }

    /**
     * Metodo encargado de agregar un nuevo reseña a la base de datos
     *
     * @param dto el nuevo DTO
     * @return la reseña creado
     * @throws BusinessLogicException Exception de las reglas del negocio
     */
    @POST
    public ResenaDetailDTO createResena(ResenaDetailDTO dto) throws BusinessLogicException
    {
        return new ResenaDetailDTO(logic.createResena(dto.toEntity()));
    }

    /**
     * Metodo encargado de actualizar la informacion de una reseña
     *
     * @param id el id de una reseña
     * @param dto el DTO con la informacion actualizada
     * @return la reseña actualizado
     * @throws BusinessLogicException Exception de las reglas del negocio
     */
    @PUT
    @Path("{id: \\d+}")
    public ResenaDetailDTO updateResena(@PathParam("id") Long id, ResenaDetailDTO dto) throws BusinessLogicException, WebApplicationException
    {
        if (logic.findResena(id) == null)
        {
            throw new WebApplicationException(404);
        }
        ResenaEntity entity = dto.toEntity();
        entity.setId(id);
        return new ResenaDetailDTO(logic.updateResena(entity));
    }

    /**
     * Elimina una reseña de la base de datos
     *
     * @param id el id dla reseña
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteResena(@PathParam("id") Long id) throws WebApplicationException
    {
        if (logic.findResena(id) == null)
        {
            throw new WebApplicationException(404);
        }
        logic.delete(id);
    }

}
