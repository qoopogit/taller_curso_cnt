/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.gob.cnt.sysmed.controllers;

import ec.gob.cnt.sysmed.entities.Cliente;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Alberto
 */
@Stateless
public class ClienteFacade extends AbstractFacade<Cliente> {

    @PersistenceContext(unitName = "sysmedPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ClienteFacade() {
        super(Cliente.class);
    }

    /**
     * Método para buscar un paciente por identificación o su apellido paterno
     *
     * @param valor
     * @return
     */
    public List<Cliente> buscarNombresIdentificacion(String valor) {
        Query conPac = em.createNativeQuery("select p.*\n"
                + "from cliente p \n"
                + "where p.identificacion like ?1 or \n"
                + "      p.nombres  like ?1", Cliente.class);
        conPac.setParameter(1, "%" + valor + "%");
        return conPac.getResultList();
    }

}
