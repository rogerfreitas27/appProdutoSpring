package br.com.appproduto.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.appproduto.model.Categoria;
import br.com.appproduto.repository.CategoriaRepository;

@Service
public class CategoriaPersistence {
	private final CategoriaRepository cr;
	
	public  CategoriaPersistence (CategoriaRepository cr) {
		this.cr=cr;
	}
	
	
	public Categoria save(Categoria c) {		
		return cr.save(c);
	}
	
		
	public List<Categoria> findAll () {
		return cr.findAll();
	}
	
	
	public void delete(Categoria c)  {
		cr.delete(c);
	}
	
	public Optional<Categoria> findById(Long id) {
		 return cr.findById(id);
	}

	/**
	 * Este metodo é privado  para que ninguém sem autorização  tenha acesso   
	 * @return
	 * @throws Exception
	 */
	private int contadorCategoria()  {
		int  cont = cr.findAll().size();
	return cont;
	}
	
	public int RespcontadorCategoria(){
		return   contadorCategoria(); 
	}
	
	
	
	public Optional<Categoria> findByNome(Categoria c) {
		return cr.findByNome(c);
		
	}
	
	
}
