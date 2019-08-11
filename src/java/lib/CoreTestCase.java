package lib;

import io.appium.java_client.AppiumDriver;
import junit.framework.TestCase;
import lib.ui.WelcomePageObject;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.RemoteWebDriver;


public class CoreTestCase extends TestCase
{
    protected RemoteWebDriver driver;


    @Override
    protected void setUp() throws Exception
    {
        super.setUp(); // использовать setUp из TestCase
        driver = Platform.getInstance().getDriver();
        this.skipWelcomePageForIOSApp();
        this.openWikiWebPageForMobileWeb( );

    }


    @Override
    protected void tearDown() throws Exception
    {
        driver.quit();

        super.tearDown(); // использовать tearDown из TestCase
    }


    protected void rotateToPortrait()
    {
        if (driver instanceof AppiumDriver)
        {
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.rotate(ScreenOrientation.PORTRAIT);
        }
        else
        {
            System.out.println("Method rotateToPortrait() does nothing to platform " + Platform.getInstance().getPlatformVar());
        }
    }


    protected void rotateToLandscape()
    {
        if (driver instanceof AppiumDriver)
        {
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.rotate(ScreenOrientation.LANDSCAPE);
        }
        else
        {
            System.out.println("Method rotateToLandscape() does nothing to platform " + Platform.getInstance().getPlatformVar());
        }
    }


    protected void backgroundApp(int seconds)
    {
        if (driver instanceof AppiumDriver)
        {
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.runAppInBackground(seconds);
        }
        else
        {
            System.out.println("Method backgroundApp() does nothing to platform " + Platform.getInstance().getPlatformVar());
        }
    }


    protected void openWikiWebPageForMobileWeb()
    {
        if (Platform.getInstance().isMw())
        {
            driver.get("https://en.m.wikipedia.org");
        }
        else
        {
            System.out.println("Method openWikiWebPageForMobileWeb() does nothing to platform " + Platform.getInstance().getPlatformVar());
        }
    }


    private void skipWelcomePageForIOSApp()
    {
        if (Platform.getInstance().isIOS())
        {
            AppiumDriver driver = (AppiumDriver) this.driver;
            WelcomePageObject WelcomePageObject = new WelcomePageObject(driver);
            WelcomePageObject.clickSkip();
        }
    }
}
