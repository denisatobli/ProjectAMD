package com.project.managedbeans;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.project.entity.Role;
import com.project.model.UserModel;
import com.project.service.UserService;
import com.project.utils.SessionUtils;

@ManagedBean(name = "user")
@SessionScoped
public class UserBean implements Serializable{

	private static final long serialVersionUID = 1L;
	private String firstname;
	private String lastname;
	private String phoneNumber;
	private String email;
	private String password;
	private UserModel userModel;

	private static UserService userService = new UserService();

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public UserModel getUserModel() {
		return userModel;
	}

	public void setUserModel(UserModel userModel) {
		this.userModel = userModel;
	}
	
	public String displayWelcomeMessage() {
		userModel = userService.getLoggedUser();
		if (userModel == null) {
			return "Account";
		} else
			return "Welcome " + userModel.getFirstname();
	}

	public String login() {
		int id = userService.validateUser(email, password);
		boolean valid = (id != 0);
		if (valid) {
			Role role = userService.getUserById(id).getRole();
			
				HttpSession session = SessionUtils.getSession();
				session.setAttribute("email", email);
				session.setAttribute("userId", id);

				session.setAttribute("role", role);
				
				switch (role) {
				case ADMIN:
					return "admin/adminLandingPage?faces-redirect=true";
				case USER:
					return "user/userLandingPage?faces-redirect=true";
				}
			 
		} else {
			FacesContext.getCurrentInstance().addMessage("forms",
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Wrong email or password!", null));
			return "login";
		}

		return "";
	}
	
	public String insertUser() {
		FacesContext context = FacesContext.getCurrentInstance();
		if(userService.insertUser(firstname, lastname, email, password, phoneNumber)) {
			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "The user was added!", null));
		}
		else {
			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "The user was not added!", null));
		}
		return "addUser";
	}

	public String logout() {
		HttpSession session = SessionUtils.getSession();
		if (session != null)
			session.invalidate();
		return "/login.xhtml?faces-redirect=true";
	}

}

