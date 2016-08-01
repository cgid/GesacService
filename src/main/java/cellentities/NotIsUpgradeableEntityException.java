/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cellentities;

/**
 *
 * @author murilo
 */
public class NotIsUpgradeableEntityException extends RuntimeException{
    public NotIsUpgradeableEntityException() {
        super("Nao e possivel atualizar esta entidade. Por favor, verifique seus valores.");
    }
}
