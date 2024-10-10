package com.skills.neki.dto;

import com.skills.neki.model.Skills;

public class SkillsDTO {

    private Long id;  // Adiciona o ID aqui
    private byte[] photo;
    private String nome;
    private String descricao;
    private String tecnologia;
    private String nivel; 

    public SkillsDTO() {}

    public SkillsDTO(String nome, String descricao, String tecnologia, String nivel, byte[] photo) {
        this.nome = nome;
        this.descricao = descricao;
        this.tecnologia = tecnologia;
        this.nivel = nivel; 
        this.photo = photo;
    }

    public SkillsDTO(Skills skill) {
        this.id = skill.getId();  // Captura o ID da entidade Skills
        this.nome = skill.getNome();
        this.descricao = skill.getDescricao();
        this.tecnologia = skill.getTecnologia();
        this.nivel = skill.getNivel(); 
        this.photo = skill.getPhoto(); 
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTecnologia() {
        return tecnologia;
    }

    public void setTecnologia(String tecnologia) {
        this.tecnologia = tecnologia;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }
}
