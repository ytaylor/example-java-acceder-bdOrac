/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.upsa.scifi.trabajo2;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import oracle.jdbc.OracleDriver;

/**
 * @author samantha
 */
public class OracleDao implements Dao{
    
    private Connection connection;
    public OracleDao(String url, String username, String password) throws SQLException 
    {
        Driver driver = new OracleDriver();
        DriverManager.registerDriver( driver );      
        
        this.connection = DriverManager.getConnection(url, username, password);
    }

    @Override
    public void close() throws Exception 
    {
        if ( this.connection != null )
        {
            this.connection.close();
            this.connection = null;            
        }
    }

    @Override
    public List<Producto> consultarProductos() throws SQLException {
    
        final String SQL_SELECT = "SELECT A.REFERENCIA, A.NOMBRE, A.STOCK, A.PVP " + 
                                  "FROM PRODUCTOS A" ;
        List<Producto> productos = new ArrayList<>();
        try ( Statement statement = connection.createStatement(); 
              ResultSet resultSet = statement.executeQuery(SQL_SELECT);){
            if ( resultSet.next() == true  )
            {
                do
                {
                    Producto producto = new Producto();
                    producto.setReferencia(resultSet.getString(1) );
                    producto.setNombre( resultSet.getString(2) );
                    producto.setStock(resultSet.getInt(3) );
                    producto.setPvp(resultSet.getDouble(4) );
                    productos.add(producto);
                } while ( resultSet.next() == true );                
            }
        }catch (SQLException sqlException) {
            throw sqlException;
        }
        return productos;    
    }

    @Override
    public Producto consultarProducto(String referencia) throws SQLException, ProductoNoEncontradoException {
        String sql = "SELECT A.REFERENCIA, A.NOMBRE, A.STOCK, A.PVP "
                + " FROM FROM PRODUCTOS A "
                + " WHERE A.REFERENCIA = ? ";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, referencia);
            try (ResultSet resultSet = ps.executeQuery()) {
                if (resultSet.next()) {
                    Producto producto = new Producto();
                    producto.setReferencia(resultSet.getString(1));
                    producto.setNombre(resultSet.getString(2));
                    producto.setStock(resultSet.getInt(3));
                    producto.setPvp(resultSet.getDouble(4));
                    return producto;
                }
            }
        } catch (SQLException sqlException) {
            String message = sqlException.getMessage();
            if ( message.contains("PK_REFERENCIA") )       
                throw new ProductoNoEncontradoException("No se encuentra el producto con la referencia " + referencia);
            throw sqlException;
        }
        return null;
    }
        

    @Override
    public List<Producto> consultarProductosPorPrecio(double pvp) throws SQLException {
        final String SQL_SELECT = "SELECT A.REFERENCIA, A.NOMBRE, A.STOCK, A.PVP "
                + "FROM PRODUCTOS A "
                 + "WHERE A.PVP >= ? ";
        List<Producto> productos = new ArrayList<>();
         try ( PreparedStatement ps = connection.prepareStatement(SQL_SELECT) )
        {
             ps.setDouble(1, pvp);
             try (ResultSet resultSet = ps.executeQuery()){
            if (resultSet.next() == true) {
                do {
                    Producto producto = new Producto();
                    producto.setReferencia(resultSet.getString(1));
                    producto.setNombre(resultSet.getString(2));
                    producto.setStock(resultSet.getInt(3));
                    producto.setPvp(resultSet.getDouble(4));
                    productos.add(producto);
                } while (resultSet.next() == true);
            }
           }
        } catch (SQLException sqlException) {
            throw sqlException;
        }
        return productos;

    }

    @Override
    public List<Producto> consultarProductosPorStock(int limiteInferior, int limiteSuperior) throws SQLException {
        final String SQL_SELECT = "SELECT A.REFERENCIA, A.NOMBRE, A.STOCK, A.PVP "
                + "FROM PRODUCTOS A "
                + "WHERE A.STOCK BETWEEN ? AND ? ";
        List<Producto> productos = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(SQL_SELECT)) {
            ps.setInt(1, limiteInferior);
            ps.setInt(2, limiteSuperior);
            try (ResultSet resultSet = ps.executeQuery()) {
                if (resultSet.next() == true) {
                    do {
                        Producto producto = new Producto();
                        producto.setReferencia(resultSet.getString(1));
                        producto.setNombre(resultSet.getString(2));
                        producto.setStock(resultSet.getInt(3));
                        producto.setPvp(resultSet.getDouble(4));
                        productos.add(producto);
                    } while (resultSet.next() == true);
                }
            }
        } catch (SQLException sqlException) {
            throw sqlException;
        }
        return productos;  
    }

    @Override
    public Producto insertarProducto(String referencia, String nombre, int stock, double PVP) throws SQLException, NombreRepetidoException, ProductoRepetidoException {
        final String SQL_INSERT =   "INSERT INTO PRODUCTOS(REFERENCIA, NOMBRE, STOCK, PVP) " 
                                  + "VALUES(?,?,?,?) ";
        try ( PreparedStatement ps = connection.prepareStatement(SQL_INSERT) )
        {
            ps.setString(1, referencia);
            ps.setString(2, nombre);
            ps.setInt(3, stock);
            ps.setDouble(4, PVP);
            ps.executeUpdate();            
            return new Producto(referencia, nombre, stock, PVP);
        } catch (SQLException sqlException)
          {
            String message = sqlException.getMessage();
            if ( message.contains("PK_REFERENCIA") )       throw new ProductoRepetidoException("Ya existe un producto con la referencia " + referencia);
            if ( message.contains("UK_PRODUCTOS.NOMBRE") )   throw new NombreRepetidoException("Ya existe otro producto con el nombre " + nombre);
            
            throw sqlException;            
          }
    }

    @Override
    public Producto actualizarProducto(String referencia, String nombre, int stock, double PVP) throws SQLException, ProductoNoEncontradoException, NombreRepetidoException {
        final String SQL_UPDATE = "UPDATE PRODUCTOS( NOMBRE, STOCK, PVP) "
                + "VALUES(?,?,?) "
                +"WHERE A.REFERENCIA = ?";
        try (PreparedStatement ps = connection.prepareStatement(SQL_UPDATE)) {
            ps.setString(1, nombre);
            ps.setInt(2, stock);
            ps.setDouble(3, PVP);
            ps.setString(4, referencia);
            ps.executeUpdate();
            return new Producto(referencia, nombre, stock, PVP);
        } catch (SQLException sqlException) {
            String message = sqlException.getMessage();

            if ( message.contains("PK_REFERENCIA") )       
                throw new ProductoNoEncontradoException("No se encuentra el producto con la referencia " + referencia);
            if (message.contains("UK_PRODUCTOS.NOMBRE")) {
                throw new NombreRepetidoException("Ya existe otro producto con el nombre " + nombre);
            }

            throw sqlException;
        }
    }

    @Override
    public boolean eliminarProducto(String referencia) throws SQLException, ProductoNoEncontradoException {
        String sql = "DELETE PRODUCTOS A "
                + " WHERE A.REFERENCIA = ? ";

        try ( PreparedStatement ps = connection.prepareStatement(sql) )
        {
            ps.setString(1, referencia);
            return (1 == ps.executeUpdate());
        }catch (SQLException sqlException) {
            String message = sqlException.getMessage();
            if ( message.contains("PK_REFERENCIA") )       
                throw new ProductoNoEncontradoException("No se encuentra el producto con la referencia " + referencia);
            throw sqlException;
        }
        
        
    }

}
