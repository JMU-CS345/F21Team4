import java.net.URL;

/**
 * 
 * @author zacktucker
 *
 */
public class Dog {
	private final String name;
	private final URL url;
	private final String height;
	private final String weight;
	private final String origin;
	private final String lifespan;

	public Dog(String name, URL url, String height, String weight, String origin, String lifespan) {

		this.name = name;
		this.url = url;
		this.height = height;
		this.weight = weight;
		this.origin = origin;
		this.lifespan = lifespan;

	}

	public String getName() {
		return this.name;
	}

	public URL getURL() {
		return this.url;
	}

	public String getHeight() {
		return this.height;
	}

	public String getWeight() {
		return this.weight;
	}

	public String getOrigin() {
		return this.origin;
	}

	public String getLifespan() {
		return this.lifespan;
	}

}
