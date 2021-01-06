package il.ac.afeka.cloud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import il.ac.afeka.cloud.resolvers.CreatePostDataFetcher;
import il.ac.afeka.cloud.resolvers.CreateUserDataFetcher;
import il.ac.afeka.cloud.resolvers.RecentPostsDataFetcher;
import il.ac.afeka.cloud.resolvers.UserByEmailDataFetcher;
import il.ac.afeka.cloud.resolvers.UserDataFetcher;

@Component
public class GraphQLDataFetchers {
	private RecentPostsDataFetcher recentPostsDataFetcher;
	private UserByEmailDataFetcher userByEmailDataFetcher;
	private CreateUserDataFetcher createUserDataFetcher;
	private UserDataFetcher userDataFetcher;
	private CreatePostDataFetcher createPostDataFetcher;
	
	@Autowired
	public GraphQLDataFetchers(RecentPostsDataFetcher recentPostsDataFetcher,
			UserByEmailDataFetcher userByEmailDataFetcher, CreateUserDataFetcher createUserDataFetcher,
			UserDataFetcher userDataFetcher, CreatePostDataFetcher createPostDataFetcher) {
		this.recentPostsDataFetcher = recentPostsDataFetcher;
		this.userByEmailDataFetcher = userByEmailDataFetcher;
		this.createUserDataFetcher = createUserDataFetcher;
		this.userDataFetcher = userDataFetcher;
		this.createPostDataFetcher = createPostDataFetcher;
	}

	public RecentPostsDataFetcher getRecentPostsDataFetcher() {
		return recentPostsDataFetcher;
	}

	public UserByEmailDataFetcher getUserByEmailDataFetcher() {
		return userByEmailDataFetcher;
	}

	public CreateUserDataFetcher getCreateUserDataFetcher() {
		return createUserDataFetcher;
	}

	public UserDataFetcher getUserDataFetcher() {
		return userDataFetcher;
	}

	public CreatePostDataFetcher getCreatePostDataFetcher() {
		return this.createPostDataFetcher;
	}

}
