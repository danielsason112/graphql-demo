package il.ac.afeka.cloud.resolvers;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import il.ac.afeka.cloud.data.Post;

@Component
public class RecentPostsDataFetcher implements DataFetcher<List<Post>> {
	
	public RecentPostsDataFetcher() {
		
	}

	@Override
	public List<Post> get(DataFetchingEnvironment environment) throws Exception {
		int size = environment.getArgument("size");
		int page = environment.getArgument("page");
		
		// Fetch the recent posts from the blog service
		return WebClient.create("http://reactive-blog-service.herokuapp.com/blog?sortOrder=DESC")
		.get()
		.accept(MediaType.TEXT_EVENT_STREAM)
		.retrieve()
		.bodyToFlux(Post.class)
		.skip(size*page)
		.limitRequest(size)
		.collectList().block();
	}

}
