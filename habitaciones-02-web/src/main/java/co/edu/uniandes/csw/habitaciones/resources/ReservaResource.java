/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.habitaciones.resources;

import co.edu.uniandes.csw.habitaciones.dtos.HabitacionDetailDTO;
import co.edu.uniandes.csw.habitaciones.dtos.ReservaDetailDTO;
import co.edu.uniandes.csw.habitaciones.ejbs.ReservaLogic;
import co.edu.uniandes.csw.habitaciones.entities.ReservaEntity;
import co.edu.uniandes.csw.habitaciones.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author dg.guarin20
 */
@Path("/reservas")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ReservaResource {

    /**
     * la logica de reserva
     */
    @Inject
    private ReservaLogic logic;

    /**
     * Se encarga de convertirla entidad a dto y retorna una lista de esta
     *
     * @param listEntity
     * @return List ReservaDetailDTO
     */
    private List<ReservaDetailDTO> listEntity2DTO(List<ReservaEntity> listEntity) {
        List<ReservaDetailDTO> lista = new ArrayList<>();
        for (ReservaEntity entity : listEntity) {

            lista.add(new ReservaDetailDTO(entity));
        }
        return lista;
    }

    /**
     * @GET public List<ReservaDetailDTO> getReservas() {
     *
     * return listEntity2DTO(logic.findReservas()); }
     *
     */
    /**
     * Metodo que retorna una lista de las reservas relacionado al id del
     * viajero entrado por parametro
     *
     * @param idViajero
     * @return List ReservaDetailDTO
     */
    //@GET
    //public List<ReservaDetailDTO> getReservasByViajero(@PathParam("viajeroId") Long idViajero) {
      //  return listEntity2DTO(logic.findReservasByViajero(idViajero));
    //}

    @GET
    public List<ReservaDetailDTO> getReservas() {

        return listEntity2DTO(logic.findReservas());
    }
    /**
     * encargado de buscar la reserva con el id introducido
     *
     * @param id
     * @return ReservaDetailDTO
     */
    @GET
    @Path("{id: \\d+}")
    public ReservaDetailDTO getReserva(@PathParam("id") Long id) {
        // TODO Si la reserva  no existe debe disparar WebApplicationException 404
        if (logic.findReserva(id) == null) {
            throw new WebApplicationException(404);
        }
        return new ReservaDetailDTO(logic.findReserva(id));
    }

    /**
     * Busca la habitacion con el id de reservas
     *
     * @return clase HabitacionResource
     */
    @Path("{habitacionId: \\d+}/habitaciones")
    public Class<HabitacionResource> getHabitacionReservas() {
        return HabitacionResource.class;
    }

    /**
     * Metodo encargado de crear una reserva
     *
     * @param dto
     * @return ReservaDetailDTO
     * @throws BusinessLogicException
     */
    @POST
    public ReservaDetailDTO createReserva(ReservaDetailDTO dto) throws BusinessLogicException {
        return new ReservaDetailDTO(logic.createReserva(dto.toEntity()));
    }

    /**
     * Metodo de actualizar una reserva buscado por su parametro
     *
     * @param id
     * @param dto
     * @return ReservaDetailDTO
     * @throws BusinessLogicException
     */
//    @PUT
//    @Path("{id: \\d+}")
//    public ReservaDetailDTO updateReserva(@PathParam("id") Long id, ReservaDetailDTO dto) throws BusinessLogicException {
//        // TODO Si la reserva  no existe debe disparar WebApplicationException 404
//        if (logic.findReserva(id) == null) {
//            throw new WebApplicationException(404);
//        }
//        ReservaEntity entity = dto.toEntity();
//        entity.setId(id);
//        return new ReservaDetailDTO(logic.updateReserva(entity));
//    }
    @PUT
    @Path("{id: \\d+}")
    public ReservaDetailDTO updateReserva(@PathParam("id") Long id, ReservaDetailDTO dto) throws BusinessLogicException {
        // TODO Si la reserva  no existe debe disparar WebApplicationException 404
        if (logic.findReserva(id) == null) {
            throw new WebApplicationException(404);
        }
        ReservaEntity entity = dto.toEntity();
        entity.setId(id);
        return new ReservaDetailDTO(logic.updateReserva(entity));
    }
    @PUT
    @Path("{id: \\d+}/algo")
    public ReservaDetailDTO updatecancelado(@PathParam("id") Long id) throws BusinessLogicException{
        
        if(logic.findReserva(id) == null)
        {
            throw new WebApplicationException(404);
        }
        
        ReservaEntity entity =  logic.findReserva(id);
        Boolean nuevo = entity.getCancelado();
        if(!nuevo)
        {
            nuevo = true;
        }
        else
        {
            nuevo = false;
        }
        entity.setId(id);
        entity.setCancelado(nuevo);
        return new ReservaDetailDTO(logic.updateCancelado(entity));
        
        
    }
    /**
     * Elimina una reserva buscado por su id
     *
     * @param id
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteReserva(@PathParam("id") Long id) {
        // TODO Si la reserva  no existe debe disparar WebApplicationException 404
        if (logic.findReserva(id) == null) {
            throw new WebApplicationException(404);
        }
        logic.delete(id);
    }

    //TODO cómo se puede saber cuáles son las reservas de un ahabitación?
}
