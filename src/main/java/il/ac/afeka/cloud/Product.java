package il.ac.afeka.cloud;

public class Product {
	private String id;

	public Product(String id) {
		super();
		this.id = id;
	}

	public Product() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + "]";
	}
	
	
}
