package br.com.zup.orange.MercadoLivre.Valicacoes;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.util.Assert;

public class ObjectExistsValidator implements ConstraintValidator<ObjectExists, Object> {
 	private String domainAttribute;
    private Class<?> klass;
    
    @PersistenceContext
    private EntityManager manager;

    
    @Override
    public void initialize(ObjectExists params) {
        domainAttribute = params.fieldName();
        klass = params.domainClass();
    }

    
    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        Query query = manager.createQuery("select 1 from " + klass.getName() + " where " + domainAttribute + " = :value");
        query.setParameter("value", value);
        List<?> list = query.getResultList();
        Assert.state(list.size() <= 1, "Não foi encontrado um "+ domainAttribute);

        return !list.isEmpty();
    }
}
