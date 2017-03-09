/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.habitaciones.ejbs;

import co.edu.uniandes.csw.habitaciones.entities.PagoEntity;
import co.edu.uniandes.csw.habitaciones.persistence.PagoPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author ne.cabrera
 */
@Stateless
public class PagoLogic 
{
     @Inject private PagoPersistence persistence;
    
    public List<PagoEntity> getPagos()
    {
        return persistence.findAll();
    }
    
    public PagoEntity getPago(Long id)
    {
        return persistence.find(id);
    }
    
    public PagoEntity createPago(PagoEntity pago)
    {
        persistence.create(pago);
        return pago;
    }
    
    public PagoEntity updatePago(PagoEntity pago)
    {
        return persistence.update(pago);
    }
    
    public void deletePago( Long id )
    {
        persistence.delete(id);
    }
}
