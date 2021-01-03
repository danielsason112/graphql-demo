package il.ac.afeka.cloud;

import java.util.Map;

public class Post {
	private User user;
	private Product product;
	private String postingTimestamp;
	private String language;
	private Map<String, Object> postContent;
	
	public Post(User user, Product product, String postingTimestamp, String language, Map<String, Object> postContent) {
		this.user = user;
		this.product = product;
		this.postingTimestamp = postingTimestamp;
		this.language = language;
		this.postContent = postContent;
	}

	public Post() {
		super();
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getPostingTimestamp() {
		return postingTimestamp;
	}

	public void setPostingTimestamp(String postingTimestamp) {
		this.postingTimestamp = postingTimestamp;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public Map<String, Object> getPostContent() {
		return postContent;
	}

	public void setPostContent(Map<String, Object> postContent) {
		this.postContent = postContent;
	}

	@Override
	public String toString() {
		return "Post [user=" + user + ", product=" + product + ", postingTimestamp=" + postingTimestamp
				+ ", language=" + language + ", postContent=" + postContent + "]";
	}
	
	

}
