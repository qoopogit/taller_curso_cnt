/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.gob.cnt.sysmed.converters;

import ec.gob.cnt.sysmed.controllers.AbstractFacade;
import ec.gob.cnt.sysmed.controllers.ProductoFacade;
import ec.gob.cnt.sysmed.entities.Producto;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Alberto
 */
@Named("productoConverter") //Soporte la @Inject
@RequestScoped
public class ProductoConverter extends ConvGenerico<Producto> {

    @Inject
    private ProductoFacade facade;

    @Override
    protected AbstractFacade<Producto> getAdminGenerico() {
       return facade;
    }

}
