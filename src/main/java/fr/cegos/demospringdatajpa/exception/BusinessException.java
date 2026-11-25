package fr.cegos.demospringdatajpa.exception;

/**
 * classe d'Exception personalisée lancée lorsqu'on a une validation Métier qui échoue
 */
public class BusinessException extends RuntimeException {
    public BusinessException(String message) {
        super(message);
    }
}