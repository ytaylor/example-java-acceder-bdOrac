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
public class NombreRepetidoException extends Exception {
   public NombreRepetidoException() {
    }

    public NombreRepetidoException(String message) {
        super(message);
    }

    public NombreRepetidoException(String message, Throwable cause) {
        super(message, cause);
    }

    public NombreRepetidoException(Throwable cause) {
        super(cause);
    }

    public NombreRepetidoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }  
}
