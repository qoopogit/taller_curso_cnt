/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.gob.cnt.sysmed.beans;

import java.io.Serializable;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.extensions.model.layout.LayoutOptions;

/**
 *
 * @author Alberto
 */
@Named
@ViewScoped
public class PrincipalBean implements Serializable {

    @Getter
    @Setter
    private LayoutOptions layoutOptions;

    public PrincipalBean() {
        layoutOptions = new LayoutOptions();

        // options for all panes (center and west)
        LayoutOptions paneMenu = new LayoutOptions();
        paneMenu.addOption("slidable", false);
        layoutOptions.setWestOptions(paneMenu);
    }

}
