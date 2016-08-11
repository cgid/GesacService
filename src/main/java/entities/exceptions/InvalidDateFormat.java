/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.exceptions;

/**
 *
 * @author murilo
 */
public class InvalidDateFormat extends RuntimeException{
    public InvalidDateFormat() {
        super("A data inserida nao esta no formato adequado.\n-> (dd-MM-aaaa HH:mm:ss)");
    }
}
