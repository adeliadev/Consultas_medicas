package com.projeto_fuji.consultas.validation;

import com.projeto_fuji.consultas.adapter.AdapterValidadorEmail;
import com.projeto_fuji.consultas.repository.ValidadorEmailProtocolo;

public class EmailValidation {
    public static void validarEmailClasse(ValidadorEmailProtocolo validadorEmail, String email) {
        if (validadorEmail.isEmail(email)) {
            System.out.println("Email do paciente é válido");
        } else {
            System.out.println("Email do paciente é inválido");
        }
    }

    public static void main(String[] args) {
        String emailValido = "turma@gmail.com";
        String emailInvalido = "turma@";

        try {
            // Testa um email válido
            System.out.println("Testando email válido:");
            validarEmailClasse(new AdapterValidadorEmail(), emailValido);

            System.out.println();

            // Testa email inválido
            System.out.println("Testando email inválido:");
            validarEmailClasse(new AdapterValidadorEmail(), emailInvalido);
        } catch (Exception e) {
            System.err.println("Ocorreu um erro ao validar o email: "+ e.getMessage());
            e.printStackTrace();  // Exibe a pilha de erros para facilitar a depuração
        }
    }
}
