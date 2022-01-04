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
import br.com.appproduto.dto.UsuarioDto;
import br.com.appproduto.model.User;
import br.com.appproduto.model.Usuario;
import br.com.appproduto.persistence.UsuarioPersistence;
import br.com.appproduto.repository.UserRepository;
import br.com.appproduto.security.AuthProviderService;
import br.com.appproduto.service.UsuarioService;
import br.com.appproduto.util.Serializar;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioRest {

	
	private final UsuarioPersistence up ;
	private final Serializar sr;
	private final AuthProviderService auth ;	
	private final UserRepository userRepository;	
	private final UsuarioService ususerv;
	
	
	  
	public UsuarioRest(UsuarioPersistence up, Serializar sr, AuthProviderService auth,
			UserRepository userRepository,UsuarioService ususerv) {
		this.up = up;
		this.sr = sr;
		this.auth=auth;
		this. userRepository=  userRepository;
		
		this.ususerv=ususerv;
		
		
	}
	
	
	@PostMapping
	@PreAuthorize("hasAuthority('USUARIO_WRITE_PRIVILEGE')")
	public ResponseEntity<String> save(@RequestBody Usuario usuario ){
		
		try {
			up.save(usuario);
			return  ResponseEntity.status(HttpStatus.CREATED).body("Cadastro realizado com sucesso !");
		} catch (Exception e) {
			e.printStackTrace();
			return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Falha ao realizar cadastro" + e.getMessage());
		}
		
	}
	
	
	
	@GetMapping("/buscarPorId")
	@PreAuthorize("hasAuthority('USUARIO_READ_PRIVILEGE')")
	public ResponseEntity<String> findById(@RequestParam(value="id") Long id){
		Usuario u = new Usuario();
		Optional<Usuario> usu;
		try {
		usu = up.findById(id);
		if(usu.isEmpty()) {
			return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não há usuário com este id");
		}
		u = usu.get();
				    	 
    	 Optional<User> user =  userRepository.findByUsuario(u);
    	 
    	 if(user.isEmpty()) {
    		 return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao buscar usuario");
    	 }
    	 
    	 // Aqui o user é o usuário  do spring security
    	 User us = new User();
    	 us = user.get();
		
		
		String dados = ususerv.carregarFrontUsuarioDto(us, u);
			return  ResponseEntity.status(HttpStatus.OK).body(dados);
		} catch (Exception e) {
			e.printStackTrace();
			return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Falha ao realizar a busca" + e.getMessage());
		}
		
	}
	
	
	
	
	@GetMapping("/buscarPorEmail")
	@PreAuthorize("hasAuthority('USUARIO_READ_PRIVILEGE')")
	public ResponseEntity<String> findByEmail(@RequestParam(value="email") String email){
		Usuario u = new Usuario();
		Optional<Usuario> usu;
		try {
		usu = up.findByEmail(email);
		if(usu.isEmpty()) {
			return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não há usuário com este email");
		}
		u = usu.get();	 
		UsuarioDto dto = new UsuarioDto();
		dto.setEmail(u.getEmail());
		dto.setId(u.getId());
		dto.setNome(u.getNome());
		   String dados = sr.UsuarioDtoSerializar(dto);
			return  ResponseEntity.status(HttpStatus.OK).body(dados);
		} catch (Exception e) {
			e.printStackTrace();
			return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Falha ao realizar a busca por email" + e.getMessage());
		}
		
	}
	
	
	
	
	
	
	@GetMapping
	@PreAuthorize("hasAuthority('USUARIO_READ_PRIVILEGE')")
	public ResponseEntity<String> findAll(){
		List<Usuario> usuarios = new ArrayList<>();
		try {
			usuarios = up.findAll();
			if(usuarios.size()==0) {
				return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não há registros");
			}
			String dados = sr.usuariosSerializar(usuarios);
			return  ResponseEntity.status(HttpStatus.OK).body(dados);
		} catch (Exception e) {
			e.printStackTrace();
			return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Falha ao listar usuários" + e.getMessage());
		}
		
	}
	
	
	@PostMapping("/autenticar")
	public ResponseEntity<String> authenticar (@RequestBody UsuarioDto usuario ){
	//(@RequestBody Usuario usuario ){
		Optional<Usuario> usu;
		Usuario u = new Usuario();
		try {
			
        	auth.authenticate(usuario);	       
        	 usu = up.findByEmail(usuario.getEmail());
        	 
        	 if( usu.isEmpty()) {
        		 return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao buscar usuario");
        	 }
        	 
        	 u = usu.get();
        	 
        	 Optional<User> user =  userRepository.findByUsuario(u);
        	 
        	 if(user.isEmpty()) {
        		 return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao buscar usuario");
        	 }
        	 
        	 // Aqui o user é o usuário  do spring security
        	 User us = new User();
        	 us = user.get();
        	 
        	        	 
           String dados = ususerv.carregarUsuarioDto(us, u);
			return  ResponseEntity.status(HttpStatus.CREATED).body(dados);
		} catch (Exception e) {
			e.printStackTrace();
			return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Falha ao realizar a autenticação" + e.getMessage());
		}
		
	}
	
	
	@DeleteMapping
	@PreAuthorize("hasAuthority('USUARIO_WRITE_PRIVILEGE')")
	public ResponseEntity<String> deleteById(@RequestParam(value="id") Long id){
		Usuario u = new Usuario();
		Optional<Usuario> usu;
		try {
	         usu = up.findById(id);
	         if(usu.isEmpty()) {
	        	 return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não há registro para o id");
	         }
	         
	         u = usu.get();
	         up.delete(u);
			return  ResponseEntity.status(HttpStatus.OK).body("O Registro foi excluido com sucesso !");
		} catch (Exception e) {
			e.printStackTrace();
			return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Falha ao realizar a exclusão" + e.getMessage());
		}
		
	}
	
	
	
	
	@PutMapping
	@PreAuthorize("hasAuthority('USUARIO_WRITE_PRIVILEGE')")
	public ResponseEntity<String> update(@RequestBody Usuario usuario ){
		
		try {
			up.save(usuario);
			return  ResponseEntity.status(HttpStatus.OK).body("Alteração realizada com sucesso !");
		} catch (Exception e) {
			e.printStackTrace();
			return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Falha ao alterar cadastro" + e.getMessage());
		}
		
	}
	
	
	@GetMapping("/codificar")
	public void teste() {
	System.out.println("Testando !!");
		
		
		
		System.out.println("Teste docker e endpoint");
	}
	
	
	
	 
	
	
}
