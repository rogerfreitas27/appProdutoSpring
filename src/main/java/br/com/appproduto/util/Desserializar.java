package br.com.appproduto.util;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.appproduto.model.Produto;

@Service
public class Desserializar {
	ObjectMapper mapper = new ObjectMapper();
	
	public Produto desserializaProduto(String Prod)throws Exception {
           Produto produto = null;	
		   produto = mapper.readValue(Prod, Produto.class);
		   return produto;
	}
}
