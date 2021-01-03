package il.ac.afeka.cloud.resolvers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import il.ac.afeka.cloud.data.Post;
import il.ac.afeka.cloud.data.User;
import il.ac.afeka.cloud.data.UserDao;

@Component
public class UserByEmailDataFetcher implements DataFetcher<User> {
	private UserDao userDao;
	
	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public User get(DataFetchingEnvironment environment) throws Exception {
		Post post = environment.getSource();
        User user = post.getUser();
        // Get the User from the Database by the email found in the Post
		return this.userDao.findById(user.getEmail()).orElse(null);
	}

}
