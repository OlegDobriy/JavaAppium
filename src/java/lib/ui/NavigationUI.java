package lib.ui;

import io.appium.java_client.AppiumDriver;


abstract public class NavigationUI extends MainPageObject
{
    protected static String
    MY_LIST_BUTTON;


    public NavigationUI (AppiumDriver driver)
    {
        super(driver);
    }

    public void openMyList()
    {
        this.waitForElementAndClick(
                MY_LIST_BUTTON,
                "Cannot find the 'My lists' button"
        );
    }

}
