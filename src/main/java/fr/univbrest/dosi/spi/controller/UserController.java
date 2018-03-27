package fr.univbrest.dosi.spi.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.univbrest.dosi.spi.bean.Authentification;
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
	public boolean authentifier( HttpServletRequest request, @RequestBody Authentification authentification) {
        Authentification auth = userService.authentifier(authentification.getLoginConnection(), authentification.getMotPasse());

		if (auth != null) {
			request.getSession().setAttribute("user", auth);
			return true;
		} else {
			request.getSession().removeAttribute("user");
			return false;
		}
	}

	@RequestMapping(value = "/user")
	public Authentification users( HttpServletRequest request,  HttpServletResponse response) {
        Authentification auth = (Authentification) request.getSession().getAttribute("user");
		return auth;

	}

	@RequestMapping(value = "/deconnexion", method = RequestMethod.GET)
	public void authentifier(HttpServletRequest request) {
		request.getSession().removeAttribute("user");
	}

}
