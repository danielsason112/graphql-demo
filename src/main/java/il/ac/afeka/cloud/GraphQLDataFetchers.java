package il.ac.afeka.cloud;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import graphql.schema.DataFetcher;

@Component
public class GraphQLDataFetchers {
	private UserDao userDao;
	
	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public DataFetcher<List<Post>> getRecentPostsFetcher() {
		return dataFetchingEnvironment -> {
			// Get the page and size arguments
			int size = dataFetchingEnvironment.getArgument("size");
			int page = dataFetchingEnvironment.getArgument("page");
			
			// Fetch the recent posts from the blog service
			return WebClient.create("http://reactive-blog-service.herokuapp.com/blog?sortOrder=DESC")
			.get()
			.accept(MediaType.TEXT_EVENT_STREAM)
			.retrieve()
			.bodyToFlux(Post.class)
			.skip(size*page)
			.limitRequest(size)
			.collectList().block();
        };
	}
	
	public DataFetcher<User> getUserByEmailDataFetcher() {
		return dataFetchingEnvironment -> {
			Post post = dataFetchingEnvironment.getSource();
            User user = post.getUser();
            // Get the User from the Database by the email found in the Post
			return this.userDao.findById(user.getEmail()).orElse(null);
		};
	}

	public DataFetcher<User> createUser() {
		return dataFetchingEnvironment -> {
			// Get InputUser from the mutation
			Map<String, Object> input = dataFetchingEnvironment.getArgument("input");
			
			// Get all of the arguments from the InputUser
			String email = input.get("email").toString();
			String password = input.get("password").toString();
			String birthdate = input.get("birthdate").toString();
			String first = input.get("first").toString();
			String last = input.get("last").toString();
			Set<String> roles = new HashSet(((ArrayList)input.get("roles")));
			
			// Create a new User Entity
			User entity = new User(email, new Name(first, last), birthdate, password, roles);
			
			// Save and return the new saved User
			return this.userDao.save(entity);
        };
	}

}
