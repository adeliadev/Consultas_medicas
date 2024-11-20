package com.projeto_fuji.consultas.exception;

public class EmailAlreadyBeingUsedException extends RuntimeException{
    public EmailAlreadyBeingUsedException() {
        super("O email já está sendo usado.");
    }
}
