package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Sales_Model.class)
public abstract class Sales_Model_ {

	public static volatile SingularAttribute<Sales_Model, Integer> id;
	public static volatile SingularAttribute<Sales_Model, String> model;
	public static volatile SingularAttribute<Sales_Model, Double> price;
	public static volatile SingularAttribute<Sales_Model, String> description;
	public static volatile SingularAttribute<Sales_Model, String> reg;
	public static volatile SingularAttribute<Sales_Model, byte[]> image;
	public static volatile SingularAttribute<Sales_Model, String> make;

}

