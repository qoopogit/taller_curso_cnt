/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.gob.cnt.sysmed.converters;

import ec.gob.cnt.sysmed.controllers.AbstractFacade;
import ec.gob.cnt.sysmed.controllers.ClienteFacade;
import ec.gob.cnt.sysmed.entities.Cliente;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Alberto
 */
@Named("clienteConverter") //Soporte la @Inject
@RequestScoped
public class ClienteConverter extends ConvGenerico<Cliente> {

    @Inject
    private ClienteFacade facade;

    @Override
    protected AbstractFacade<Cliente> getAdminGenerico() {
       return facade;
    }

}
