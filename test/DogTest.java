import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.net.URL;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * A class to test the functionality of the Dog class.
 * 
 * @author Zack Tucker, Thomas Mandell, Matt Wong, Johnathan Wist, Alex Polivka
 *
 */
public class DogTest {
	Dog dogObj;

	@BeforeEach
	void beforeEach() throws IOException {
		dogObj = new Dog("Test", new URL("https://api.thedogapi.com/v1/breeds"), "50", "100", "ISAT Building", "1", "2");

	}

	@Test
	void testName() throws Exception {
		assertEquals("Test", dogObj.getName());
	}

	@Test
	void testURL() throws Exception {
		assertEquals("https://api.thedogapi.com/v1/breeds", dogObj.getURL().toString());
	}

	@Test
	void testWeight() throws Exception {
		assertEquals("100", dogObj.getWeight());
	}

	@Test
	void testHeight() throws Exception {
		assertEquals("50", dogObj.getHeight());

	}

	@Test
	void testOrigin() throws Exception {
		assertEquals("ISAT Building", dogObj.getOrigin());

	}

	@Test
	void testLifespan() throws Exception {
		assertEquals("1", dogObj.getLifespan());

	}

}
