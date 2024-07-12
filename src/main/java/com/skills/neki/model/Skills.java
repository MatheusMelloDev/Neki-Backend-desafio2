package com.skills.neki.model;






import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "skills")
public class Skills {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_skill")
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "descricao")
    private String descricao;

    
    @Column(name = "tecnologia")
    private String tecnologia;
    

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;
    
/*estou comentando pq acho que não vou precisar dessa associação
    @ManyToMany(mappedBy = "skills")
    List<User> users;
    */
    
    public Skills() {
    }

public Skills(Long id, String nome, String descricao,  String tecnologia, User user) {
	super();
	this.id = id;
	this.nome = nome;
	this.descricao = descricao;
	
	this.tecnologia = tecnologia;
	this.user = user;
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

public User getUser() {
	return user;
}

public void setUser(User user) {
	this.user = user;
}

public void setImagemUrl(Object imagemUrl) {
	// TODO Auto-generated method stub
	
}



 
}