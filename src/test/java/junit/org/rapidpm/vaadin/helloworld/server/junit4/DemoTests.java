package junit.org.rapidpm.vaadin.helloworld.server.junit4;

import org.junit.*;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import static java.lang.System.out;
import static org.junit.Assert.fail;

/**
 *
 */
public class DemoTests {

  @BeforeClass
  public static void beforeclass() { out.println("beforeclass"); }

  @AfterClass
  public static void afterclass() { out.println("afterclass"); }

  @Before
  public void setUp() { out.println("setUp"); }

  //@After
  public void tearDown() { out.println("tearDown - orig"); }


  @Test
  public void test001() { out.println("test001 - non failing"); }

  @Test
  public void test002() {
    out.println("test002 - failing");
    fail();
  }

  @Rule
  public TakeScreenShotRule takeScreenShotRule = new TakeScreenShotRule();

  public static class TakeScreenShotRule implements TestRule {
    public Statement apply(Statement statement, Description description) {
      return new Statement() {
        @Override
        public void evaluate() throws Throwable {
          try {
            statement.evaluate();
          } catch (Error e) {
            out.println("screenshot now....");
            throw e;
          } finally {
            tearDownManually();
            out.println("###############");
          }
        }
        //Put your tear down code in this method!
        public void tearDownManually() {
          out.print("tearDownManually - now delegating - ");
          out.println("release resources from baseStatement");
        }
      };
    }
  }
}
