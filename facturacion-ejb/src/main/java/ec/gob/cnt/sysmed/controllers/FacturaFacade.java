/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.gob.cnt.sysmed.controllers;

import ec.gob.cnt.sysmed.entities.Factura;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Alberto
 */
@Stateless
public class FacturaFacade extends AbstractFacade<Factura> {

    @PersistenceContext(unitName = "sysmedPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FacturaFacade() {
        super(Factura.class);
    }

    /**
     * Busca por numero de factura
     *
     * @param numeroFactura
     * @return
     */
    public List<Factura> buscarPorNumero(String numeroFactura) {
        TypedQuery<Factura> conHisNum = em.createQuery("Select his from Factura his "
                + " where his.numero like :numero", Factura.class);
        conHisNum.setParameter("numero", numeroFactura + "%");
        return conHisNum.getResultList();
    }

}
