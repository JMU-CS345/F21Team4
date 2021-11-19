import java.net.URL;

/**
 * 
 * @author Zack Tucker, Matt Wong, Thomas Mandell, Alex Polivka, Jonathan Wist
 * @version Nov 1, 2021
 *
 */
public class Dog
{
  private final String name;
  private final URL url;
  private final String height;
  private final String weight;
  private final String origin;
  private final String lifespan;
  private final String temperament;

  public Dog(String name, URL url, String height, String weight, String origin, String lifespan,
      String temperament)
  {
    this.name = name;
    this.url = url;
    this.height = height;
    this.weight = weight;
    this.origin = origin;
    this.lifespan = lifespan;
    this.temperament = temperament;

  }

  public String getName()
  {
    return this.name;
  }

  public URL getURL()
  {
    return this.url;
  }

  public String getHeight()
  {
    return this.height;
  }

  public String getWeight()
  {
    return this.weight;
  }

  public String getOrigin()
  {
    return this.origin;
  }

  public String getLifespan()
  {
    return this.lifespan;
  }

  public String getTemperament()
  {
    return this.temperament;
  }

  public String toString()
  {
    return this.name;
  }

}
