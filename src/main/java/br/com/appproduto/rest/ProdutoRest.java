package br.com.appproduto.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import br.com.appproduto.model.Produto;
import br.com.appproduto.persistence.ProdutoPersistence;
import br.com.appproduto.servico.ProdutoService;
import br.com.appproduto.util.Serializar;


@RestController
@RequestMapping("/api/produto")
public class ProdutoRest {
	
	
	
	private final ProdutoPersistence pp;
	private final Serializar sr;
	private final ProdutoService ps;
	
	public ProdutoRest(ProdutoPersistence pp, Serializar sr,ProdutoService ps) {
		
		this.pp = pp;
		this.sr = sr;
		this.ps = ps;
	}
	
	
	
	@PostMapping
	@PreAuthorize("hasAuthority('PRODUTO_WRITE_PRIVILEGE')")
	public ResponseEntity<String> save(String produtoJson, MultipartFile foto ){
			
		try {		
			
		ps.save(produtoJson,  foto);
			
			return  ResponseEntity.status(HttpStatus.CREATED).body("Cadastro realizado com sucesso !");
		} catch (Exception e) {
			e.printStackTrace();
			return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Falha ao realizar cadastro" + e.getMessage());
		}
		
		
		
		
	}
	
	
	
	@GetMapping("/buscarPorId")
	@PreAuthorize("hasAuthority('PRODUTO_READ_PRIVILEGE')")
	public ResponseEntity<String> findById(@RequestParam(value="id") Long id){
		Produto p = new Produto();
		Optional<Produto> prod;
		try {
		prod = pp.findById(id);
		if(prod.isEmpty()) {
			return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não há produtos com este id");
		}
		p = prod.get();
		String dados = sr.produtoSerializar(p);
			return  ResponseEntity.status(HttpStatus.OK).body(dados);
		} catch (Exception e) {
			e.printStackTrace();
			return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Falha ao realizar a busca" + e.getMessage());
		}
		
	}
	
	
	
	
	@GetMapping("/buscarPorNome")
	@PreAuthorize("hasAuthority('PRODUTO_READ_PRIVILEGE')")
	public ResponseEntity<String> findById(@RequestParam(value="nome") String nome){
		Produto p = new Produto();
		Optional<Produto> prod;
		try {
		prod = pp.findByNome(nome);
		if(prod.isEmpty()) {
			return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não há produtos com este nome");
		}
		p = prod.get();
		String dados = sr.produtoSerializar(p);
			return  ResponseEntity.status(HttpStatus.OK).body(dados);
		} catch (Exception e) {
			e.printStackTrace();
			return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Falha ao realizar a busca" + e.getMessage());
		}
		
	}
	
	
	
	
	@GetMapping
	@PreAuthorize("hasAuthority('PRODUTO_READ_PRIVILEGE')")
	public ResponseEntity<String> findAll(){
		List<Produto> produtos = new ArrayList<>();
		try {
			produtos = pp.findAll();
			if(produtos.size()==0) {
				return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não há registros");
			}
			String dados = sr.produtosSerializar(produtos);
			return  ResponseEntity.status(HttpStatus.OK).body(dados);
		} catch (Exception e) {
			e.printStackTrace();
			return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Falha ao listar produtos" + e.getMessage());
		}
		
	}
	
	
	
	@DeleteMapping
	@PreAuthorize("hasAuthority('PRODUTO_WRITE_PRIVILEGE')")
	public ResponseEntity<String> deleteById(@RequestParam(value="id") Long id){
		Produto p = new Produto();
		Optional<Produto> prod;
		try {
		prod = pp.findById(id);
		if(prod.isEmpty()) {
	        	 return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não há registro para o id");
	         }
	         
	         p = prod.get();
	         pp.delete(p);
			return  ResponseEntity.status(HttpStatus.OK).body("O Registro foi excluido com sucesso !");
		} catch (Exception e) {
			e.printStackTrace();
			return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Falha ao realizar a exclusão" + e.getMessage());
		}
		
	}
	
	@PutMapping
	@PreAuthorize("hasAuthority('PRODUTO_WRITE_PRIVILEGE')")
	public ResponseEntity<String> update(@RequestBody Produto produto ){
		
		try {
			pp.save(produto);
			return  ResponseEntity.status(HttpStatus.OK).body("Alteração realizada com sucesso !");
		} catch (Exception e) {
			e.printStackTrace();
			return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Falha ao alterar cadastro" + e.getMessage());
		}
		
	}
	
	
	
	
	
	
	

}
