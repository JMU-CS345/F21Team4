import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.net.URL;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CustomImageTest
{
  @BeforeEach
  void beforeEach() throws IOException
  {
    CustomImage image = new CustomImage(500, 500);

    CustomImage coloredImage = new CustomImage(500, 500, 255, 255, 255);
  }

  @Test
  void test()
  {
    fail("Not yet implemented");
  }

}
