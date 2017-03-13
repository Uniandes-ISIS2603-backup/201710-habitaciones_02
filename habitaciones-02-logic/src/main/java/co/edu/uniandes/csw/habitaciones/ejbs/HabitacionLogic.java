/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.habitaciones.ejbs;

import co.edu.uniandes.csw.habitaciones.entities.HabitacionEntity;
import co.edu.uniandes.csw.habitaciones.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.habitaciones.persistence.HabitacionPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author b.gamba10
 */
@Stateless
public class HabitacionLogic {

    @Inject
    private HabitacionPersistence persistence;

    public List<HabitacionEntity> getHabitaciones() {

        return persistence.findAll();
    }

    public HabitacionEntity getHabitacion(Long id) {

        return persistence.find(id);
    }

    public HabitacionEntity createHabitacion(HabitacionEntity entity) throws BusinessLogicException {

        String problemas = "Se generaron errores al intentar agregar una habitacion:\n";
        Boolean problema = false;

        if (!entity.informacionCompleta()) {
            problemas += "La informacion no esta completa:\n"
                    + "   -Area: <" + entity.getArea().toString() + ">\n"
                    + "   -Valor Alquier: <" + entity.getValorAlquiler().toString() + ">\n"
                    + "   -Ruta imagen: <" + entity.getRutaImagen() + ">\n"
                    + "   -Descripcion: <" + entity.getDescripcion() + ">\n";
            problema = true;
        } else {
            if (entity.getArea() <= 0) {
                problemas += "El area debe ser mayor que cero. \n";
                problema = true;
            }

            if (entity.getValorAlquiler() <= 0) {
                problemas += "El valor del alquiler debe ser mayor que cero.";
                problema = true;
            }

        }

        if (problema) {
            throw new BusinessLogicException(problemas);
        }

        persistence.create(entity);
        return entity;

    }

    public HabitacionEntity updateHabitacion(HabitacionEntity entity) throws BusinessLogicException {

        String problemas = "Se generaron errores al intentar actualizar la habitacion:\n";
        Boolean problema = false;

        if (!entity.informacionCompleta()) {
            problemas += "La informacion no esta completa:\n"
                    + "   -Area: <" + entity.getArea().toString() + ">\n"
                    + "   -Valor Alquier: <" + entity.getValorAlquiler().toString() + ">\n"
                    + "   -Ruta imagen: <" + entity.getRutaImagen() + ">\n"
                    + "   -Descripcion: <" + entity.getDescripcion() + ">\n";
            problema = true;
        } else {
            if (entity.getArea() <= 0) {
                problemas += "El area debe ser mayor que cero. \n";
                problema = true;
            }

            if (entity.getValorAlquiler() <= 0) {
                problemas += "El valor del alquiler debe ser mayor que cero.";
                problema = true;
            }

        }

        if (problema) {
            throw new BusinessLogicException(problemas);
        }

        return persistence.update(entity);

    }

    public void deleteHabitacion(Long id) {

        persistence.delete(id);
    }
}
