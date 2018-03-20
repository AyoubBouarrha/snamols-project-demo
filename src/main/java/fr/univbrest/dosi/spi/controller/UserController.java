package fr.univbrest.dosi.spi.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.univbrest.dosi.spi.bean.User;
import fr.univbrest.dosi.spi.exception.SPIException;
import fr.univbrest.dosi.spi.service.UserService;

@RestController
public class UserController {

	@Autowired
	UserService userService;

	@RequestMapping(value = "/auth", method = RequestMethod.POST, headers = "Accept=application/json")
	public boolean authentifier( HttpServletRequest request, @RequestBody  User user) {
		User users = userService.authentifier(user.getUsername(), user.getPwd());

		if (users != null) {
			request.getSession().setAttribute("user", users);
			return true;
		} else {
			request.getSession().removeAttribute("user");
			return false;
		}
	}

	@RequestMapping(value = "/user")
	public User users( HttpServletRequest request,  HttpServletResponse response) {
		User user = (User) request.getSession().getAttribute("user");
		return user;

	}

	@RequestMapping(value = "/deconnexion", method = RequestMethod.GET)
	public void authentifier(HttpServletRequest request) {
		request.getSession().removeAttribute("user");
	}

}
