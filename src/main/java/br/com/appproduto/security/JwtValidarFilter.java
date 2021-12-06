package br.com.appproduto.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import io.jsonwebtoken.ExpiredJwtException;

/**Classe responsável por filtrar todas as requisições, as unicas requisições que 
 * não são interceptadas são as requisições que tem o metodo permitAll() 
 * na classe Configuração->configure(HttpSecurity http)
* @author Roger Freitas
* @version 1.01
* @since 
*/
@Component
public class JwtValidarFilter extends OncePerRequestFilter {
	
	
	 private final JwtUtil jwtUtil;
	 private final DetalheClienteServiceImpl service;
	 
	

	    public JwtValidarFilter(JwtUtil jwtUtil,DetalheClienteServiceImpl service) {
	    	this.jwtUtil= jwtUtil;
	    	this.service = service;
	       
	    }
	
	
/**Método filtra as requisições
* Neste método  
* 1° Na requisição no cabeçalho (header)eu pego o token
* 2° Envio o token para JwtUtil e extraio o nome de usuario(Login)
* 3° Envio o nome de usuario (Login) para DetalheClienteSrvice e carrego as informações do usuario logado
* 4° Envio o token e o usuario logado para JJwtUtil para saber se os logins são iguais
* e saber se o token é valido
* 5° Se o passo 4 for verdadeiro eu armazeno o usuario e as permissões  no  UsernamePasswordAuthenticationToken (Spring security)
* 6° Sigo o fluxo da requisição (pedido do usuario)
* @author Roger
* @param um identificador unico do usuario 
* @return  User do Spring Security
*/

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String authorizationHeader = request.getHeader("Authorization");
		
		System.out.println("header filtro ->" + authorizationHeader);

        String token = null;
        String userName = null;
      
       try {
     
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            token = authorizationHeader.substring(7);
            System.out.println("token ->" + token);
            
            userName = jwtUtil.extractUsername(token);
        }

        if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails = service.loadUserByUsername(userName);

            if (jwtUtil.validateToken(token, userDetails)) {

                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
	    
       
        filterChain.doFilter(request, response);
       
      } catch(ExpiredJwtException eje) {   
    	  // log.info("Erro ao decodificar o token",
    	           // eje.getClaims().getSubject(), eje.getMessage()); caso eu esteja em duvida sobre a expiração do token é só descomentar
    			//   eje.getMessage());
    	

    	  
    	 System.out.println("Token invalido");
    	   
    	 
    	  ((HttpServletResponse) response).setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    	

    	
    	
    	
    	
    	   
    	
    	 
    	 
       }
       
      
       
       
     
        
      
    }
	    
	    
	}


