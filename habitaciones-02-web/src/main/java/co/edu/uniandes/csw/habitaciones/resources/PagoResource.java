/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.habitaciones.resources;

import co.edu.uniandes.csw.habitaciones.dtos.PagoDTO;
import co.edu.uniandes.csw.habitaciones.ejbs.PagoLogic;
import co.edu.uniandes.csw.habitaciones.entities.PagoEntity;
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
 * @author ne.cabrera
 */
@Path("/pagos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PagoResource {

    /**
     * la logica del pago
     */
    @Inject
    private PagoLogic pagoLogic;
    @Context
    private HttpServletResponse response;
    @QueryParam("page")
    private Integer page;
    @QueryParam("limit")
    private Integer maxRecords;

    /**
     * @param entityList lista de pagoEntity
     * @return lista de pago DTO
     */
    private List<PagoDTO> listEntity2DTO(List<PagoEntity> entityList) {
        List<PagoDTO> list = new ArrayList<>();
        for (PagoEntity entity : entityList) {
            list.add(new PagoDTO(entity));
        }
        return list;
    }

    /**
     * obtiene todos los pagos como DTO
     *
     * @return lista de pagoDTO
     */
    @GET
    public List<PagoDTO> getPagos() {
        return listEntity2DTO(pagoLogic.getPagos());
    }

    /**
     * @param id del pago buscado
     * @return el pago con un id igual al dado por parametro
     */
    @GET
    @Path("{id: \\d+}")
    public PagoDTO getPago(@PathParam("id") Long id) {  // TODO Si el pago no existe debe disparar WebApplicationException 404
        PagoEntity pago = pagoLogic.getPago(id);
        if (pago == null) {
            throw new WebApplicationException(404);
        }
        return new PagoDTO(pago);
    }

    /**
     * @param dto pagoDetailDTO a agregar
     * @return el pago agregado
     * @throws BusinessLogicException si la informacion del pago no esta
     * completa, el pago es menor a 0, la fecha de pago es mayor a la actual o
     * el pago no esta asociado a una reserva
     */
    @POST
    public PagoDTO createPago(PagoDTO dto) throws BusinessLogicException {
        return new PagoDTO(pagoLogic.createPago(dto.toEntity()));
    }

    /**
     *
     * @param id id del pago a actualizar
     * @param dto pago dto a actualizar
     * @return el pago actualizado
     * @throws BusinessLogicException si el pago no esta asociado a una reserva
     * o si la fecha de pago es mayor a la actual
     */
    @PUT
    @Path("{id: \\d+}")
    public PagoDTO updatePago(@PathParam("id") Long id, PagoDTO dto) throws BusinessLogicException {// TODO Si el pago no existe debe disparar WebApplicationException 404
        PagoEntity pago = pagoLogic.getPago(id);
        if (pago == null) {
            throw new WebApplicationException(404);
        }
        PagoEntity entity = dto.toEntity();
        entity.setId(id);
        return new PagoDTO(pagoLogic.updatePago(entity));
    }

    /**
     * @param id el id del pago a eliminar
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deletePago(@PathParam("id") Long id) {// TODO Si el pago no existe debe disparar WebApplicationException 404
        PagoEntity pago = pagoLogic.getPago(id);
        if (pago == null) {
            throw new WebApplicationException(404);
        }
        pagoLogic.deletePago(id);
    }

}
