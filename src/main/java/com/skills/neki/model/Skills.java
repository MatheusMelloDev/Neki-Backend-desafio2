package com.skills.neki.model;

import com.skills.neki.dto.SkillsDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "skills")
public class Skills {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_skill")
    private Long idSkill;

    @Column(name = "nome", unique = true, nullable = false)
    private String nome;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "imagem_url")
    private String imagemUrl;

    @Column(name = "tecnologia", nullable = false)
    private String tecnologia;

    public Skills() {
    }

   

 
    public Skills(Long idSkill, String nome, String descricao, String imagemUrl, String tecnologia) {
		super();
		this.idSkill = idSkill;
		this.nome = nome;
		this.descricao = descricao;
		this.imagemUrl = imagemUrl;
		this.tecnologia = tecnologia;
	}




	public Skills(SkillsDTO dto) {
		this.idSkill = dto.getIdSkill();
        this.nome = dto.getNome();
        this.descricao = dto.getDescricao();
        this.imagemUrl = dto.getImagemUrl();
        this.tecnologia = dto.getTecnologia();
    }




	public Long getIdSkill() {
		return idSkill;
	}




	public void setIdSkill(Long idSkill) {
		this.idSkill = idSkill;
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




	public String getImagemUrl() {
		return imagemUrl;
	}




	public void setImagemUrl(String imagemUrl) {
		this.imagemUrl = imagemUrl;
	}




	public String getTecnologia() {
		return tecnologia;
	}




	public void setTecnologia(String tecnologia) {
		this.tecnologia = tecnologia;
	}

}

