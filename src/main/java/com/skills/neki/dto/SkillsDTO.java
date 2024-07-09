package com.skills.neki.dto;

public class SkillsDTO {

    private Long idSkill;
    private String nome;
    private String descricao;
    private String imagemUrl;
    private String tecnologia;

    public SkillsDTO() {
    }

    public SkillsDTO(Long idSkill, String nome, String descricao, String imagemUrl, String tecnologia) {
        this.idSkill = idSkill;
        this.nome = nome;
        this.descricao = descricao;
        this.imagemUrl = imagemUrl;
        this.tecnologia = tecnologia;
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
