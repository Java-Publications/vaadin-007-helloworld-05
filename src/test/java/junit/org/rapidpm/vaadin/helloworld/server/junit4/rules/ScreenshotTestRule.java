package junit.org.rapidpm.vaadin.helloworld.server.junit4.rules;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Optional;

import static java.lang.System.out;

/**
 *
 */
public class ScreenshotTestRule implements TestRule {

  public static final String PATHNAME = "target/surefire-reports/";
  protected Optional<WebDriver> driverOptional = Optional.empty();

  public void setDriverOptional(Optional<WebDriver> driverOptional) {
    this.driverOptional = driverOptional;
  }

  public Statement apply(Statement statement, Description description) {

    return new Statement() {
      @Override
      public void evaluate() throws Throwable {
        try {
          statement.evaluate();
        } catch (Throwable t) {
          out.println("ScreenshotTestRule.evaluate -> catch !! ");
          out.flush();
          String methodName = description.getMethodName();
          captureScreenshot(methodName);
          throw t; // rethrow to allow the failure to be reported to JUnit
        }
      }

      public void captureScreenshot(String testname) {
        if (!driverOptional.isPresent())
          out.println("no WebDriver available for Screenshots " + testname);


        driverOptional.ifPresent(driver -> {
          try {
            new File(PATHNAME).mkdirs(); // Insure directory is there
            FileOutputStream out = new FileOutputStream(PATHNAME + "failed_screenshot-" + testname + ".png");
            out.write(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES));
            out.flush();
            out.close();
          } catch (Exception e) {
            // No need to crash the tests if the screenshot fails
            out.println("e = " + e);
          }
        });
      }
    };
  }
}
