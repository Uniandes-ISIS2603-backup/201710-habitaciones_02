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
        // TODO se debe verificar que el pago esté asociado con una reserva
        String problemas = "Se generaron errores al intentar ingresar el registro de pago:\n";
        Boolean problema = false;
        
        if(!pago.informacionCompleta())
        {
            problemas += "la informacion no esta completa:\n" 
                            + "   -Pago: <"+pago.getPago()+">\n"
                            + "   -Fecha: <"+pago.getFechaDePago().toString()+">\n"
                            + "   -Tipo: <"+pago.getTipoTramite()+">\n";
            problema = true;
        }
        else
        {
            if (pago.getPago() < 0) 
            {
                problemas += "el pago debe ser mayor a 0.\n";
                problema = true;
            }
            if (pago.getFechaDePago().after(new Date())) 
            {
                problemas += "la fecha de pago no puede ser mayor a la actual.\n";
                problema = true;
            }
        }


        if(problema)
        {
            throw new BusinessLogicException(problemas);
        }
        
        persistence.create(pago);
        return pago;

    }
    
    public PagoEntity updatePago(PagoEntity pago)
    {// TODO se debe verificar que el pago esté asociado con una reserva
        // Se debe volver a verificar las fechas, ...
        return persistence.update(pago);
    }
    
    public void deletePago( Long id )
    {
        persistence.delete(id);
    }
}
