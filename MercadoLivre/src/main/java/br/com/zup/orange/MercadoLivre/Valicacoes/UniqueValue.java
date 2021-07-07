package br.com.zup.orange.MercadoLivre.Valicacoes;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
 
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;


//Responsavel por criar a anotação
@Documented
@Constraint(validatedBy = {UniqueValueValidator.class})
@Target({ FIELD})
@Retention(RUNTIME)
public @interface UniqueValue {

	
    String message() default "{O campo dever ser único}";
    
    
    Class<?>[] groups() default {};
    
    
    Class<? extends Payload>[] payload() default {};
    
    
    String fieldName();
    
    
    Class<?> domainClass();
    
    
}
