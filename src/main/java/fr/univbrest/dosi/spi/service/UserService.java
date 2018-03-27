package fr.univbrest.dosi.spi.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.univbrest.dosi.spi.bean.Authentification;
import fr.univbrest.dosi.spi.dao.AuthentificationRepository;
import fr.univbrest.dosi.spi.dao.RubriqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.univbrest.dosi.spi.bean.User;

@Service
public class UserService {

	private final Map<String, User> mapBouchonUser;


	@Autowired
    private AuthentificationRepository authentificationRepository;

	public UserService() {
		mapBouchonUser = new HashMap<String, User>();
		mapBouchonUser.put("chakib", new User("chakib", "1234", Arrays.asList("Admin")));
		mapBouchonUser.put("zou", new User("zou", "1234", Arrays.asList("visiteur")));
		mapBouchonUser.put("mouad", new User("mouad", "1234", Arrays.asList("Prof")));
	}

	/**
	 * @param login
	 * @param pwd
	 * @return
	 */
	public User authentifier(final String login, final String pwd) {
		final User user = mapBouchonUser.get(login);
		if(user==null){
            List<Authentification> listAuth =  authentificationRepository.findByLoginConnectionAndMotPasse(login,pwd);

        }
		else if (user != null && user.getPwd().equals(pwd)) {
			return user;
		}
		return null;
	}

}
