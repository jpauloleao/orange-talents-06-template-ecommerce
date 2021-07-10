package br.com.zup.orange.MercadoLivre.Valicacoes;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = {ObjectExistsValidator.class})
@Target({ FIELD})
@Retention(RUNTIME)
public @interface ObjectExists {
	
	String message() default "{O objeto não existe}";
    
    
    Class<?>[] groups() default {};
    
    
    Class<? extends Payload>[] payload() default {};
    
    
    String fieldName();
    
    
    Class<?> domainClass();
    
}
