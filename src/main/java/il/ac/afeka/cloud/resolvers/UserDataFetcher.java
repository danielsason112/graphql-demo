package il.ac.afeka.cloud.resolvers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import il.ac.afeka.cloud.data.User;
import il.ac.afeka.cloud.data.UserDao;

@Component
public class UserDataFetcher implements DataFetcher<User> {
	private UserDao userDao;
	
	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public User get(DataFetchingEnvironment environment) throws Exception {
		String email = environment.getArgument("email");
		return this.userDao.findById(email).orElse(null);
	}

}
