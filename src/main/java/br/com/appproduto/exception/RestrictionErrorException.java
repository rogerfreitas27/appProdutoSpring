package br.com.appproduto.exception;



import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
/**Classe  responsavel pelas respostas de tratamento de error para violação de 
 * constraint
 * metodos sem permissão).
* @author Roger Freitas
* @version 1.01
* @since 
*/
public class RestrictionErrorException extends ResponseEntityExceptionHandler {
	
	
	
	 @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> DataIntegrityViolationException(
      Exception ex, WebRequest request) {
        return new ResponseEntity<Object>(
          "Violação de constraint, verifique os dados informados ou contate o administrador", new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

}
