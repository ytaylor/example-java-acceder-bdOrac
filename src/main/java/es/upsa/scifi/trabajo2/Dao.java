/*
  Interfaz para definir los metodos del DAO
 */
package es.upsa.scifi.trabajo2;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author samantha
 */
public interface Dao extends AutoCloseable{
    
    /*consultarProductos: esta funcionalidad permitirá obtener los datos de todos los productos
registrados en la base de datos*/
    public List<Producto> consultarProductos() throws SQLException; 
    
    /*consultarProducto: esta funcionalidad permitirá obtener el producto identificado por su
referencia.*/
    public Producto consultarProducto(String referencia) throws SQLException, ProductoNoEncontradoException; 
    
    /*consultarProductosPorPrecio: esta funcionalidad permitirá obtener un lista de productos cuyo
PVP sea menor o igual a un determinado valor que se pasará como parámetro a la funcionalidad.*/
    public List<Producto> consultarProductosPorPrecio(double pvp) throws SQLException; 
    
    /*consultarProductosPorStock: a través de esta funcionalidad se obtendrá una lista conteniendo
los productos cuyo stock esté comprendido entre un limiteInferior y un limiteSuperior que serán
pasados como parámetros a la funcionalidad.*/
    public List<Producto> consultarProductosPorStock(int limiteInferior, int limiteSuperior) throws SQLException; 
    
    /*insertarProducto: esta funcionalidad permitirá incluir en la tabla un nuevo producto. Será
necesario pasar como parámetro los datos del producto. */
    public Producto insertarProducto(String referencia, String nombre, int stock, double PVP) throws SQLException, NombreRepetidoException, ProductoRepetidoException; 
   
    /*actualizarProducto: a través de esta funcionalidad se permitirá modificar todos los datos de un
producto excepto su referencia*/
    public Producto actualizarProducto(String referencia, String nombre, int stock, double PVP) throws SQLException, ProductoNoEncontradoException, NombreRepetidoException; 
    
    /*eliminarProducto: esta funcionalidad permitirá eliminar un producto en concreto identificado por
    su referencia*/
    public boolean eliminarProducto(String referencia) throws SQLException, ProductoNoEncontradoException; 
}
