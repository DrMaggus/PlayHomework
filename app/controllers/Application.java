package controllers;

import java.io.IOException;
import java.util.List;

import play.mvc.*;
import play.mvc.Http.MultipartFormData;
import play.mvc.Http.MultipartFormData.FilePart;
import play.data.Form;

import utils.*;
import views.html.*;
import models.SignIn;

public class Application extends Controller {
  	
    public static Result index() {
        return ok(index.render());
    }
    
    public static Result login() {
    	return ok(login.render());
    }
    
    public static Result logout() {
    	session().remove("loggedInUser");
    	return redirect(routes.Application.index());
    }
    
    public static Result upload() {
    	if (session("loggedInUser") == null)
    		return redirect(routes.Application.login());
    	
    	return ok(upload.render());
    }
    
    public static Result processUpload() {

    	MultipartFormData body = request().body().asMultipartFormData();
    	FilePart txt = body.getFile("txt-file");
    	if (utils.allowedContentTypes.contains(txt.getContentType())) {
    		if (!utils.containsSpecialChar(txt.getFilename()))
    			try {
    				String absolutePath = new java.io.File( "." ).getCanonicalPath()
    						+ "/public/Files/"+session("loggedInUser")+"/"+txt.getFilename();
    				utils.saveFile(txt.getFile(), absolutePath);
    				flash("uploadSuccess", "true");
    			}
    			catch (IOException e) {
    				flash("uploadSuccess", "ioerror"); 
    		}
    		else
    			flash("uploadSuccess", "specialchar");
    	} 
    	else 
    		flash("uploadSuccess", "deniedCT");    

    	return redirect(routes.Application.index());
    }
  
    public static Result processLogin() {
    	
	  	SignIn signin = Form.form(SignIn.class).bindFromRequest().get();
	   	List<SignIn> user = SignIn.find().where("username = \""+signin.username+"\"").findList();
		   	   	
	   	if (!user.isEmpty()  && user.get(0).username.equals(signin.username)
	   			&& user.get(0).password.equals(security.SHA256HexHash(signin.username)))
		    session("loggedInUser",signin.username);
	   	else {
	   		flash("Loginfail","true");
	   		return redirect(routes.Application.login());
	   	}
	   	
	   	return redirect(routes.Application.index());
    }
    
    public static void main(String[] args) {
    	try {
    		System.out.println(new java.io.File( "." ).getCanonicalPath());
    	}
    	catch (IOException e){}
    }
}
