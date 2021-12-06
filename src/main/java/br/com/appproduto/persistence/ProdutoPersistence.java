package br.com.appproduto.persistence;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;


import br.com.appproduto.model.Produto;
import br.com.appproduto.repository.ProdutoRepository;

@Service
public class ProdutoPersistence {
	
	private final ProdutoRepository  pr;
	
	public  ProdutoPersistence (ProdutoRepository  pr) {
		this.pr=pr;
	}
	
	
	public Produto save(Produto p) throws Exception{
		return pr.save(p);
	}
	
	public List<Produto> findAll () throws Exception{
		return pr.findAll();
	}
	
	
	public void delete(Produto p) throws Exception {
		pr.delete(p);
	}
	
	public Optional<Produto> findById(Long id) throws Exception {
		 return pr.findById(id);
	}
	
	public Optional<Produto> findByNome(String nome) throws Exception {
		 return pr.findByNomeIgnoreCase(nome);
	}


	private int contadorProduto()  throws Exception{
		int  cont = pr.findAll().size();
	return cont;
	}
	
	public int RespcontadorProduto() throws Exception{
		return   contadorProduto(); 
	}
}
