package lib.ui;


import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;


abstract public class NavigationUI extends MainPageObject
{
    protected static String
    MY_LIST_BUTTON,
    OPEN_NAVIGATION_MENU;


    public NavigationUI (RemoteWebDriver driver)
    {
        super(driver);
    }


    public void openNavigationMenu()
    {
        if (Platform.getInstance().isMw())
        {
            this.waitForElementAndClick(OPEN_NAVIGATION_MENU, "Cannot open navigation menu");
        }
        else
        {
            System.out.println("Method openNavigationMenu() does nothing for platform " + Platform.getInstance().getPlatformVar());
        }
    }


    public void openMyList()
    {
        if (Platform.getInstance().isMw())
        {
            this.tryClickElementWithFewAttempts(MY_LIST_BUTTON, "Cannot find the 'My lists' button", 5);
        }
        else
        {
            this.waitForElementAndClick(
                    MY_LIST_BUTTON,
                    "Cannot find the 'My lists' button"
            );
        }

    }

}
