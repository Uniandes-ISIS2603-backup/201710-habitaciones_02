/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.habitaciones.ejbs;

import co.edu.uniandes.csw.habitaciones.entities.PagoEntity;
import co.edu.uniandes.csw.habitaciones.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.habitaciones.persistence.PagoPersistence;
import java.util.Date;
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
    
    public PagoEntity createPago(PagoEntity pago) throws BusinessLogicException
    {
        if(pago.getPago() < 0)
        {
            throw new BusinessLogicException("el pago debe ser mayor a 0");
        }
        else if(pago.getFechaDePago().after(new Date()))
        {
            throw new BusinessLogicException("la fecha de pago no puede ser mayor a la actual");
        }
        else if(!pago.informacionCompleta())
        {
            throw new BusinessLogicException("la informacion no esta completa");
        }
        else
        {
        persistence.create(pago);
        return pago;
        }
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
