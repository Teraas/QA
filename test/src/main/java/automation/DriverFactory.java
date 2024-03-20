package automation;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chromium.ChromiumDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

/**
 * @author harish.kumar-mbp
 * createdOn 14/02/24
 */
public class DriverFactory {

    public WebDriver createInstance(String browser) {
        //Target target = Target.valueOf(configuration().target().toUpperCase());
        String target = System.getProperty("browser", "Chrome");
        WebDriver webdriver = new ChromeDriver();

        switch (target) {
            case "LOCAL":
                //webdriver = BrowserFactory.valueOf(browser.toUpperCase()).createDriver();
                break;
            case "REMOTE":
                //webdriver = createRemoteInstance(BrowserFactory.valueOf(browser.toUpperCase()).getOptions());
                break;
            default:
                //throw new TargetNotValidException(target.toString());
        }
        return webdriver;
    }

    private RemoteWebDriver createRemoteInstance(MutableCapabilities capability) {
        RemoteWebDriver remoteWebDriver = null;
        try {
            String gridURL = String.format("http://%s:%s", "configuration().gridUrl()", "configuration().gridPort()");

            remoteWebDriver = new RemoteWebDriver(new URL(gridURL), capability);
        } catch (java.net.MalformedURLException e) {
            //logger.log(Level.SEVERE, "Grid URL is invalid or Grid is not available");
            //logger.log(Level.SEVERE, String.format("Browser: %s", capability.getBrowserName()), e);
        } catch (IllegalArgumentException e) {
            //logger.log(Level.SEVERE, String.format("Browser %s is not valid or recognized", capability.getBrowserName()), e);
        }

        return remoteWebDriver;
    }
}
