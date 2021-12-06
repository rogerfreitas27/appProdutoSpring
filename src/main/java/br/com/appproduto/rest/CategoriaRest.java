package br.com.appproduto.rest;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

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
import br.com.appproduto.model.Categoria;
import br.com.appproduto.persistence.CategoriaPersistence;
import br.com.appproduto.util.Serializar;

@RestController
@RequestMapping("/api/categoria")
public class CategoriaRest {
	
	private final CategoriaPersistence cp;
	private final Serializar sr;
	
	public CategoriaRest(CategoriaPersistence cp, Serializar sr) {
		
		
		this.cp = cp;
		this.sr = sr;
	}
	
	@PostMapping
	@PreAuthorize("hasAuthority('CATEGORIA_WRITE_PRIVILEGE')")
	public ResponseEntity<String> save(@RequestBody   @Valid Categoria categoria ){
		
		try {
			cp.save(categoria);
			return  ResponseEntity.status(HttpStatus.CREATED).body("Cadastro realizado com sucesso !");
		} catch (Exception i) {
			return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Falha ao realizar cadastro");
		}
		
	}
	
	
	
	@GetMapping("/buscarPorId")
	@PreAuthorize("hasAuthority('CATEGORIA_READ_PRIVILEGE')")
	public ResponseEntity<String> findById(@RequestParam(value="id") Long id){
		Categoria c = new Categoria();
		Optional<Categoria> cat;
		try {
		cat = cp.findById(id);
		if(cat.isEmpty()) {
			return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não há categoria com este id");
		}
		c = cat.get();
		String dados = sr.categoriaSerializar(c);
			return  ResponseEntity.status(HttpStatus.OK).body(dados);
		} catch (Exception e) {
			e.printStackTrace();
			return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Falha ao realizar a busca" + e.getMessage());
		}
		
	}
	
	
	@GetMapping
	@PreAuthorize("hasAuthority('CATEGORIA_READ_PRIVILEGE')")
	public ResponseEntity<String> findAll(){
		
		
		List<Categoria> categorias = new ArrayList<>();
		try {
			categorias = cp.findAll();
			if(categorias.size()==0) {
				return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não há registros");
			}
			String dados = sr.categoriasSerializar(categorias);
			return  ResponseEntity.status(HttpStatus.OK).body(dados);
		} catch (Exception e) {
			e.printStackTrace();
			return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Falha ao listar categorias" + e.getMessage());
		}
		
	}
	
	
	
	@DeleteMapping
	@PreAuthorize("hasAuthority('CATEGORIA_WRITE_PRIVILEGE')")
	public ResponseEntity<String> deleteById(@RequestParam(value="id") Long id){
		Categoria c = new Categoria();
		Optional<Categoria> cat;
		try {
		cat = cp.findById(id);
		if(cat.isEmpty()) {
	        	 return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não há registro para o id");
	         }
	         
	         c = cat.get();
	         cp.delete(c);
			return  ResponseEntity.status(HttpStatus.OK).body("O Registro foi excluido com sucesso !");
		} catch (Exception e) {
			e.printStackTrace();
			return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Falha ao realizar a exclusão" + e.getMessage());
		}
		
	}
	
	@PutMapping
	@PreAuthorize("hasAuthority('CATEGORIA_WRITE_PRIVILEGE')")
	public ResponseEntity<String> update(@RequestBody Categoria categoria ){
		
		try {
			cp.save(categoria);
			return  ResponseEntity.status(HttpStatus.OK).body("Alteração realizada com sucesso !");
		} catch (Exception e) {
			e.printStackTrace();
			return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Falha ao alterar cadastro" + e.getMessage());
		}
		
	}
	
	
	
	
	
	

}
