package model;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
public class Service_Model {
	
	@Id
	@GeneratedValue
	private int  job_id;
	private String job_name;
	private int job_time;
	private double job_price;
	

	
	
	
	public Service_Model(int job_id, String job_name, int job_time,
			double job_price) {
		this.job_id = job_id;
		this.job_name = job_name;
		this.job_time = job_time;
		this.job_price = job_price;

	}
	
	
	public Service_Model(){}


	public int getJob_id() {
		return job_id;
	}


	public void setJob_id(int job_id) {
		this.job_id = job_id;
	}


	public String getJob_name() {
		return job_name;
	}


	public void setJob_name(String job_name) {
		this.job_name = job_name;
	}


	public int getJob_time() {
		return job_time;
	}


	public void setJob_time(int job_time) {
		this.job_time = job_time;
	}


	public double getJob_price() {
		return job_price;
	}


	public void setJob_price(double job_price) {
		this.job_price = job_price;
	}




	@Override
	public String toString() {
		return "Service_Model [job_id=" + job_id + ", job_name=" + job_name
				+ ", job_time=" + job_time + ", job_price=" + job_price
				+   "]";
	}






	
	
	
	
	
	
	
	
	
	

}
