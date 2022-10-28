/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.gob.cnt.sysmed.controllers;

import ec.gob.cnt.sysmed.entities.Factura;
import ec.gob.cnt.sysmed.entities.Producto;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Alberto
 */
//No-view-interface
@Stateless //EJB Sin estado @Stateful con estado (shopping cart)
public class ProductoFacade extends AbstractFacade<Producto> {

    //Identifico cual es la unidad de persistencia
    @PersistenceContext(unitName = "sysmedPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProductoFacade() {
        super(Producto.class);
    }

    /**
     * Método para cargar las especialidades por SP
     *
     * @return
     */
    public List<Producto> cargarTodo() {
//        return em.createNamedStoredProcedureQuery("obtenerEspecialidades").getResultList();
        TypedQuery<Producto> conHisNum = em.createNamedQuery("Producto.findAll", Producto.class);
        return conHisNum.getResultList();
    }

}
