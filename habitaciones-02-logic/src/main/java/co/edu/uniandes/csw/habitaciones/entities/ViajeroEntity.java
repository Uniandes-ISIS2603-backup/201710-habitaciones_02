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
package co.edu.uniandes.csw.habitaciones.entities;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author s.cortes
 */
@Entity
@PrimaryKeyJoinColumn(referencedColumnName = "idUsuario")
public class ViajeroEntity extends UsuarioEntity {
    //----------------------------------------------------------------------------------------------------
    // ATRIBUTOS
    //----------------------------------------------------------------------------------------------------

    /**
     * Relacion One-to-Many con las Reservas
     */
    @PodamExclude
    @OneToMany(mappedBy = "viajero")
    private List<ReservaEntity> reservas;

    /**
     * Relacion One-to-Many con las reseñas
     */
    @PodamExclude
    @OneToMany(mappedBy = "viajero")
    private List<ResenaEntity> resenas;

    //----------------------------------------------------------------------------------------------------
    // METODO
    //----------------------------------------------------------------------------------------------------
    /**
     * Retorna las resevas del viajero
     *
     * @return lista de reservas
     */
    public List<ReservaEntity> getReservas() {
        return reservas;
    }

    /**
     * asigna las reservas del viajero
     *
     * @param reservas la lista de reservas a asignar
     */
    public void setReservas(List<ReservaEntity> reservas) {
        this.reservas = reservas;
    }

    /**
     * retorna la lista de reseñas
     *
     * @return lista de reseñas
     */
    public List<ResenaEntity> getResenas() {
        return resenas;
    }

    /**
     * asigna las reseñas del viajero
     *
     * @param resenas la lista de las reseñas a asignar
     */
    public void setResenas(List<ResenaEntity> resenas) {
        this.resenas = resenas;
    }

}
