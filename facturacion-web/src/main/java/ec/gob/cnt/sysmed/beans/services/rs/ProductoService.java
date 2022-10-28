/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.gob.cnt.sysmed.beans.services.rs;

import ec.gob.cnt.sysmed.controllers.ProductoFacade;
import ec.gob.cnt.sysmed.entities.Producto;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Servicio Web para administrar las operaciones de Tipo de Paciente
 *
 * @author Alberto
 */
@Path("/producto")
@Consumes(value = MediaType.APPLICATION_JSON)
@Produces(value = MediaType.APPLICATION_JSON)
public class ProductoService {

    @Inject
    private ProductoFacade adminProducto;

    @GET
    @Path("/{id}")
    public Producto consultarPorId(@PathParam(value = "id") int idProducto) {
        return adminProducto.buscarPorId(idProducto);
    }

    @GET
    @Path("/consultarTodos")
    public List<Producto> consultarTodos() {
        return adminProducto.buscarTodos();
    }

    @POST
    public String guardar(Producto producto) {
        try {
            adminProducto.guardar(producto);
            return "Producto guardado correctamente";
        } catch (Exception e) {
            return "Error al Guardar:" + e.getMessage();
        }
    }

    @PUT
    public String actualizar(Producto producto) {
        try {
            adminProducto.actualizar(producto);
            return "Producto actualizado correctamente";
        } catch (Exception e) {
            return "Error al Actualizar:" + e.getMessage();
        }
    }

    @DELETE
    @Path("/{id}")
    public String eliminar(@PathParam(value = "id")int idProducto) {
        try {
            Producto producto = adminProducto.buscarPorId(idProducto);
            adminProducto.eliminar(producto);
            return "Producto eliminado correctamente";
        } catch (Exception e) {
            return "Error al eliminar:" + e.getMessage();
        }
    }

}
