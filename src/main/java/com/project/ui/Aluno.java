package com.project.ui;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.util.Calendar;
import java.text.DateFormatSymbols;
import java.util.Date;

/**
 *
 * @author oluis
 */
public class Aluno {
    private String nome;
    private String cpf;
    private String nascimento;
    private String email;
    private String telefone;
    private String plano;
    private String datainicio;
    private String datafim;
    private String status;

    public Aluno(String nome, String cpf, String nascimento, String email, String telefone, String plano){
        this.nome = nomeFormatter(nome);
        this.cpf = cpfFormatter(cpf);
        this.nascimento = nascimentoFormatter(nascimento);
        this.email = emailFormatter(email);
        this.telefone = telefoneFormatter(telefone);
        this.plano = plano;
        verificarEAtualizarStatus();
    }

    public Aluno(String nome, String cpf, String nascimento, String email, String telefone, String plano, String status, String dataInicial, String dataFinal) {
        this.nome = nomeFormatter(nome);
        this.cpf = cpfFormatter(cpf);
        this.nascimento = nascimentoFormatter(nascimento);
        this.email = emailFormatter(email);
        this.telefone = telefoneFormatter(telefone);
        this.plano = plano;
        this.status = status;
        this.datainicio = dataInicial;
        this.datafim = getData_fim();
        verificarEAtualizarStatus();
    }


    // Setters
    public void setNome(String nome) {
        this.nome = nomeFormatter(nome);;
    }

    public void setCpf(String cpf) {
        this.cpf = cpfFormatter(cpf);
    }

    public void setNascimento(String nascimento) {
        this.nascimento = nascimentoFormatter(nascimento);
    }

    public void setEmail(String email) {
        this.email = emailFormatter(email);
    }

    public void setTelefone(String telefone) {
        this.telefone = telefoneFormatter(telefone);
    }

    public void setPlano(String plano) {
        this.plano = plano;
    }

    public void setDatainicio(String datainicio) {
        this.datainicio = getData_inicio();
    }

    // Sistema de Data
    private Calendar calendar = Calendar.getInstance();
    private String data_inicio = getDiaDoMes() + "/" + getMes() + "/" + getAno();
    private String data_fim = setDataFim();
    private DateFormatSymbols df = new DateFormatSymbols();
    private Date dt = new Date();

    private String getDiaDoMes() {
        int dia = this.calendar.get(Calendar.DAY_OF_MONTH);
        return String.format("%02d", dia); // Formata para dois dígitos
    }

    private String getMes() {
        int mes = this.calendar.get(Calendar.MONTH) + 1; // Calendar.MONTH é baseado em 0
        return String.format("%02d", mes); // Formata para dois dígitos
    }

    private String getAno() {
        int ano = this.calendar.get(Calendar.YEAR);
        return String.valueOf(ano);
    }

    public String setDataFim() {
        try {
            if (data_inicio == null || data_inicio.length() != 10) {
                throw new IllegalArgumentException("Data de início inválida");
            }

            int dia = Integer.parseInt(data_inicio.substring(0, 2));
            int month = Integer.parseInt(data_inicio.substring(3, 5));
            int year = Integer.parseInt(data_inicio.substring(6, 10));

            // Ajusta o calendário para a data de início
            calendar.set(year, month - 1, dia);

            // Adiciona um mês
            calendar.add(Calendar.MONTH, 1);

            // Verifica se o novo dia ainda é válido no próximo mês
            dia = calendar.get(Calendar.DAY_OF_MONTH);
            month = calendar.get(Calendar.MONTH) + 1; // Calendar.MONTH é baseado em 0
            year = calendar.get(Calendar.YEAR);

            data_fim = String.format("%02d/%02d/%d", dia, month, year);

            return data_fim;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Formato de data inválido: " + data_inicio);
        }
    }


    private void verificarEAtualizarStatus() {
        Calendar calInicio = Calendar.getInstance();
        calInicio.set(Integer.parseInt(getAno()), Integer.parseInt(getMes()) - 1, Integer.parseInt(getMes()));

        Calendar calFim = Calendar.getInstance();
        calFim.set(Integer.parseInt(data_fim.substring(6, 10)), Integer.parseInt(data_fim.substring(3, 5)) - 1, Integer.parseInt(data_fim.substring(0, 2)));
        calFim.add(Calendar.DAY_OF_MONTH, 1);

        if (calInicio.equals(calFim)) {
            this.status = "Inativo";
        } else {
            this.status = "Ativo";
        }
    }

    public void setStatus(String status) {
        verificarEAtualizarStatus();
    }

    // Formatadores das Entradas do Construtor
    private String nomeFormatter(String nome){
        if (!nome.isEmpty()) {
            for (int i = 0; i < nome.length(); i++) {
                if (i == 0 || nome.charAt(i - 1) == ' ') {
                    nome = nome.substring(0, i) + nome.substring(i, i + 1).toUpperCase() + nome.substring(i + 1);
                }
            }
            return nome;
        } else {
            throw new IllegalArgumentException("Nome inválido. Deve conter ao menos 1 caractere.");
        }
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

    private String nascimentoFormatter(String datanascimento){
        if (datanascimento.length() == 10) {
            datanascimento = datanascimento.replaceAll("\\D", "");
            datanascimento = datanascimento.substring(0, 2) + "/" + datanascimento.substring(2, 4) + "/" + datanascimento.substring(4, 8);
            return datanascimento;
        } else {
            throw new IllegalArgumentException("Data de nascimento inválida. Deve conter 10 dígitos.");
        }
    }

    private String emailFormatter(String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email inválido. Deve conter ao menos 1 caractere.");
        }

        email = email.trim(); // Remove espaços em branco no início e no final

        System.out.println("Email a ser validado: " + email); // Adiciona uma mensagem de depuração

        // Verificação aprimorada para um endereço de e-mail básico
        if (email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            return email;
        } else {
            throw new IllegalArgumentException("Email inválido. Deve conter '@' e '.'.");
        }
    }

    private String telefoneFormatter(String telefone){
        telefone = telefone.replaceAll("\\D", "");
        if (telefone.length() == 11) {
            telefone = "(" + telefone.substring(0, 2) + ") " + telefone.substring(2, 7) + "-" + telefone.substring(7, 11);
            return telefone;
        } else {
            throw new IllegalArgumentException("Telefone inválido. Deve conter 11 dígitos.");
        }
    }

    // Getters
    public String getStatus() {
        return status;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEmail() {
        return email;
    }

    public String getData_fim() {
        return data_fim;
    }

    public String getNascimento() {
        return nascimento;
    }

    public String getPlano() {
        return plano;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getData_inicio() {
        return data_inicio;
    }

    public String getDatafim() {
        return datafim;
    }

}

