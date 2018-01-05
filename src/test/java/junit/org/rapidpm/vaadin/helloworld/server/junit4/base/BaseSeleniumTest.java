package junit.org.rapidpm.vaadin.helloworld.server.junit4.base;

import static java.lang.System.out;
import static java.util.Optional.ofNullable;
import static org.openqa.selenium.By.id;
import static org.rapidpm.vaadin.helloworld.server.MyUI.BUTTON_ID;
import static org.rapidpm.vaadin.helloworld.server.MyUI.INPUT_ID_A;
import static org.rapidpm.vaadin.helloworld.server.MyUI.INPUT_ID_B;
import static org.rapidpm.vaadin.helloworld.server.MyUI.OUTPUT_ID;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import junit.org.rapidpm.vaadin.helloworld.server.junit4.rules.ScreenshotTestRule;

/**
 *
 */
public class BaseSeleniumTest extends BaseTest {

  protected Optional<WebDriver> driver;

  @Rule
  public ScreenshotTestRule screenshotTestRule = new ScreenshotTestRule();

  @Override
  @Before
  public void setUp() throws Exception {
    super.setUp();
    out.println("BaseSeleniumTest.setUp !! ");
    out.flush();
    System.setProperty("webdriver.chrome.driver", "_data/chromedriver");
    final ChromeOptions chromeOptions = new ChromeOptions();
    chromeOptions.setHeadless(false);
    driver = Optional.of(new ChromeDriver(chromeOptions));
    screenshotTestRule.setDriverOptional(driver);
  }


  @Override
  @After
  public void tearDown() throws Exception {
    out.println("BaseSeleniumTest.tearDown !! ");
    out.flush();
    driver.ifPresent(d -> {
      d.close();
      d.quit();
    });
    driver = Optional.empty();
    super.tearDown();
  }


  //generic version - need it later
  private BiFunction<WebDriver, String, Optional<WebElement>> elementFor
      = (driver, id) -> ofNullable(driver.findElement(id(id)));

  //localized version
  private Function<String, WebElement> element
      = (id) -> driver
      .flatMap(d -> elementFor.apply(d, id))
      .orElseThrow(()-> new RuntimeException("WebElement with the ID "
                                             + id +" is not available"));

  protected Supplier<WebElement> button = () -> element.apply(BUTTON_ID);
  protected Supplier<WebElement> output = () -> element.apply(OUTPUT_ID);
  protected Supplier<WebElement> inputA = () -> element.apply(INPUT_ID_A);
  protected Supplier<WebElement> inputB = () -> element.apply(INPUT_ID_B);
}
