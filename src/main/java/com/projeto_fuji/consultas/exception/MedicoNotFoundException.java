package com.projeto_fuji.consultas.exception;

public class MedicoNotFoundException extends RuntimeException {
    public MedicoNotFoundException(){super("Medico não encontrado!");}
}
