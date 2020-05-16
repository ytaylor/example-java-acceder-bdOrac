/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.upsa.scifi.trabajo2;

/**
 *
 * @author becaeice
 */
public class ProductoRepetidoException extends Exception{
     public ProductoRepetidoException() {
    }

    public ProductoRepetidoException(String message) {
        super(message);
    }

    public ProductoRepetidoException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProductoRepetidoException(Throwable cause) {
        super(cause);
    }

    public ProductoRepetidoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }  
    
}
