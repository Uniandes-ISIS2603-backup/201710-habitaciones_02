/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.habitaciones.resources;

import co.edu.uniandes.csw.habitaciones.dtos.HabitacionDTO;
import co.edu.uniandes.csw.habitaciones.dtos.ViviendaDTO;
import co.edu.uniandes.csw.habitaciones.dtos.ViviendaDetailDTO;
import co.edu.uniandes.csw.habitaciones.ejbs.HabitacionLogic;
import co.edu.uniandes.csw.habitaciones.ejbs.ViviendaLogic;
import co.edu.uniandes.csw.habitaciones.entities.HabitacionEntity;
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
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author ne.cabrera
 */
@Path("/viviendas")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ViviendaResource
{
    private final static int ERROR_404 = 404;
    
    /**
     * logic de la vivienda
     */
    @Inject
    private ViviendaLogic viviendaLogic;
    /**
     * logic de las habitaciones
     */
    @Inject
    private HabitacionLogic habitacionLogic;

    @Context
    private HttpServletResponse response;
    @QueryParam("page")
    private Integer page;
    @QueryParam("limit")
    private Integer maxRecords;

    /**
     * @param entityList lista de viviendaEntity
     * @return lista de vivienda DTO
     */
    private List<ViviendaDTO> listEntity2DTO(List<ViviendaEntity> entityList)
    {
        List<ViviendaDTO> list = new ArrayList<>();
        for (ViviendaEntity entity : entityList)
        {
            list.add(new ViviendaDTO(entity));
        }
        return list;
    }

    /**
     * @param entityList lista de habitacionEntity
     * @return lista de habitacion DTO
     */
    private List<HabitacionDTO> listEntityHabitacion2DTO(List<HabitacionEntity> entityList)
    {
        List<HabitacionDTO> list = new ArrayList<>();
        for (HabitacionEntity entity : entityList)
        {
            list.add(new HabitacionDTO(entity));
        }
        return list;
    }

    /**
     * obtiene todas las vivienda como DTO
     *
     * @return lista de viviendaDTO
     */
    @GET
    public List<ViviendaDTO> getViviendas()
    {
        return listEntity2DTO(viviendaLogic.getViviendas());
    }

    /**
     * @param id de la vivienda buscada
     * @return la vivienda con un id igual al dado por parametro
     */
    @GET
    @Path("{id: \\d+}")
    public ViviendaDetailDTO getVivienda(@PathParam("id") Long id)
    { // TODO Si la vivienda no existe debe disparar WebApplicationException 404
        ViviendaEntity vivienda = viviendaLogic.getVivienda(id);
        if (vivienda == null)
        {
            throw new WebApplicationException();
        }
        return new ViviendaDetailDTO(viviendaLogic.getVivienda(id));
    }

    /**
     * @param dto viviendaDetailDTO a agregar
     * @return la vivienda agregada
     * @throws BusinessLogicException si los datos de la vivienda no estan
     * completos
     */
    @POST
    public ViviendaDetailDTO createVivienda(ViviendaDetailDTO dto) throws BusinessLogicException
    {
        //System.out.println("aaaa");
        return new ViviendaDetailDTO(viviendaLogic.createVivienda(dto.toEntity()));
    }

    /**
     *
     * @param id el id de la vivienda a actualizar
     * @param dto viviendaDetailDTO a actualizar
     * @return si los datos de la vivienda no estan completos
     */
    @PUT
    @Path("{id: \\d+}")
    public ViviendaDetailDTO updateVivienda(@PathParam("id") Long id, ViviendaDetailDTO dto)
    {// TODO Si la vivienda no existe debe disparar WebApplicationException 404
        ViviendaEntity vivienda = viviendaLogic.getVivienda(id);
        if (vivienda == null)
        {
            throw new WebApplicationException(ERROR_404);
        }
        ViviendaEntity entity = dto.toEntity();
        entity.setId(id);
        return new ViviendaDetailDTO(viviendaLogic.updateVivienda(entity));
    }

    /**
     * @param id el id de la vivienda a eliminar
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteVivienda(@PathParam("id") Long id)
    {// TODO Si la vivienda no existe debe disparar WebApplicationException 404
        ViviendaEntity vivienda = viviendaLogic.getVivienda(id);
        if (vivienda == null)
        {
            throw new WebApplicationException(ERROR_404);
        }
        viviendaLogic.deleteVivienda(id);
    }

    // TODO falta get /viviendas/:id/habitaciones las habitaciones de una vivienda
    /**
     * @param id el id de la vivienda
     * @return las habitacione pertenecientes a la vivienda con el id dado por
     * parametro
     */
    @GET
    @Path("{id: \\d+}/habitaciones")
    public List<HabitacionDTO> getHabitaciones(@PathParam("id") Long id)
    {
        ViviendaEntity vivienda = viviendaLogic.getVivienda(id);
        if (vivienda == null)
        {
            throw new WebApplicationException(ERROR_404);
        }
        return listEntityHabitacion2DTO(habitacionLogic.getHabitacionesVivienda(id));
    }
}
