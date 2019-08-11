package lib.ui;

import org.openqa.selenium.remote.RemoteWebDriver;

public class AuthorizationPageObject extends MainPageObject
{
    private static final String
        LOGIN_BUTTON = "css:a.mw-ui-icon-minerva-login",
        LOGIN_INPUT= "css:#wpName1",
        PASSWORD_INPUT= "css:#wpPassword1",
        SUBMIT_BUTTON= "css:#wpLoginAttempt";


    public AuthorizationPageObject(RemoteWebDriver driver)
    {
        super(driver);
    }


    public void clickAuthButton() throws InterruptedException
    {
        this.checkElementIsMoving(LOGIN_BUTTON);
        this.waitForElementPresent(LOGIN_BUTTON, "Cannot find 'Log in' button");
        this.waitForElementAndClick(LOGIN_BUTTON, "Cannot click 'Log in' button");
    }


    public void enterLoginData(String login, String password)
    {
        this.waitForElementAndSendKeys(LOGIN_INPUT, login, "Cannot send value to 'Login' field");
        this.waitForElementAndSendKeys(PASSWORD_INPUT, password, "Cannot send value to 'Password' field");
    }


    public void submitForm()
    {
        this.waitForElementAndClick(SUBMIT_BUTTON, "Cannot find 'Submit auth' button");
    }
}
