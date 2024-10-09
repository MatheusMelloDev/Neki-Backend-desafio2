package com.skills.neki.dto;

import com.skills.neki.model.Skills;

public class SkillsDTO {

	
	
	 private byte[] photo;
    private String nome;
    private String descricao;
    private String tecnologia;

    public SkillsDTO() {}

    
    public SkillsDTO(String nome, String descricao, String tecnologia, byte[] photo) {
        this.nome = nome;
        this.descricao = descricao;
        this.tecnologia = tecnologia;
        this.photo = photo;
    }

    
    public SkillsDTO(Skills skill) {
        this.nome = skill.getNome();
        this.descricao = skill.getDescricao();
        this.tecnologia = skill.getTecnologia();
        this.photo = skill.getPhoto(); 
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

 
}
