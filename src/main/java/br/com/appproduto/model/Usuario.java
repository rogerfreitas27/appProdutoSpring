package br.com.appproduto.model;

import java.io.Serializable;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;//esse import é pra não exibir a senha



@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {

	
	private static final long serialVersionUID = 2422373020592189539L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Campo obrigatório")
	@Size(min = 3, max = 60, message 
    = "O campo nome só pode ter entre 3 e 60 caracteres")
	private String nome;
	
	@Email(message = "E-mail inválido")
    @NotBlank(message = "E-mail não informado")
	@Column(unique = true)
	@Size(min = 5, max = 60, message 
    = "O campo senha tem entre 5 e 60 caracteres")
	private String email;
	
	
	@Size(min = 6, max = 255, message 
		    = "O campo senha tem entre 6 e 10 caracteres")
	 @NotBlank(message = "Campo obrigatorio")
	 @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	 private String senha;   
   
	 
	
	public Usuario() {
		
	}
//.toUpperCase()
	public Usuario(Long id, String nome, String email, String senha) {
	
		this.id = id;
		this.nome = nome.toUpperCase();
		this.email = email;
		this.senha = senha;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nome=" + nome + ", email=" + email + ", senha=" + senha + "]";
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
	
	
	

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(id, other.id);
	}
	
	
	
	

}
