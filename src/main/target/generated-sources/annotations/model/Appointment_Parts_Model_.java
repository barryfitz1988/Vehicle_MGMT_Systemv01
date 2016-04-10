package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Appointment_Parts_Model.class)
public abstract class Appointment_Parts_Model_ {

	public static volatile SingularAttribute<Appointment_Parts_Model, Integer> id;
	public static volatile SingularAttribute<Appointment_Parts_Model, Parts_Model> part;
	public static volatile SingularAttribute<Appointment_Parts_Model, Appointment_Model> appointment;

}

