package lib.ui.ios;

import lib.ui.NavigationUI;
import org.openqa.selenium.remote.RemoteWebDriver;

public class IOSNavigationUI extends NavigationUI
{
    static
    {
        MY_LIST_BUTTON = "id:Saved";
    }


    public IOSNavigationUI(RemoteWebDriver driver)
    {
        super(driver);
    }
}
