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
import javax.ws.rs.WebApplicationException;
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
    //----------------------------------------------------------------------------------------------------
    // ATRIBUTOS DEL RECURSO
    //----------------------------------------------------------------------------------------------------

    /**
     * Atributo del logic del viajero
     */
    @Inject
    private ViajeroLogic viajeroLogic;

    /**
     * Atributo Response
     */
    @Context
    private HttpServletResponse response;

    /**
     * Atributo page
     */
    @QueryParam("page")
    private Integer page;

    /**
     * Atributo maxRecords
     */
    @QueryParam("limit")
    private Integer maxRecords;

    //----------------------------------------------------------------------------------------------------
    // METODOS AUXILIARES DEL RECURSO
    //----------------------------------------------------------------------------------------------------
    /**
     * Metodo encargado de convertir la entidad a DTO
     *
     * @param listEntity la lista con las entidades
     * @return una lista con DTOs
     */
    public List<ViajeroDTO> listEntity2DTO(List<ViajeroEntity> listEntity)
    {
        List<ViajeroDTO> listDto = new ArrayList<>();
        for (ViajeroEntity entity : listEntity)
        {
            ViajeroDTO ndto = new ViajeroDTO(entity);
            listDto.add(ndto);
        }

        return listDto;
    }

    //----------------------------------------------------------------------------------------------------
    // METODOS GET DEL RECURSO
    //----------------------------------------------------------------------------------------------------
    /**
     * Metodo encargado de obtener todos los DTOs de los viajeros
     *
     * @return
     */
    @GET
    public List<ViajeroDTO> getViajeros()
    {
        return listEntity2DTO(viajeroLogic.getViajeros());
    }

    /**
     * Metodo encargado de retornar un DTO viajero a partir de un id
     *
     * @param id el id de la entidad
     * @return
     */
    @GET
    @Path("{id: \\d+}")
    public ViajeroDetailDTO getViajero(@PathParam("id") Long id) throws WebApplicationException
    {
        ViajeroDetailDTO viajero = new ViajeroDetailDTO(viajeroLogic.getViajero(id));
        if (viajero == null)
        {
            throw new WebApplicationException(404);
        }
        return viajero;
    }

    /**
     * Metodo encargado de llamar a la clase ReservaResourse para retornar las
     * resenas de un viajero respectivo
     *
     * @return la clase ReservaResourse encargada de retornar las resenas de
     * viajero.
     */
    @Path("{viajeroId: \\d+}/reservas")
    public Class<ReservaResource> getReservasViajeo()
    {
        return ReservaResource.class;
    }

    //----------------------------------------------------------------------------------------------------
    // METODOS POST DEL RECURSO
    //----------------------------------------------------------------------------------------------------
    /**
     * Metodo encargado de agregar un nuevo viajero a la base de datos
     *
     * @param dto el nuevo DTO
     * @return el viajero creado
     * @throws BusinessLogicException Exception de las reglas del negocio
     */
    @POST
    public ViajeroDetailDTO crearViajero(ViajeroDetailDTO dto) throws BusinessLogicException
    {
        ViajeroEntity entity = viajeroLogic.createViajero(dto.toEntity());
        return new ViajeroDetailDTO(entity);
    }

    //----------------------------------------------------------------------------------------------------
    // METODOS PUT DEL RECURSO
    //----------------------------------------------------------------------------------------------------    
    /**
     * Metodo encargado de actualizar la informacion de un viajero
     *
     * @param id el id de un viajero
     * @param dto el DTO con la informacion actualizada
     * @return el viajero actualizado
     * @throws BusinessLogicException Exception de las reglas del negocio
     */
    @PUT
    @Path("{id: \\d+}")
    public ViajeroDetailDTO updateViajero(@PathParam("id") Long id, ViajeroDetailDTO dto) throws BusinessLogicException, WebApplicationException
    {
        if (viajeroLogic.getViajero(id) == null)
        {
            throw new WebApplicationException(404);
        }
        ViajeroEntity entity = dto.toEntity();
        entity.setIdUsuario(id);
        return new ViajeroDetailDTO(viajeroLogic.updateViajero(entity));
    }

    //----------------------------------------------------------------------------------------------------
    // METODOS DELETE DEL RECURSO
    //----------------------------------------------------------------------------------------------------    
    /**
     * Elimina un viajero de la base de datos
     *
     * @param id el id del viajero
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteViajero(@PathParam("id") Long id) throws WebApplicationException
    {
        if (viajeroLogic.getViajero(id) == null)
        {
            throw new WebApplicationException(404);
        }
        viajeroLogic.deleteViajero(id);
    }

}
