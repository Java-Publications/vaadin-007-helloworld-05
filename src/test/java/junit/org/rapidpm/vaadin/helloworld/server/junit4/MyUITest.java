package junit.org.rapidpm.vaadin.helloworld.server.junit4;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import junit.org.rapidpm.vaadin.helloworld.server.junit4.base.BaseSeleniumTest;

/**
 *
 */
public class MyUITest extends BaseSeleniumTest {

  @Test
  public void test001() throws Exception {
    final WebDriver webDriver = driver
        .orElseThrow(() -> new RuntimeException("WebDriver not available"));

    webDriver.get("http://127.0.0.1:8080/");

    inputA.get().sendKeys("5");
    inputB.get().sendKeys("5");

    final WebElement btn = button.get();
    btn.click();
    String value = output.get().getAttribute("value");
    Assert.assertEquals("10", value);
  }
}
