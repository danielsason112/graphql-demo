package il.ac.afeka.cloud;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import il.ac.afeka.cloud.data.Post;
import il.ac.afeka.cloud.data.Product;
import il.ac.afeka.cloud.data.User;
import il.ac.afeka.cloud.data.UserDao;

@Component
public class CreatePostDataFetcher implements DataFetcher<Post> {
	private UserDao userDao;
	
	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public Post get(DataFetchingEnvironment environment) throws Exception {
		Map<String, Object> input = environment.getArgument("input");
		String email = input.get("userEmail").toString();
		String productId = input.get("productId").toString();
		String language = input.get("language").toString();
		Map<String, Object> postContent = (HashMap<String, Object>)input.get("postContent");
		
		if (!this.userDao.existsById(email)) {
			throw new RuntimeException("Can not create post because user does not exists.");
		}
		
		Post post = new Post();
		post.setLanguage(language);
		post.setPostContent(postContent);
		post.setProduct(new Product(productId));
		post.setUser(new User(email));
		
		return WebClient.create("http://reactive-blog-service.herokuapp.com/blog")
				.post()
				.accept(MediaType.APPLICATION_JSON)
				.bodyValue(post)
				.retrieve()
				.bodyToMono(Post.class)
				.block();
	}

}
