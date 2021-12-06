package br.com.appproduto.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Entity
@Table(name = "produto")
public class Produto implements Serializable {

	
	private static final long serialVersionUID = -3770333516648122412L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

   @NotBlank(message = "Campo obrigatório")
   	@Size(min = 3, max = 60, message 
   = "O campo nome só pode ter entre 3 e 60 caracteres")
   @Column(unique = true)
	private String nome;
	
	@NotBlank(message = "Campo obrigatório")
	@Size( min=3, max = 60, message 
	   = "O campo descrição tem no minimo 3 e no maximo 60 caracteres")
	private String descricao;
	
	@NotBlank(message = "Campo obrigatório")
	@DecimalMin(value = "0.99", inclusive = false)
	@Column( precision = 8, scale = 2)
	private BigDecimal valor;
	
	@NotBlank(message = "Campo obrigatório")
    @Min(value = 1, message = "É necessario pelo menos 1 produto para cadastro")
	private Long quantidade;
	
	@NotBlank(message = "Campo obrigatório")
	private String url_imagem;
	
	@ManyToOne 
	@JoinColumn(name="idCategoria")
	@NotBlank(message = "Campo obrigatório")
	private Categoria idCategoria;
	
	@ManyToOne
	@JoinColumn(name="idMarca")
	@NotBlank(message = "Campo obrigatório")
	private Marca idMarca;
	
	public Produto() {
		
	}

	public Produto(Long id, String nome, String descricao, BigDecimal valor, Long quantidade, String url_imagem,
			Categoria idCategoria, Marca idMarca) {
		
		this.id = id;
		this.nome = nome.toUpperCase();
		this.descricao = descricao;
		this.valor = valor;
		this.quantidade = quantidade;
		this.url_imagem = url_imagem;
		this.idCategoria = idCategoria;
		this.idMarca = idMarca;
	}

	@Override
	public String toString() {
		return "ProdutoModel [id=" + id + ", nome=" + nome + ", descricao=" + descricao + ", valor=" + valor
				+ ", quantidade=" + quantidade + ", url_imagem=" + url_imagem + ", idCategoria=" + idCategoria
				+ ", idMarca=" + idMarca + "]";
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Long getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Long quantidade) {
		this.quantidade = quantidade;
	}

	public String getUrl_imagem() {
		return url_imagem;
	}

	public void setUrl_imagem(String url_imagem) {
		this.url_imagem = url_imagem;
	}

	public Categoria getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Categoria idCategoria) {
		this.idCategoria = idCategoria;
	}

	public Marca getIdMarca() {
		return idMarca;
	}

	public void setIdMarca(Marca idMarca) {
		this.idMarca = idMarca;
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
		Produto other = (Produto) obj;
		return Objects.equals(id, other.id);
	}
	
	
	
	
	
	
	
	

}
