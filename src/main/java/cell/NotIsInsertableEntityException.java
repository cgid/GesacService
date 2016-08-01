/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cell;

import entity.*;

/**
 *
 * @author murilo
 */
public class NotIsInsertableEntityException extends RuntimeException{
    public NotIsInsertableEntityException() {
        super("Nao e possivel deletetar esta entidade. Por favor, verifique seus valores.");
    }
}
