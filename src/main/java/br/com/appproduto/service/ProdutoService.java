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
	
	public void save(Produto produto, MultipartFile foto) {
     
		
			
		
		try {
					
		String url = as.uploadFile(foto,produto.getNome());
		produto.setUrl_imagem(url);		
		System.out.println(produto.getUrl_imagem());
		pp.save(produto);
		}catch(Exception e) {
			 as.deleteFile(produto.getNome());
			  e.getMessage();
		}
		
		
	}
	
	
	
	public void deleteFile(String nomeImagem) throws Exception {
		as.deleteFile(nomeImagem);
	}

}
