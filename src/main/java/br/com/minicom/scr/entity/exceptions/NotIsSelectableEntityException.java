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
public class NotIsSelectableEntityException extends RuntimeException{
    public NotIsSelectableEntityException() {
        super("Nao e possivel selecionar esta entidade. Por favor, verifique seus valores.");
    } 
}
