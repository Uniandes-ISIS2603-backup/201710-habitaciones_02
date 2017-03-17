/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.habitaciones.resources;

import co.edu.uniandes.csw.habitaciones.dtos.ReservaDetailDTO;
import co.edu.uniandes.csw.habitaciones.ejbs.ReservaLogic;
import co.edu.uniandes.csw.habitaciones.entities.ReservaEntity;
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
import javax.ws.rs.core.MediaType;

/**
 *
 * @author dg.guarin20
 */
@Path("/reservas")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ReservaResource {

    @Inject
    private ReservaLogic logic;

    private List<ReservaDetailDTO> listEntity2DTO(List<ReservaEntity> listEntity) {
        List<ReservaDetailDTO> lista = new ArrayList<>();
        for (ReservaEntity entity : listEntity) {

            lista.add(new ReservaDetailDTO(entity));
        }
        return lista;
    }

    @GET
    public List<ReservaDetailDTO> getReservas() {

        return listEntity2DTO(logic.findReservas());
    }

    @GET
    @Path("{id: \\d+}")
    public ReservaDetailDTO getReserva(@PathParam("id") Long id) {
        // TODO Si la reserva  no existe debe disparar WebApplicationException 404
        return new ReservaDetailDTO(logic.findReserva(id));
    }

    @POST
    public ReservaDetailDTO createReserva(ReservaDetailDTO dto) 
    {
        return new ReservaDetailDTO(logic.createReserva(dto.toEntity()));
    }

    @PUT
    @Path("{id: \\d+}")
    public ReservaDetailDTO updateReserva(@PathParam("id") Long id, ReservaDetailDTO dto) {
         // TODO Si la reserva  no existe debe disparar WebApplicationException 404
        ReservaEntity entity = dto.toEntity();
        entity.setId(id);
        return new ReservaDetailDTO(logic.updateReserva(entity));
    }

    @DELETE
    @Path("{id: \\d+}")
    public void deleteReserva(@PathParam("id") Long id) {
         // TODO Si la reserva  no existe debe disparar WebApplicationException 404
        logic.delete(id);
    }
    
    //TODO cómo se puede saber cuáles son las reservas de un ahabitación?
}
