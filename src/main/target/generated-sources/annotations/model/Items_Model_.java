package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Items_Model.class)
public abstract class Items_Model_ {

	public static volatile SingularAttribute<Items_Model, Integer> id;
	public static volatile SingularAttribute<Items_Model, Double> item_price;
	public static volatile SingularAttribute<Items_Model, String> item_name;
	public static volatile SingularAttribute<Items_Model, Integer> item_quantity;
	public static volatile SingularAttribute<Items_Model, Integer> invoice_id;

}

