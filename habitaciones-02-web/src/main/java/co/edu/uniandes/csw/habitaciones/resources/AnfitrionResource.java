/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.habitaciones.resources;

import co.edu.uniandes.csw.habitaciones.dtos.AnfitrionDetailDTO;
import co.edu.uniandes.csw.habitaciones.ejbs.AnfitrionLogic;
import co.edu.uniandes.csw.habitaciones.entities.AnfitrionEntity;
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
 * @author df.sanabria761
 */
@Path("/anfitriones")

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AnfitrionResource 
{
    @Inject
    private AnfitrionLogic logica;
    
    private List<AnfitrionDetailDTO> listEntityToDTO(List<AnfitrionEntity> entityList) {
        List<AnfitrionDetailDTO> lista = new ArrayList();
        for(AnfitrionEntity entity : entityList){
            lista.add(new AnfitrionDetailDTO(entity));
        }
        
        return lista;
    }
    
    @GET
    private List<AnfitrionDetailDTO> getAnfitriones()
    {
        return listEntityToDTO(logica.getAnfitriones());
    }
    
    @GET
    @Path("{id: \\d+}")
    private AnfitrionDetailDTO getAnfitrion(@PathParam("id") Long id)
    {
        return new AnfitrionDetailDTO (logica.getAnfitrion(id));
    }
    @POST
    private AnfitrionDetailDTO createAnfitrion(AnfitrionDetailDTO dtoo)
    {
        return new AnfitrionDetailDTO (logica.createAnfitrion(dtoo.toEntity()));
    }
    
    @PUT
    @Path("{id: \\d+}")
    private AnfitrionDetailDTO updateAnfitrion(AnfitrionDetailDTO dto, @PathParam("id") Long id)
    {
        AnfitrionEntity en = dto.toEntity();
        en.setIdUsuario(id);
        return new AnfitrionDetailDTO(logica.updateAnfitrion(en));
    }
    
    @DELETE
    @Path("{id: \\d+}")
    private void deleteAnfitrion(@PathParam("id") Long id)
    {
        logica.deleteAnfitrion(id);
    }
    
}
