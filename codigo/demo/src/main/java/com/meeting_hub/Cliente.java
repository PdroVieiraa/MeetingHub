package com.meeting_hub;

public class Cliente {
    protected Long id;
    protected String nome; // Alterado de private para protected
    protected String telefone;

    public String getNome() {
        return nome;
    }

    public Long getId(){
        return id;
    }

    // Construtor, getters e setters, se necessário
}
