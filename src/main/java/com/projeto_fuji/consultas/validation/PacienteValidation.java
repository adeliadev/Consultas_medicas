package com.projeto_fuji.consultas.validation;

import com.projeto_fuji.consultas.validation.constraints.Paciente;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PacienteValidation implements ConstraintValidator<Paciente, String> {

    @Override
    public boolean isValid(String nomePaciente, ConstraintValidatorContext context) {
        if (nomePaciente == null || nomePaciente.isEmpty()) {
            return true;
        }
        boolean isValid = PacienteBase.isDiretorValido(nomePaciente);
        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                    String.format("O nome do diretor '%s' informado não é válido.", nomePaciente)
            ).addConstraintViolation();
        }
        return isValid;
    }
}