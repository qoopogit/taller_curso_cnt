/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.gob.cnt.sysmed.beans;

import ec.gob.cnt.sysmed.beans.util.AbstractManagedBean;
import ec.gob.cnt.sysmed.controllers.ClienteFacade;
import ec.gob.cnt.sysmed.entities.Cliente;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.PrimeFaces;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author Alberto
 */
@Named
@SessionScoped
public class ClienteBean extends AbstractManagedBean implements Serializable {

    @Getter
    @Setter
    private Cliente objeto;
    @Getter
    @Setter
    private Cliente seleccionado;
    @Getter
    @Setter
    private List<Cliente> lista;

    @Getter
    @Setter
    private String criterioBusqueda;

    @Getter
    @Setter
    private String mascaraIden;
    @Getter
    @Setter
    private String pathImagen;
    @Getter
    @Setter
    private StreamedContent imagenBinaria;

    @Inject
    private ClienteFacade adminPaciente;

    public ClienteBean() {
        this.objeto = new Cliente();
        this.lista = new ArrayList<>();

        this.mascaraIden = "9999999999";
        this.pathImagen = "/resources/img/usuario.webp";
    }

    /**
     * Método para buscar pacientes
     */
    public void buscar() {
        try {
            this.lista = adminPaciente.buscarNombresIdentificacion(criterioBusqueda);
            if (lista.isEmpty()) {
                anadirInfo("No existen pacientes con ese criterio");
            }
        } catch (Exception e) {
            anadirError("Error al buscar los pacientes con ese criterio:" + e.getMessage());
        }
    }

    /**
     * Método para guardar o actualizar un objeto
     */
    public void guardar() {
        try {
            if (objeto.getId() != null) {
                adminPaciente.actualizar(objeto);
            } else {
                adminPaciente.guardar(objeto);
            }
            anadirInfo("Guardado correctamente");
            resetearFormulario();
        } catch (Exception e) {
            anadirError("Error al procesar la operación:" + e.getMessage());
        }
    }

    /**
     * Método para guardar o actualizar un objeto
     */
    public void getId() {
        try {
            if (objeto.getId() != null) {
                adminPaciente.actualizar(objeto);
            } else {
                adminPaciente.guardar(objeto);
            }
            anadirInfo("Guardado correctamente");
            resetearFormulario();
        } catch (Exception e) {
            anadirError("Error al procesar la operación:" + e.getMessage());
        }
    }

    /**
     * Método para seleccionar un objeto
     *
     * @param ev
     */
    public void seleccionarFila(SelectEvent<Cliente> ev) {
        this.seleccionado = ev.getObject();
    }

    /**
     * Método para cargar un objeto
     */
    public void editar() {
        if (seleccionado != null) {
            this.objeto = seleccionado;

            // Actuaizar Imagen
            try {
                InputStream fis = new ByteArrayInputStream(objeto.getFoto());
                imagenBinaria = DefaultStreamedContent.builder().stream(() -> fis).build();
            } catch (Exception e) {

            }
            PrimeFaces.current().executeScript("PF('diaNuePac').show();");
        } else {
            anadirError("Se debe seleccionar un paciente");
        }
    }

    /**
     * Método para eliminar un objeto
     */
    public void eliminar() {
        if (seleccionado != null) {
            adminPaciente.eliminar(seleccionado);
            anadirInfo("Paciente eliminado correctamente");
            buscar();
            resetearFormulario();
        } else {
            anadirError("Se debe seleccionar un paciente");
        }
    }

    /**
     * Método para subir una imagen
     */
    public void subirImagen(FileUploadEvent event) {
        InputStream fis = new ByteArrayInputStream(event.getFile().getContent());
        imagenBinaria = DefaultStreamedContent.builder().stream(() -> fis).build();
        this.objeto.setFoto(event.getFile().getContent());
    }

    /**
     * Método para resetear el formulario
     */
    public void resetearFormulario() {
        this.objeto = new Cliente();
        this.seleccionado = null;
        this.criterioBusqueda = null;
    }

    @PostConstruct
    public void inicializar() {
        // cargarTipoPacientes();
    }

}
