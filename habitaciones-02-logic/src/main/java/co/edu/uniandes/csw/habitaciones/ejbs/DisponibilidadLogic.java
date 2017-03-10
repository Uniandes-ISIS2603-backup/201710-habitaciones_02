/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.habitaciones.ejbs;

import co.edu.uniandes.csw.habitaciones.entities.DisponibilidadEntity;
import co.edu.uniandes.csw.habitaciones.persistence.DisponibilidadPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author b.gamba10
 */
@Stateless
public class DisponibilidadLogic {

    @Inject
    private DisponibilidadPersistence persistence;

    public List<DisponibilidadEntity> getDisponibilidades() {

        return persistence.findAll();
    }

    public DisponibilidadEntity getDisponibilidad(Long id) {

        return persistence.find(id);
    }

    public DisponibilidadEntity createDisponibilidad(DisponibilidadEntity entity) {

        persistence.create(entity);
        return entity;
    }

    public DisponibilidadEntity updateDisponibilidad(DisponibilidadEntity entity) {

        return persistence.update(entity);
    }

    public void deleteDisponibilidad(Long id) {

        persistence.delete(id);
    }

}
