package com.projeto_fuji.consultas.adapter;

import com.projeto_fuji.consultas.repository.ValidadorEmailProtocolo;
import org.apache.commons.validator.routines.EmailValidator;

public class AdapterValidadorEmail implements ValidadorEmailProtocolo {

    @Override
    public boolean isEmail(String valor) {
        if (valor == null || valor.isEmpty()) {
            return false;
        }
        return EmailValidator.getInstance().isValid(valor);
    }
}
