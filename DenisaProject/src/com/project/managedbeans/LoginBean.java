package com.project.managedbeans;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.project.entity.Role;
import com.project.service.UserService;
import com.project.utils.SessionUtils;

@ManagedBean(name = "user")
@SessionScoped
public class LoginBean {

	private String email;
	private String password;

	private static UserService userService = new UserService();

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
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Email ose fjalekalim i pasakte!", null));
			return "login";
		}

		return "";
	}

	public String logout() {
		HttpSession session = SessionUtils.getSession();
		if (session != null)
			session.invalidate();
		return "/index.xhtml?faces-redirect=true";
	}

}

