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

        if (entity.getArea() < 0) {
            throw new BusinessLogicException("El area debe ser mayor que cero.");
        }
        if (entity.getDescripcion() == null || entity.getDescripcion().equals("")) {
            throw new BusinessLogicException("Debe escribir la descripcion de la habitacion.");
        }
        if (entity.getRutaImagen() == null) {
            throw new BusinessLogicException("Debe ingresar la ruta de la imagen de la habitacion.");
        }
        if (entity.getValorAlquiler() < 0) {
            throw new BusinessLogicException("El valor del alquiler debe ser mayor que cero.");
        } else {
            persistence.create(entity);
            return entity;
        }
    }

    public HabitacionEntity updateHabitacion(HabitacionEntity entity) throws BusinessLogicException {

        if (entity.getArea() < 0) {
            throw new BusinessLogicException("El area debe ser mayor que cero.");
        }
        if (entity.getDescripcion() == null || entity.getDescripcion().equals("")) {
            throw new BusinessLogicException("Debe escribir la descripcion de la habitacion.");
        }
        if (entity.getRutaImagen() == null) {
            throw new BusinessLogicException("Debe ingresar la ruta de la imagen de la habitacion.");
        }
        if (entity.getValorAlquiler() < 0) {
            throw new BusinessLogicException("El valor del alquiler debe ser mayor que cero.");
        } else {
            return persistence.update(entity);
        }
    }

    public void deleteHabitacion(Long id) {

        persistence.delete(id);
    }
}
