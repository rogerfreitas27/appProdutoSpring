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
	
	
	public Categoria save(Categoria c) throws Exception{
		return cr.save(c);
	}
	
	public List<Categoria> findAll () throws Exception{
		return cr.findAll();
	}
	
	
	public void delete(Categoria c) throws Exception {
		cr.delete(c);
	}
	
	public Optional<Categoria> findById(Long id)  throws Exception{
		 return cr.findById(id);
	}

	/**
	 * Este metodo é privado  para que ninguém sem autorização  tenha acesso   
	 * @return
	 * @throws Exception
	 */
	private int contadorCategoria()  throws Exception{
		int  cont = cr.findAll().size();
	return cont;
	}
	
	public int RespcontadorCategoria() throws Exception{
		return   contadorCategoria(); 
	}
}
