package models;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.db.ebean.Model;

@SuppressWarnings("serial")
@Entity
public class SignIn extends Model{
	
	@Id
	public long id;
	public String username;
	public String password;
	
	public static Model.Finder<Long, SignIn> find() {
		return  new Model.Finder<Long,SignIn>(Long.class, SignIn.class);
	}

}
