package com.projeto_fuji.consultas.exception;

public class PacientNotFoundException extends RuntimeException{
    public PacientNotFoundException(){
        super("Paciente não encontrado!");
    }
}
