/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.gob.cnt.sysmed.beans;

import ec.gob.cnt.sysmed.beans.util.AbstractManagedBean;
import ec.gob.cnt.sysmed.controllers.ProductoFacade;
import ec.gob.cnt.sysmed.entities.Producto;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author Alberto
 */
@Named
@ViewScoped
public class ProductoBean extends AbstractManagedBean implements Serializable {

    @Getter
    @Setter
    private Producto objeto;
    @Getter
    @Setter
    private Producto seleccionado; //Seleccionar una fila en la tabla

    @Getter
    @Setter
    private List<Producto> lista;

    @Inject
    private ProductoFacade adminProducto;

    public ProductoBean() {
        this.objeto = new Producto();
        this.lista = new ArrayList<>();
    }

    /**
     * Método para guardar o actualizar un tipo de paciente
     */
    public void guardar() {
        try {
            if (objeto.getId() != null) {
                //Llamando al controlador (EJB)
                adminProducto.actualizar(objeto);
            } else {
                adminProducto.guardar(objeto);
            }
            anadirInfo("Guardado correctamente");
            cargar();
            resetearFormulario();
        } catch (Exception e) {
            //Mensaje de error
            anadirError("Error al procesar operación:" + e.getMessage());
        }
    }

    /**
     * Método para cargar los tipos de pacientes
     */
    private void cargar() {
        this.lista = adminProducto.buscarTodos();
    }

    /**
     * Seleccionar una fila (tipo de paciente) de la tabla
     *
     * @param ev
     */
    public void seleccionarFila(SelectEvent<Producto> ev) {
        this.seleccionado = ev.getObject();
    }

    /**
     * Método para cargar el tipo de paciente
     */
    public void editar() {
        if (seleccionado != null) {
            this.objeto = seleccionado;
        } else {
            anadirError("Se debe seleccionar un registro");
        }
    }

    /**
     * Método para eliminar un tipo de paciente
     */
    public void eliminar() {
        try {
            if (seleccionado != null) {
                adminProducto.eliminar(seleccionado);
                anadirInfo("Tipo de paciente elminado correctamente");
            } else {
                anadirError("Se debe seleccionar un registro");
            }
            resetearFormulario();
            cargar();
        } catch (Exception e) {
            anadirError("Error al eliminar un tipo de paciente:" + e.getMessage());
        }
    }

    /**
     * Método para limpiar el formulario
     */
    public void resetearFormulario() {
        this.objeto = new Producto();
        this.seleccionado = null;
    }

    @PostConstruct
    public void inicializar() {
        cargar();
    }
}
