/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.minicom.scr.entity.exceptions;

/**
 *
 * @author murilo
 */
public class InvalidDateFormat extends RuntimeException{
    public InvalidDateFormat() {
        super("A data inserida nao esta no formato adequado.\n-> (yyyy-dd-MM HH:mm:ss)");
    }
}
