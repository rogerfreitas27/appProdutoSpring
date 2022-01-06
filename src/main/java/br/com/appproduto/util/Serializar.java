package br.com.appproduto.util;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.appproduto.dto.UsuarioDto;
import br.com.appproduto.model.Categoria;
import br.com.appproduto.model.Marca;
import br.com.appproduto.model.Privilegio;
import br.com.appproduto.model.Produto;
import br.com.appproduto.model.Role;
import br.com.appproduto.model.Usuario;

@Service
/**Classe   responsavel por serializar objetos(converter em json) .
* @author Roger Freitas
* @version 1.01
* @since 
*/
public class Serializar {
	ObjectMapper objectMapper = new ObjectMapper();
	
	
	public String categoriaSerializar(Categoria c ) throws Exception {
		
				
		String dados = objectMapper.writeValueAsString(c);
		return dados;
		
	}
	
	
    public String categoriasSerializar(List<Categoria> categorias ) throws Exception {
    		
		String dados = objectMapper.writeValueAsString(categorias);
		return dados;
	}
    
    
    
public String permissaoSerializar(Role per ) throws Exception {
		
	String dados = objectMapper.writeValueAsString(per);
	return dados;
	}
	
	
    public String permissoesSerializar(List<Role> permissoes ) throws Exception {
    	
		String dados = objectMapper.writeValueAsString(permissoes);
		return dados;
	}
    
    
    
public String produtoSerializar(Produto p ) throws Exception {
			
	String dados = objectMapper.writeValueAsString(p);
	return dados;
	}
	
	
    public String produtosSerializar(List<Produto> produtos ) throws Exception {
    			
		String dados = objectMapper.writeValueAsString(produtos);
		return dados;
	}
    
    
public String usuarioSerializar(Usuario u ) throws Exception {
			
	String dados = objectMapper.writeValueAsString(u);
	return dados;
	}
	
	
    public String usuariosSerializar(List<Usuario>  usuarios) throws Exception {
    			
    	String dados = objectMapper.writeValueAsString(usuarios);
    	return dados;
	}
    
    
public String marcaSerializar(Marca m ) throws Exception {
			
	String dados = objectMapper.writeValueAsString(m);
	return dados;
	}
	
	
    public String marcasSerializar(List<Marca> marcas) throws Exception {
    	//ObjectMapper objectMapper = new ObjectMapper();		
    	String dados = objectMapper.writeValueAsString(marcas);
    	return dados;
	}
    
public String UsuarioDtoSerializar(UsuarioDto usudto ) throws Exception {
		
		String dados = objectMapper.writeValueAsString(usudto);
		return dados;
		
	}

public String rolesSerializar(List<Role> roles ) throws Exception {
	String dados = objectMapper.writeValueAsString(roles);
	return dados;
}


public String privilegiosSerializar(List<Privilegio>privilegios ) throws Exception {
	String dados = objectMapper.writeValueAsString(privilegios);
	return dados;
}


}
