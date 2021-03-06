package br.com.appproduto.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;             
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import br.com.appproduto.model.Categoria;
import br.com.appproduto.model.Marca;



public class ProdutoDto implements Serializable {

	
	
	
	
	private static final long serialVersionUID = 1L;

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
	
	private byte[] img;
	
	public ProdutoDto() {
		
	}

	public ProdutoDto(Long id, String nome, String descricao, BigDecimal valor, Long quantidade, String url_imagem,
			Categoria idCategoria, Marca idMarca,byte[] img) {
		
		this.id = id;
		this.nome = nome.toUpperCase();
		this.descricao = descricao;
		this.valor = valor;
		this.quantidade = quantidade;
		this.url_imagem = url_imagem;
		this.idCategoria = idCategoria;
		this.idMarca = idMarca;
		this.img=img;
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

	
	
	public byte[] getImg() {
		return img;
	}

	public void setImg(byte[] img) {
		this.img = img;
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
		ProdutoDto other = (ProdutoDto) obj;
		return Objects.equals(id, other.id);
	}
	
	
	
	
	
	
	
	

}

