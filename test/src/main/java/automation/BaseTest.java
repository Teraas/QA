package automation;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;


/**
 * @author harish.kumar-mbp
 * createdOn 14/02/24
 */
public class BaseTest {
    static
    ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<>();

    protected WebDriver driver;
    //protected Configuration configuration;

    public static WebDriver getDriver(){
        return threadLocalDriver.get();
    }

    @BeforeEach
    public void preCondition() {
        //configuration = configuration();

        driver = new DriverFactory().createInstance("chrome");
        //driver.get(configuration().url());
    }

    @AfterEach
    public void postCondition() {
        driver.quit();
        threadLocalDriver.remove();
    }
}
