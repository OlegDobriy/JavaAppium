package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import lib.Platform;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class MainPageObject
{
    protected RemoteWebDriver driver;

    public MainPageObject(RemoteWebDriver driver)
    {
        this.driver = driver;
    }

    public WebElement waitForElementPresent(String locator, String error_message, long timeoutInSeconds)
    {
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }


    public WebElement waitForElementPresent(String locator, String errorMessage)
    {
        return waitForElementPresent(locator, errorMessage, 5);
    }


    public WebElement waitForElementAndClick(String locator, String errorMessage, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(locator, errorMessage, timeoutInSeconds);
        element.click();
        return element;
    }


    public WebElement waitForElementAndClick(String locator, String errorMessage)
    {
        return waitForElementAndClick(locator, errorMessage, 5);
    }


    public WebElement waitForElementAndSendKeys(String locator, String value, String errorMessage, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(locator, errorMessage, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }


    public WebElement waitForElementAndSendKeys(String locator, String value, String errorMessage)
    {
        return waitForElementAndSendKeys(locator, value, errorMessage, 5);
    }


    public boolean waitForElementNotPresent(String locator, String errorMessage, long timeoutInSeconds)
    {
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(errorMessage + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }


    public boolean waitForElementNotPresent(String locator, String errorMessage) {
        return waitForElementNotPresent(locator, errorMessage,5);
    }


    public WebElement waitForElementAndClear(String locator, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(locator, error_message, timeoutInSeconds);
        element.clear();
        return element;
    }


    public WebElement waitForElementAndClear (String locator, String errorMessage)
    {
        return waitForElementAndClear(locator, errorMessage, 5);
    }


    public List<WebElement> waitForElementsAndGetList(String locator, String errorMessage, long timeoutInSeconds)
    {
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(errorMessage + "\n");
        return wait.until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(by)
        );
    }

    public List<String> getTextFromElementsInList(List<WebElement> listOfWebElements)
    {
        List<String> resultList = new ArrayList();
        for (WebElement element : listOfWebElements)
        {
            if (lib.Platform.getInstance().isAndroid())
            {
                String elementText = element.getAttribute("text");
                resultList.add(elementText);
            }
            else if (lib.Platform.getInstance().isIOS())
            {
                String elementText = element.getAttribute("name");
                resultList.add(elementText);
            }
            else
            {
                String elementText = element.getAttribute("title");
                resultList.add(elementText);
            }
        }
        return resultList;
    }



    protected void swipeUp (int timeOfSwipe)
    {
        if (driver instanceof AppiumDriver)
        {
            TouchAction action = new TouchAction((AppiumDriver) driver);
            Dimension size = driver.manage().window().getSize(); // получить размеры устройства

            int x = size.width / 2; // середина устройства по оси x
            int y_start = (int) (size.height * 0.8);
            int y_end = (int) (size.height * 0.2);

            action
                    .press(x, y_start)
                    .waitAction(timeOfSwipe)
                    .moveTo(x, y_end)
                    .release()
                    .perform();
        }
        else
        {
            System.out.println("Method swipeUp() does nothing to platform " + Platform.getInstance().getPlatformVar());
        }

    }


    protected void swipeUpQuick ()
    {
        swipeUp(200);
    }


    public void scrollWebPageUp()
    {
        if (Platform.getInstance().isMw())
        {
            JavascriptExecutor JSExecutor = (JavascriptExecutor) driver;
            JSExecutor.executeScript("window.scrollBy(0, 250)");
        }
        else
        {
            System.out.println("Method scrollWebPageUp() does nothing for platform " + Platform.getInstance().getPlatformVar());
        }
    }


    public void scrollWebPageUpTillElementNotVisible(String locator, String errorMessage, int maxSwipes)
    {
        int alreadySwiped = 0;
        WebElement element = this.waitForElementPresent(locator, errorMessage);
        while (!this.isElementLocatedOnTheScreen(locator))
        {
            scrollWebPageUp();
            ++alreadySwiped;
            if (alreadySwiped > maxSwipes)
            {
                Assert.assertTrue(errorMessage, element.isDisplayed());
            }
        }
    }

    public void swipeUpToFindElement(String locator, String errorMessage, int maxSwipes)
    {
        By by = this.getLocatorByString(locator);
        int alreadySwiped = 0;
        while (driver.findElements(by).size() == 0) {

            if (alreadySwiped > maxSwipes) {
                waitForElementPresent(locator, "Cannot find the element by swiping. \n" + errorMessage);
                return;
            }
            swipeUpQuick();
            ++alreadySwiped;
        }
    }


    public void swipeUpTillElementAppears(String locator, String errorMessage, int maxSwipes)
    {
        int alreadySwiped = 0;
        while (!this.isElementLocatedOnTheScreen(locator))
        {
            if(alreadySwiped > maxSwipes)
            {
                Assert.assertTrue(errorMessage, this.isElementLocatedOnTheScreen(locator));
            }
            swipeUpQuick();
            ++alreadySwiped;
        }
    }


    public boolean isElementLocatedOnTheScreen(String locator)
    {
        int elementLocationByY = this.waitForElementPresent(locator, "Cannot find element on the screen: " + locator).getLocation().getY();
        if (Platform.getInstance().isMw())
        {
            JavascriptExecutor JSExecutor = (JavascriptExecutor) driver;
            Object jsResult = JSExecutor.executeScript("return window.pageYOffset");
            elementLocationByY -= Integer.parseInt(jsResult.toString());
        }
        int screenSizeByY = driver.manage().window().getSize().getHeight();
        return elementLocationByY < screenSizeByY;
    }


    public void clickElementToTheRightUpperCorner(String locator, String errorMessage)
    {
        if (driver instanceof AppiumDriver)
        {
            TouchAction action = new TouchAction((AppiumDriver) driver);
            WebElement element = this.waitForElementPresent(locator + "/..", errorMessage); // подняться на уровень выше
            int x_left = element.getLocation().getX();
            int y_upper = element.getLocation().getY();
            int y_lower = y_upper + element.getSize().getHeight();
            int y_middle = (y_upper + y_lower) / 2;
            int width = element.getSize().getWidth();
            int x_pointToClick = (x_left + width) - 3;
            int y_pointToClick = y_middle;
            action.tap(x_pointToClick, y_pointToClick).perform();
        }
        else
        {
            System.out.println("Method clickElementToTheRightUpperCorner() does nothing to platform " + Platform.getInstance().getPlatformVar());
        }
    }


    public void swipeElementToLeft (String locator, String errorMessage)
    {
        if (driver instanceof AppiumDriver)
        {
            TouchAction action = new TouchAction((AppiumDriver) driver);
            WebElement element = waitForElementPresent(locator,
                    errorMessage,
                    5);

            int x_left = element.getLocation().getX(); // координата x эелемента (слева)
            int x_right = x_left + element.getSize().getWidth(); // координата x эелемента (справа)
            int y_top = element.getLocation().getY(); // координата y эелемента (сверху)
            int y_bottom = y_top + element.getSize().getHeight(); // координата y эелемента (снизу)
            int y_middle = (y_top + y_bottom) / 2; // координата y эелемента (середина)

            action.press(x_right, y_middle);
            action.waitAction(300);
            if (Platform.getInstance().isAndroid())
            {
                action.moveTo(x_left, y_middle);
            }
            else
            {
                int x_offset = (-1 * element.getSize().getWidth());
                action.moveTo(x_offset, 0);
            }
            action.release();
            action.perform();
        }
        else
        {
            System.out.println("Method swipeElementToLeft() does nothing to platform " + Platform.getInstance().getPlatformVar());
        }
    }

    public int getAmountOfElements(String locator)
    {
        By by = this.getLocatorByString(locator);
        List elements = driver.findElements(by);
        return elements.size();
    }


    public boolean isElementPresent(String locator)
    {
        return getAmountOfElements(locator) > 0;
    }


    public void assertElementNotPresent(String locator, String errorMessage, int timeoutInSeconds)
    {
        int amountOfElements = getAmountOfElements(locator);
        if (amountOfElements > 0)
        {
            String defaultMessage = "An element '" + locator + "' should not be present.";
            throw new AssertionError(defaultMessage + " " + errorMessage);
        }
    }


    public void tryClickElementWithFewAttempts(String locator, String errorMessage, int amountOfAttempts)
    {
        int currentAttempts = 0;
        boolean needMoreAttempts = true;
        while (needMoreAttempts)
        {
            try
            {
                this.waitForElementAndClick(locator, errorMessage, 1);
                needMoreAttempts = false;
            }
            catch (Exception e)
            {
                if (currentAttempts > amountOfAttempts)
                {
                    this.waitForElementAndClick(locator, errorMessage, 1);
                }
            }
            ++currentAttempts;
        }
    }

    public String waitForElementAndGetAttribute(String locator, String attribute, String errorMessage, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(locator, errorMessage, timeoutInSeconds);
        return element.getAttribute(attribute);
    }

    public boolean assertElementPresent(String locator, String errorMessage)
    {
        By by = this.getLocatorByString(locator);
        try {
            driver.findElement(by);
            return true;
        }

        catch(NoSuchElementException exception) {
            throw new AssertionError("There is no such element. " + errorMessage);
        }
    }

    public void checkElementIsMoving(String locator) throws InterruptedException
    {
        String errorMessage = "Cannot find the element: " + locator.toString();
        System.out.println("Checking if the element is moving: " + locator.toString());
        Point location1 = waitForElementPresent(locator, errorMessage).getLocation();
        Point location2 = waitForElementPresent(locator, errorMessage).getLocation();
        while (!(location1.equals(location2)))
        {
            System.out.println("location1 = " + location1 + "\nlocation2 = " + location2 + "\n---");
            location1 = waitForElementPresent(locator, errorMessage).getLocation();
            Thread.sleep(2);
            location2 = waitForElementPresent(locator, errorMessage).getLocation();
            System.out.println("location1 = " + location1 + "\nlocation2 = " + location2 + "\n---");
        }
    }


    private By getLocatorByString(String locatorWithType)
    {
        String[] stringWithotSeparation = locatorWithType.split(Pattern.quote(":"), 2);
        String stringBy = stringWithotSeparation[0];
        String stringLocator = stringWithotSeparation[1];

        if (stringBy.equals("id"))
        {
            return By.id(stringLocator);
        }
        else if (stringBy.equals("xpath"))
        {
            return By.xpath(stringLocator);
        }
        else if (stringBy.equals("css"))
        {
            return By.cssSelector(stringLocator);
        }
        else
        {
            throw new IllegalArgumentException("Unknown By statement in " + locatorWithType);
        }
    }
}
