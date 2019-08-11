package lib.ui.mobile_web;

import lib.ui.NavigationUI;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWNavigationUI extends NavigationUI
{
    static
    {
        MY_LIST_BUTTON = "css:a.mw-ui-icon-minerva-watchlist";
        OPEN_NAVIGATION_MENU = "css:a.main-menu-button";
    }


    public MWNavigationUI(RemoteWebDriver driver)
    {
        super(driver);
    }
}
