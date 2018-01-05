package junit.org.rapidpm.vaadin.helloworld.server.junit4.base;

import org.junit.After;
import org.junit.Before;
import org.rapidpm.vaadin.helloworld.server.Main;

import static java.lang.System.out;

/**
 *
 */
public abstract class BaseTest {

  @Before
  public void setUp() throws Exception {
    out.println("BaseTest.setUp !! ");
    out.flush();
    Main.start();
  }

  @After
  public void tearDown() throws Exception {
    out.println("BaseTest.tearDown !! ");
    out.flush();
    Main.shutdown();
  }


}
