package com.meeting_hub.Clientes;

import com.meeting_hub.Cliente;

public class Corporativo extends Cliente {

    private String cnpj;

    public Corporativo(String nome, String telefone, String cnpj) {
        this.nome = nome;
        this.telefone = telefone;
        this.cnpj = cnpj;
    }

}