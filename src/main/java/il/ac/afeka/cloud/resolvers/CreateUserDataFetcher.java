package il.ac.afeka.cloud.resolvers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import il.ac.afeka.cloud.data.Name;
import il.ac.afeka.cloud.data.User;
import il.ac.afeka.cloud.data.UserDao;

@Component
public class CreateUserDataFetcher implements DataFetcher<User> {
	private UserDao userDao;
	
	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public User get(DataFetchingEnvironment environment) throws Exception {
		// Get InputUser from the mutation
		Map<String, Object> input = environment.getArgument("input");
			
		// Get all of the arguments from the InputUser
		String email = input.get("email").toString();
		String password = input.get("password").toString();
		String birthdate = input.get("birthdate").toString();
		String first = input.get("first").toString();
		String last = input.get("last").toString();
		Set<String> roles = new HashSet(((ArrayList)input.get("roles")));
		
		if (this.userDao.existsById(email)) {
			throw new RuntimeException("Can not create user because user already exists.");
		}
					
		// Create a new User Entity
		User entity = new User(email, new Name(first, last), birthdate, password, roles);
				
		// Save and return the new saved User
		return this.userDao.save(entity);
	}

}
