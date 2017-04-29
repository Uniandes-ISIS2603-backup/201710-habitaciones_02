/* 
 * The MIT License
 *
 * Copyright 2017 Los Favoritos.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
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
public class PagoLogic {

    /**
     * persistencia para la entidad del pago
     */
    @Inject
    private PagoPersistence persistence;

    /**
     * Retorna una lista con todos los pagos en la base de datos
     *
     * @return
     */
    public List<PagoEntity> getPagos() {
        return persistence.findAll();
    }

    /**
     * Retorna el pago cullo id es igual al dado por parametro
     *
     * @param id
     * @return pago entity con id igual al dado por parametro
     */
    public PagoEntity getPago(Long id) {
        return persistence.find(id);
    }

    /**
     * @param pago
     * @return El pago que fue agregado
     * @throws BusinessLogicException si la informacion del pago no esta
     * completa, el pago es menor a 0, la fecha de pago es mayor a la actual o
     * el pago no esta asociado a una reserva
     */
    public PagoEntity createPago(PagoEntity pago) throws BusinessLogicException {
        // TODO se debe verificar que el pago esté asociado con una reserva
        String problemas = "Se generaron errores al intentar ingresar el registro de pago:\n";
        Boolean problema = false;

        if (!pago.informacionCompleta()) {
            problemas += "la informacion no esta completa:\n"
                    + "   -Pago: <" + pago.getPago() + ">\n"
                    + "   -Fecha: <" + pago.getFechaDePago().toString() + ">\n"
                    + "   -Tipo: <" + pago.getTipoTramite() + ">\n";
            problema = true;
        } else {
            if (pago.getPago() < 0) {
                problemas += "el pago debe ser mayor a 0.\n";
                problema = true;
            }
            if (pago.getFechaDePago().after(new Date())) {
                problemas += "la fecha de pago no puede ser mayor a la actual.\n";
                problema = true;
            }
            if (pago.getReserva() == null) {
                problemas += "el pago debe estar asociado a una reserva.\n";
                problema = true;
            }
        }
        if (problema) {
            throw new BusinessLogicException(problemas);
        }

        persistence.create(pago);
        return pago;

    }

    /**
     * @param pago el pago a actualizar
     * @return el pago actualizado
     * @throws BusinessLogicException si el pago no esta asociado a una reserva
     * o si la fecha de pago es mayor a la actual
     */
    public PagoEntity updatePago(PagoEntity pago) throws BusinessLogicException {// TODO se debe verificar que el pago esté asociado con una reserva
        // Se debe volver a verificar las fechas, ...
        String problemas = "Se generaron errores al intentar actualizar el registro de pago:\n";
        boolean problema = false;
        if (pago.getFechaDePago().after(new Date())) {
            problemas += "la fecha de pago no puede ser mayor a la actual.\n";
            problema = true;
        }
        if (problema) {
            throw new BusinessLogicException(problemas);
        }
        return persistence.update(pago);
    }

    /**
     * @param id el id del pago a eliminar
     */
    public void deletePago(Long id) {
        persistence.delete(id);
    }
}
