package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Parts_Model.class)
public abstract class Parts_Model_ {

	public static volatile SingularAttribute<Parts_Model, String> part_name;
	public static volatile SingularAttribute<Parts_Model, Integer> part_id;
	public static volatile SingularAttribute<Parts_Model, Double> part_price;
	public static volatile SingularAttribute<Parts_Model, String> part_description;

}

