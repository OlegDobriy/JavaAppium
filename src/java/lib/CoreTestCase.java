package lib;

import io.appium.java_client.AppiumDriver;
import junit.framework.TestCase;
import lib.ui.WelcomePageObject;
import org.openqa.selenium.ScreenOrientation;


public class CoreTestCase extends TestCase
{
    protected AppiumDriver driver;


    @Override
    protected void setUp() throws Exception
    {
        super.setUp(); // использовать setUp из TestCase
        driver = Platform.getInstance().getDriver();
        this.skipWelcomePageForIOSApp();

    }


    @Override
    protected void tearDown() throws Exception
    {
        driver.quit();

        super.tearDown(); // использовать tearDown из TestCase
    }


    protected void rotateToPortrait()
    {
        driver.rotate(ScreenOrientation.PORTRAIT);
    }


    protected void rotateToLandscape()
    {
        driver.rotate(ScreenOrientation.LANDSCAPE);
    }


    protected void backgroundApp(int seconds)
    {
        driver.runAppInBackground(seconds);
    }


    private void skipWelcomePageForIOSApp()
    {
        if (Platform.getInstance().isIOS())
        {
            WelcomePageObject WelcomePageObject = new WelcomePageObject(driver);
            WelcomePageObject.clickSkip();
        }
    }
}
