package br.com.appproduto.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;



/**Classe responsável pela criação, validação de token.
* @author Roger Freitas
* @version 1.01
* @since 
*/
@Service
public class JwtUtil {
	
	
	
	 private  final String TOKEN_SENHA;// = "1cc48b2a-f32d-46bd-a3a0-1953f8f7e6fc";
   
	@Autowired
	public  JwtUtil (@Value("${chave}") String TOKEN_SENHA) {
		this.TOKEN_SENHA=TOKEN_SENHA;
		
	}

    public String extractUsername(String token)     {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token)     {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver)    {
        final Claims claims = extractAllClaims(token);
         return claimsResolver.apply(claims);
    }
    private Claims extractAllClaims(String token)   {
        return Jwts.parser().setSigningKey(TOKEN_SENHA).parseClaimsJws(token).getBody();
    }

    
    private Boolean isTokenExpired(String token)  {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(String username)   {
    	
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, username);
    }

    private String createToken(Map<String, Object> claims, String subject)   {

        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256,TOKEN_SENHA).compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails)    {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

}
