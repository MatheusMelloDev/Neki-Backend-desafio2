package com.skills.neki.dto;

import com.skills.neki.model.Skills;

public class SkillsDTO {

    private String nome;
    private String descricao;

    private String tecnologia;

 

    public SkillsDTO() {}



	public SkillsDTO(String nome, String descricao,String tecnologia) {
		super();
		this.nome = nome;
		this.descricao = descricao;
	
		this.tecnologia = tecnologia;
	}



	public SkillsDTO(Skills skill) {
		// TODO Auto-generated constructor stub
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



	public Object getImagemUrl() {
		// TODO Auto-generated method stub
		return null;
	}



}


