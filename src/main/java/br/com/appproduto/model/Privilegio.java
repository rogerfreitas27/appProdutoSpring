package br.com.appproduto.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class Privilegio implements Serializable {

	
	private static final long serialVersionUID = 1L;
	 @Id
     @GeneratedValue(strategy = GenerationType.AUTO)
     private Long id;     
	 
	 @NotBlank(message = "Campo obrigatório")
	   	@Size(min = 3, max = 60, message 
	   = "O campo nome só pode ter entre 3 e 60 caracteres")
	   @Column(unique = true)
     private String nome;
     
     @ManyToMany(mappedBy = "privilegios")   
     @JsonIgnore 
     private Collection<User> user = new ArrayList<>();
     
     @ManyToMany(mappedBy = "privilegios")
     @JsonIgnore 
     private Collection<Role> roles = new ArrayList<>();

	public Privilegio(Long id, String nome, Collection<User> user, Collection<Role> roles) {
		
		this.id = id;
		this.nome = nome.toUpperCase();
		this.user = user;
		this.roles = roles;
	}
	


	public Privilegio() {
	
	}

	@Override
	public String toString() {
		return "Privilegio [id=" + id + ", nome=" + nome + ", user=" + user + ", roles=" + roles + "]";
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
		this.nome = nome.toUpperCase();
	}

	public Collection<User> getUser() {
		return user;
	}

	public void setUser(Collection<User> user) {
		this.user = user;
	}

	public Collection<Role> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}
	
	
	
	
			
	
	
     
     
     
     

}
