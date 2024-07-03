/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.project.ui;

public class Admin {
    private String cpf;
    private String senha;

    // Construtor
    public Admin(String cpf, String senha) {
        this.cpf = cpfFormatter(cpf);
        this.senha = senha;
    }

    private String cpfFormatter(String cpf){
        cpf = cpf.replaceAll("\\D", "");

        if (cpf.length() == 11) {
            cpf = cpf.substring(0, 3) + "." + cpf.substring(3, 6) + "." + cpf.substring(6, 9) + "-" + cpf.substring(9, 11);
        } else {
            throw new IllegalArgumentException("CPF inválido. Deve conter 11 dígitos.");
        }
        return cpf;
    }

    // Getters e Setters
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "cpf='" + cpf + '\'' +
                ", senha='" + senha + '\'' +
                '}';
    }
}