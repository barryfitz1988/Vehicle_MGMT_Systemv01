package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Invoice_Model.class)
public abstract class Invoice_Model_ {

	public static volatile SingularAttribute<Invoice_Model, Integer> id;
	public static volatile SingularAttribute<Invoice_Model, String> price;
	public static volatile SingularAttribute<Invoice_Model, String> vehiclereg;
	public static volatile SingularAttribute<Invoice_Model, String> service;
	public static volatile SingularAttribute<Invoice_Model, String> date;
	public static volatile SingularAttribute<Invoice_Model, String> customername;

}

