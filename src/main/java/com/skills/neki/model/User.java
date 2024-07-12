package com.skills.neki.model;

import java.util.List;
import jakarta.persistence.*;

@Entity
@Table(name = "usuario")
public class User  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "email")
    private String email;

    @Column(name = "senha")
    private String senha;

    @Column(name = "cargo")
    private String cargo;

    @OneToMany(mappedBy = "user")
    private List<Skills> skillsList;

    // Construtor padrão
    public User() {
    }

    
	public User(Long id, String nome, String email, String senha, String cargo, List<Skills> skillsList) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.cargo = cargo;
		this.skillsList = skillsList;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getSenha() {
		return senha;
	}


	public void setSenha(String senha) {
		this.senha = senha;
	}


	public String getCargo() {
		return cargo;
	}


	public void setCargo(String cargo) {
		this.cargo = cargo;
	}


	public List<Skills> getSkillsList() {
		return skillsList;
	}


	public void setSkillsList(List<Skills> skillsList) {
		this.skillsList = skillsList;
	}



	
  
}
