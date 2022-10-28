/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.gob.cnt.sysmed.converters;

import ec.gob.cnt.sysmed.controllers.FacturaFacade;
import ec.gob.cnt.sysmed.entities.Factura;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Alberto
 */
@Named("facturaConverter") //Soporte la @Inject
@RequestScoped
public class FacturaConverter implements Converter<Factura> {

    @Inject
    private FacturaFacade facade;

    //Pantalla a la Bdd
    @Override
    public Factura getAsObject(FacesContext fc, UIComponent uic, String idTipPac) {
        Factura tipoPaciente = null;
        if (idTipPac != null) {
            tipoPaciente = facade.buscarPorId(Integer.valueOf(idTipPac));
        }
        return tipoPaciente;
    }

    //Bdd a la Pantalla
    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Factura tipoPac) {
        String idTipPac = "";
        if (tipoPac != null) {
            idTipPac = tipoPac.getId().toString();
        }
        return idTipPac;
    }

}
