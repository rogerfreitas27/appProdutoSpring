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
import br.com.appproduto.model.Marca;
import br.com.appproduto.persistence.MarcaPersistence;
import br.com.appproduto.util.Serializar;

@RestController
@RequestMapping("/api/marca")
public class MarcaRest {
	
	
	private final MarcaPersistence mp;
	private final Serializar sr;
	public MarcaRest(MarcaPersistence mp, Serializar sr) {
		
		this.mp = mp;
		this.sr = sr;
	}
	
	
	@PostMapping
	@PreAuthorize("hasAuthority('MARCA_WRITE_PRIVILEGE')")
	public ResponseEntity<String> save(@RequestBody Marca marca ){
		
		try {
			mp.save(marca);
			return  ResponseEntity.status(HttpStatus.CREATED).body("Cadastro realizado com sucesso !");
		} catch (Exception e) {
			e.printStackTrace();
			return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Falha ao realizar cadastro, campo nome precisa ser único" );
		}
		
	}
	
	
	@GetMapping("/buscarPorId")
	@PreAuthorize("hasAuthority('MARCA_READ_PRIVILEGE')")
	public ResponseEntity<String> findById(@RequestParam(value="id") Long id){
		Marca m = new Marca();
		Optional<Marca> mar;
		try {
		mar = mp.findById(id);
		if(mar.isEmpty()) {
			return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não há marca com este id");
		}
		m = mar.get();
		String dados = sr.marcaSerializar(m);
			return  ResponseEntity.status(HttpStatus.OK).body(dados);
		} catch (Exception e) {
			e.printStackTrace();
			return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Falha ao realizar a busca" + e.getMessage());
		}
		
	}
	
	
	
	
	@GetMapping
	@PreAuthorize("hasAuthority('MARCA_READ_PRIVILEGE')")
	public ResponseEntity<String> findAll(){
		List<Marca> marcas = new ArrayList<>();
		try {
			marcas = mp.findAll();
			if(marcas.size()==0) {
				return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não há registros");
			}
			String dados = sr.marcasSerializar(marcas);
			return  ResponseEntity.status(HttpStatus.OK).body(dados);
		} catch (Exception e) {
			e.printStackTrace();
			return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Falha ao listar marcas" + e.getMessage());
		}
		
	}
	
	
	@DeleteMapping
	@PreAuthorize("hasAuthority('MARCA_WRITE_PRIVILEGE')")
	public ResponseEntity<String> deleteById(@RequestParam(value="id") Long id){
		Marca m = new Marca();
		Optional<Marca> mar;
		try {
		mar = mp.findById(id);
		if(mar.isEmpty()) {
			return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não há marca com este id");
		}
		m = mar.get();
	         mp.delete(m);
			return  ResponseEntity.status(HttpStatus.OK).body("O Registro foi excluido com sucesso !");
		} catch (Exception e) {
			e.printStackTrace();
			return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Falha ao realizar a exclusão" + e.getMessage());
		}
		
	}
	
	
	@PutMapping
	@PreAuthorize("hasAuthority('MARCA_WRITE_PRIVILEGE')")
	public ResponseEntity<String> update(@RequestBody Marca marca ){
		
		try {
			mp.save(marca);
			return  ResponseEntity.status(HttpStatus.OK).body("Alteração realizada com sucesso !");
		} catch (Exception e) {
			e.printStackTrace();
			return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Falha ao alterar cadastro" + e.getMessage());
		}
		
	}
	
	
	
	

}
