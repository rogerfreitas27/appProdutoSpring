package br.com.appproduto.servico;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.databind.ObjectMapper;
import br.com.appproduto.model.Produto;
import br.com.appproduto.persistence.ProdutoPersistence;

/**
 * @author roger
 * Esta classe é uma auxiliar para a classe ProdutoRest
 *
 */
@Service
public class ProdutoService {
	
	private final ProdutoPersistence pp;
	
	public ProdutoService (ProdutoPersistence pp){
		this.pp=pp;
	}
	
	public void save(String produtoJson, MultipartFile foto) throws Exception{
     
		
		ObjectMapper mapper = new ObjectMapper();		
		Produto produto = null;		
		System.out.println("categoria" +  produtoJson );
		System.out.println("foto->" + foto.getOriginalFilename());	
	     
		
	    produto = mapper.readValue(produtoJson, Produto.class);		
			
			pp.save(produto);
		
		
	}

}
