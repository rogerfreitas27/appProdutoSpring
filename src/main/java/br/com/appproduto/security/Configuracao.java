package br.com.appproduto.security;

import java.util.Arrays;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
/**Classe de configuração, esta classe é responsavel por liberar e permitir as rotas.
* @author Roger Freitas
* @version 1.01
* @since 
*/
public class Configuracao  extends WebSecurityConfigurerAdapter {
	
	
    private final DetalheClienteServiceImpl clienteService;
    private final PasswordEncoder passwordEncoder;
    private final JwtValidarFilter jwtvalidarFilter;

    public Configuracao(DetalheClienteServiceImpl usuarioService, PasswordEncoder passwordEncoder,JwtValidarFilter jwtvalidarFilter) {
        this.clienteService = usuarioService;
        this.passwordEncoder = passwordEncoder;
        this.jwtvalidarFilter=jwtvalidarFilter;
    }
	
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(clienteService).passwordEncoder(passwordEncoder);
    }
    
    
       
    
    @Override
    /**Métodos permitidos sem autenticação, esse método ajuda a proteger de ataques cscrf
	 * fto para viagem.
	 * @author Roger
	 * @param HttpSecurity 
	 * @return  UsernamePasswordAuthenticationToken - que é um usuario autenticado com login, senha e a lista de permissões
	 */
    protected void configure(HttpSecurity http) throws Exception {
    	
    	   	
    	
    	http.cors()
    	.and()
    	.csrf()    	
    	.disable()    	
    	.authorizeRequests()
    	//.antMatchers(HttpMethod.POST,"/api/usuario/cadastro").permitAll()
    	.antMatchers(HttpMethod.POST,"/api/usuario/autenticar").permitAll()  
    	.antMatchers("/api/usuario/recuperarAcesso").permitAll()
    	.antMatchers("/api/usuario/codificar").permitAll()
    	.anyRequest()
        .authenticated()
        .and()
        .exceptionHandling()
        .and().sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
http.addFilterBefore(jwtvalidarFilter, UsernamePasswordAuthenticationFilter.class);

    	
    	
    	
    	
    	
    }
    	
           
        
    
    
    
    
    	
    	
    	
    
    
    

    @Bean
    /**Método para liberar o cors
	 * @author Roger
	 * @param 
	 * @return  UsernamePasswordAuthenticationToken - que é um usuario autenticado com login, senha e a lista de permissões
	 */
    CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        CorsConfiguration corsConfiguration = new CorsConfiguration().applyPermitDefaultValues();
        corsConfiguration.setAllowedOrigins(Arrays.asList("*"));
        corsConfiguration.setAllowedMethods(Arrays.asList("HEAD" ,"GET" , "POST" , "PUT" , "DELETE "));
        //corsConfiguration.setAllowCredentials(true);
      //  corsConfiguration.setAllowedHeaders((Arrays.asList("*")));
    //   corsConfiguration.setAllowedHeaders((Arrays.asList("Authorization", "Cache-Control", "Content-Type")));
       corsConfiguration.addAllowedHeader("*");
       corsConfiguration.addAllowedMethod("*");       
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }
    
    

    
    
    
    
    
    
    
    
    
    
    
}
