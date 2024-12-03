package com.projeto_fuji.consultas.validation.constraints;

import com.projeto_fuji.consultas.validation.PacienteValidation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PacienteValidation.class)
@Target({ ElementType.FIELD, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Paciente {

    String message() default "Nome do paciente inválido";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
