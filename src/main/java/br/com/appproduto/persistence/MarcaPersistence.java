package br.com.appproduto.persistence;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;


import br.com.appproduto.model.Marca;
import br.com.appproduto.repository.MarcaRepository;

@Service
public class MarcaPersistence {
	
	private final MarcaRepository mr;
	
	public  MarcaPersistence (MarcaRepository mr)  {
		this.mr=mr;
	}
	
	
	public  Marca save( Marca m) throws Exception{
		return mr.save(m);
	}
	
	public List< Marca> findAll () throws Exception{
		return mr.findAll();
	}
	
	
	public void delete( Marca m) throws Exception{
		mr.delete(m);
	}
	
	public Optional< Marca> findById(Long id) throws Exception {
		 return mr.findById(id);
	}
	
	
	private int contadorMarca()  throws Exception{
		int  cont = mr.findAll().size();
	return cont;
	}
	
	public int RespcontadorMarca() throws Exception{
		return   contadorMarca(); 
	}

}
