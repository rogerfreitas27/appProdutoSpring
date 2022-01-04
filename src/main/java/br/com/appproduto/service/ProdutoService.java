package br.com.appproduto.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.appproduto.aws.ArmazenamentoService;
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
	private final ArmazenamentoService as;
	
	public ProdutoService (ProdutoPersistence pp,ArmazenamentoService as ){
		this.pp=pp;
		this.as=as;
	}
	
	public void save(String produtoJson, MultipartFile foto) throws Exception{
     
		
		ObjectMapper mapper = new ObjectMapper();		
		Produto produto = null;		
		produto = mapper.readValue(produtoJson, Produto.class);	
		
		
		String url = as.uploadFile(foto);	
		produto.setUrl_imagem(url);
		
		System.out.println(produto.getUrl_imagem());
		pp.save(produto);
		
		
	}
	
	
	
	public void deleteFile(String nomeImagem) throws Exception {
		as.deleteFile(nomeImagem);
	}

}
