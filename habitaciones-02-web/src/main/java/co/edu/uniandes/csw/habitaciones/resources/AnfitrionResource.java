/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.habitaciones.resources;

import co.edu.uniandes.csw.habitaciones.dtos.AnfitrionDetailDTO;
import co.edu.uniandes.csw.habitaciones.ejbs.AnfitrionLogic;
import co.edu.uniandes.csw.habitaciones.ejbs.DisponibilidadLogic;
import co.edu.uniandes.csw.habitaciones.ejbs.HabitacionLogic;
import co.edu.uniandes.csw.habitaciones.ejbs.PagoLogic;
import co.edu.uniandes.csw.habitaciones.ejbs.ReservaLogic;
import co.edu.uniandes.csw.habitaciones.ejbs.ViviendaLogic;
import co.edu.uniandes.csw.habitaciones.entities.AnfitrionEntity;
import co.edu.uniandes.csw.habitaciones.entities.DisponibilidadEntity;
import co.edu.uniandes.csw.habitaciones.entities.HabitacionEntity;
import co.edu.uniandes.csw.habitaciones.entities.PagoEntity;
import co.edu.uniandes.csw.habitaciones.entities.ReservaEntity;
import co.edu.uniandes.csw.habitaciones.entities.ViviendaEntity;
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
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author df.sanabria761
 */
@Path("/anfitriones")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AnfitrionResource {

    @Inject
    private AnfitrionLogic logica;
    @Inject
    private ViviendaLogic viviendaLogica;
    @Inject
    private HabitacionLogic habitacionLogic;
    @Inject
    private DisponibilidadLogic dispoLogic;
    @Inject
    private ReservaLogic reservaLogic;
    @Inject
    private PagoLogic pagoLogic;
    
    private List<AnfitrionDetailDTO> listEntity2DTO(List<AnfitrionEntity> entityList) {
        List<AnfitrionDetailDTO> lista = new ArrayList<>();
        for (AnfitrionEntity entity : entityList) {
            AnfitrionDetailDTO nD = new AnfitrionDetailDTO(entity);
            lista.add(nD);
        }

        return lista;
    }

    @GET
    public List<AnfitrionDetailDTO> getAnfitriones() {

        return listEntity2DTO(logica.getAnfitriones());
    }

    @GET
    @Path("{id: \\d+}")
    public AnfitrionDetailDTO getAnfitrion(@PathParam("id") Long id) {
        if (logica.getAnfitrion(id) == null) {
            throw new WebApplicationException(404);
        }
        return new AnfitrionDetailDTO(logica.getAnfitrion(id));
    }

    @POST
    public AnfitrionDetailDTO createAnfitrion(AnfitrionDetailDTO dtoo) throws BusinessLogicException {

        return new AnfitrionDetailDTO(logica.createAnfitrion(dtoo.toEntity()));

    }

    @GET
    @Path("loginViajero")
    public AnfitrionDetailDTO getViajeroPorLogin(@QueryParam("correoE")String correo,
        @QueryParam("contrasena")String contrasena) throws BusinessLogicException
    {
        return new AnfitrionDetailDTO(logica.getAnfitrionLogin(correo, contrasena));
    }

    @PUT
    @Path("{id: \\d+}")
    public AnfitrionDetailDTO updateAnfitrion(AnfitrionDetailDTO dto, @PathParam("id") Long id) {
        if (logica.getAnfitrion(id) == null) {
            throw new WebApplicationException(404);
        }
        AnfitrionEntity en = dto.toEntity();
        en.setIdUsuario(id);
        return new AnfitrionDetailDTO(logica.updateAnfitrion(en));
    }

    @DELETE
    @Path("{id: \\d+}")
    public void deleteAnfitrion(@PathParam("id") Long id) {
        
        if (logica.getAnfitrion(id) == null) {
            throw new WebApplicationException(404);
        }

        logica.deleteAnfitrion(id);
    }

}
