package fr.univbrest.dosi.spi.util;

import fr.univbrest.dosi.spi.bean.Authentification;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Connection {

    public static Authentification currentUser(HttpServletRequest request) {
        Authentification auth = (Authentification) request.getSession().getAttribute("user");
        return auth;

    }
}
