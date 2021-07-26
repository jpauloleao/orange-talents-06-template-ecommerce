package br.com.zup.orange.MercadoLivre.TratamentoErros;

import java.util.List;


import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class ValidationErrorHandler {

	 @Autowired
	    private MessageSource messageSource;
	  

	    @ResponseStatus(HttpStatus.BAD_REQUEST)
	    @ExceptionHandler(MethodArgumentNotValidException.class)
	    public ValidationErrorsOutputDto argumentosInvalidos(MethodArgumentNotValidException exception) {

	        List<ObjectError> erros = exception.getBindingResult().getGlobalErrors();
	        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();

	        ValidationErrorsOutputDto va = new ValidationErrorsOutputDto(messageSource);
	        
	        return va.buildValidationErrors(erros, fieldErrors);
	    }
	    
	    @ResponseStatus(HttpStatus.BAD_REQUEST)
	    @ExceptionHandler(NullPointerException.class)
	    public ValidationErrorsOutputDto argumentoNulos(NullPointerException exception) {

	    	   ValidationErrorsOutputDto va = new ValidationErrorsOutputDto(messageSource);
		        va.addError(exception.getMessage());
		        
		        return va;
	    }
	    
	  
	    	    
}
