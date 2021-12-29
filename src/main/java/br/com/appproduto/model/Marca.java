package br.com.appproduto.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "marca")
public class Marca implements Serializable {

	
	private static final long serialVersionUID = 3116985689524922258L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	 @NotBlank(message = "Campo obrigatório")
   	@Size(min = 3, max = 60, message 
   = "O campo nome só pode ter entre 3 e 60 caracteres")
   @Column(unique = true)
	private String nome;
	
	
	public Marca() {
		
	}
	public Marca(Long id, String nome) {
		this.id = id;
		this.nome = nome.toUpperCase();
	}
	
	
	
	public Marca(Long id) {
		this.id = id;
		
	}
	@Override
	public String toString() {
		return "MarcaModel [id=" + id + ", nome=" + nome + "]";
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
	@Override
	public int hashCode() {
		return Objects.hash(id, nome);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Marca other = (Marca) obj;
		return Objects.equals(id, other.id) && Objects.equals(nome, other.nome);
	}
	
	
	
	
	
	
	
	

}
