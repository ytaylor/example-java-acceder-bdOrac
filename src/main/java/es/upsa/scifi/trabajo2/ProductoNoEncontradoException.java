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
public class ProductoNoEncontradoException extends Exception{
     public ProductoNoEncontradoException(){}
     public ProductoNoEncontradoException(String message){
     super(message);}
     public ProductoNoEncontradoException(String message, Throwable cause) {
        super(message, cause);
    }
      public ProductoNoEncontradoException(Throwable cause) {
        super(cause);
    }
       public ProductoNoEncontradoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
     
}
