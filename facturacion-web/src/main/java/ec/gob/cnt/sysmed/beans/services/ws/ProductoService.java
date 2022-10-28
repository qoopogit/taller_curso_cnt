/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package ec.gob.cnt.sysmed.beans.services.ws;

import ec.gob.cnt.sysmed.controllers.ProductoFacade;
import ec.gob.cnt.sysmed.entities.Producto;
import java.util.List;
import javax.inject.Inject;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author Alberto
 */
@WebService(serviceName = "ProductoService")
public class ProductoService {

    @Inject
    private ProductoFacade adminProducto;

    /**
     * This is a sample web service operation
     *
     * @param id ID del paciente
     * @return Paciente
     */
    @WebMethod(operationName = "consultarProductoPorId")
    public Producto consultarPorId(@WebParam(name = "id") Integer id) {
        return adminProducto.buscarPorId(id);
    }

    @WebMethod(operationName = "consultarTodos")
    public List<Producto> consultarTodos() {
        return adminProducto.buscarTodos();
    }

    /**
     * Método para guardar un tipo de tipoPaciente
     *
     * @param tipoPaciente
     * @return
     */
    public String guardarProducto(Producto tipoPaciente) {
        try {
            adminProducto.guardar(tipoPaciente);
            return "Producto registrado correctamente";
        } catch (Exception e) {
            return "Error al Guardar:" + e.getMessage();
        }
    }

    /**
     * Método para actualizar el tipo de tipoPaciente
     *
     * @param producto tipo de paciente que existe en la bdd
     * @return mensaje de confirmación
     */
    @WebMethod(operationName = "actualizarProducto")
    public String actualizarProducto(Producto producto) {
        try {
            if (adminProducto.buscarPorId(producto.getId()) != null) {
                adminProducto.actualizar(producto);
                return "Producto actualizado correctamente";
            } else {
                return "Producto no existe";
            }

        } catch (Exception e) {
            return "Error al Actualizar:" + e.getMessage();
        }
    }

    /**
     * Método para eliminar el tipo de paciente
     *
     * @param idProducto identificador de tipo de paciente
     * @return mensaje de confirmación
     */
    public String eliminarProducto(@WebParam(name = "identificadorProducto") Long idProducto) {
        try {
            Producto tipoPaciente = adminProducto.buscarPorId(idProducto);
            if (tipoPaciente != null) {
                adminProducto.eliminar(tipoPaciente);
                return "Producto eliminado correctamente";
            } else {
                return "Producto no existe";
            }

        } catch (Exception e) {
            return "Error al Eliminar:" + e.getMessage();
        }
    }
}
