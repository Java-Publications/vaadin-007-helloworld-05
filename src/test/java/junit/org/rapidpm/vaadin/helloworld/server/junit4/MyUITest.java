package junit.org.rapidpm.vaadin.helloworld.server.junit4;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import junit.org.rapidpm.vaadin.helloworld.server.junit4.base.BaseSeleniumTest;

import static java.lang.System.out;

/**
 *
 */
public class MyUITest extends BaseSeleniumTest {

  @Test
  public void test001() throws Exception {
    out.println(" start test = test001" );
    out.flush();
    final WebDriver webDriver = driver
        .orElseThrow(() -> new RuntimeException("WebDriver not available"));

    webDriver.get("http://127.0.0.1:8080/");

    inputA.get().sendKeys("5");
    inputB.get().sendKeys("5");

    button.get().click();
    String value = output.get().getAttribute("value");

//    try{
      Assert.assertEquals("10", value);
//    } catch (Exception e){
      //takeScreenshot();
//    }

    //will never be reached if test failed
    out.println(" stop test = test001" );
    out.flush();
  }
}
