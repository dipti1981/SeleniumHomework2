package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.thread.IThreadWorkerFactory;

import java.sql.Timestamp;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class TestSuite {

    protected static WebDriver driver;


    public static void clickTheElement(By by) {
        driver.findElement(by).click();
    }

    public static void typeText(By by, String text) {
        driver.findElement(by).sendKeys(text);
    }

    public static String getTextFromElement(By by) {
        return driver.findElement(by).getText();
    }

    public static long timestamp() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return timestamp.getTime();
    }

    @BeforeMethod
    public static void openBrowser() {
        //open Chrome browser
        driver = new ChromeDriver();
        //open URl
        driver.get("https://demo.nopcommerce.com/");
        //maximize the window
        driver.manage().window().maximize();
        //implying implicit wait
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.MILLISECONDS);

    }
    @AfterMethod
    public static void closeBrowser(){
        //close the driver
        driver.close();
      }

    @Test
    public static void verifyUserShouldBeAbleToRegisterSuccessfully() {
        String expectedRegistrationCompleteMsg = "Registration completed";
        //Click on register
        clickTheElement(By.className("ico-register"));
        //Type first name
        typeText(By.id("FirstName"), "First name");
        //Type lastname
        typeText(By.id("LastName"), "Last name");
        //Type email address
        typeText(By.name("Email"), "testmail" + timestamp() + "@gmail.com");
        //Type password
        typeText(By.id("Password"), "test1234");
        //Type confirm password
        typeText(By.id("ConfirmPassword"), "test1234");
        //Click on register submit button
        clickTheElement(By.id("register-button"));
        //Capture the actual message
        String actualMessage = getTextFromElement(By.xpath("//div[@class =\"result\"]"));
        System.out.println("Actual message is: " + actualMessage);
        //Assert to verify the actual message and expected message
        Assert.assertEquals(actualMessage, expectedRegistrationCompleteMsg, "your registration is not working");

            }

    @Test
    public static void verifyUserShouldBeAbleToVoteSuccessfully() {
        String expectedMessageCommunityPole = "Please register to vote";
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        //Click on good button
        clickTheElement(By.id("pollanswers-2"));
        //Click on vote button
        clickTheElement(By.id("vote-poll-1"));
        //Explicit wait to capture disappearing text
        wait.until(ExpectedConditions.elementToBeClickable(By.id("block-poll-vote-error-1")));
        //Get error message Text
        String actualMessageForCommunityPoll = getTextFromElement(By.id("block-poll-vote-error-1"));
        System.out.println("Message is: " + actualMessageForCommunityPoll);
        //Assert to verify if the actual message is same as expected message
        Assert.assertEquals(actualMessageForCommunityPoll, expectedMessageCommunityPole, "Please register to vote");

    }

    @Test
    public static void verifyUserShouldBeAbleToEmailAFriendSuccessfully() {
        String expectedErrorMessage = " registered customers can use email a friend feature";
        //Click on add to cart on Apple Macbook pro13-inch
        clickTheElement(By.xpath("//div[@class=\"product-grid home-page-product-grid\"]/div[2]/div[2]/div[1]/div[2]/div[3]/div[2]/button[1]"));
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(By.className("email-a-friend")));
        //Click on Email friend
        clickTheElement((By.className("email-a-friend")));
        //Type friend's Email id
        typeText(By.className("friend-email"), "Abc123@gmail.com");
        //Type your Email id
        typeText(By.id("YourEmailAddress"), "test@gmail.com");
        //Type personal message
        typeText(By.id("PersonalMessage"), "sample message");
        //Click on send Email button
        clickTheElement(By.name("send-email"));
        //Capture error message
        String actualErrorMessage = getTextFromElement(By.xpath("//div[@class=\"message-error validation-summary-errors\"]"));
        System.out.println("Error Message is: " + actualErrorMessage);
        //Assert to verify the actual message and expected message
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage, "Please register to use Email a friend feature");

    }

    @Test
    public static void VerifyUserCanSeeTheCorrectProductInTheShoppingCart() {
        //Click on Electronic button
        clickTheElement(By.xpath("//ul[@class = \"top-menu notmobile\"]/li[2]"));
        //Click on Camera and Photo button
        clickTheElement(By.xpath("//div[@class=\"item-grid\"]/div[1]/div[1]/h2/a"));
        //capture the name of the product
        String productname1 = getTextFromElement(By.xpath("//div[@class = \"item-grid\"]/div[3]/div[1]/div[2]/h2"));
        System.out.println("Product name before add to cart is:" + productname1);
        //click on add to cart
        clickTheElement(By.xpath("//div[@class = \"item-grid\"]/div[3]/div[1]/div[2]/div[3]/div[2]/button[1]"));
        //click on shopping cart
        clickTheElement(By.className("cart-label"));
        //capture the name of the product
        String productname2 = getTextFromElement(By.className("product-name"));
        System.out.println("Product name after add to cart is: " + productname2);
        //Assert to verify the actual message and expected message
        Assert.assertEquals(productname1, productname2, "Both the products are same");

    }

    @Test
    public static void VerifyRegisteredUserShouldAbleToReferAProductToAFriendSuccessfully() {
        String expectedMessage = "Message is sent";
        clickTheElement(By.className("ico-register"));
        //Type first name
        typeText(By.id("FirstName"), "First name");
        //Type lastname
        typeText(By.id("LastName"), "Last name");
        //Type email address
        typeText(By.name("Email"), "testmail@gmail.com");
        //Type password
        typeText(By.id("Password"), "test1234");
        //Type confirm password
        typeText(By.id("ConfirmPassword"), "test1234");
        //Click on register submit button
        clickTheElement(By.id("register-button"));
        //Click on login button
        clickTheElement(By.className("ico-login"));
        //Type email
        typeText(By.id("Email"), "testmail@gmail.com");
        //Type password
        typeText(By.id("Password"), "test1234");
        //Click on login button
        clickTheElement(By.xpath("//div[@class=\"returning-wrapper fieldset\"]/form/div[3]/button"));
        //Click Add to cart one product
        clickTheElement(By.xpath("//div[@class=\"product-grid home-page-product-grid\"]/div[2]/div[2]/div[1]/div[2]/div[3]/div[2]/button[1]"));
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(1));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class=\"email-a-friend\"]/button")));
        //Click on email a friend
        clickTheElement(By.xpath("//div[@class=\"email-a-friend\"]/button"));
        //Type friends email
        typeText(By.id("FriendEmail"), "abc@gmail.com");
        //Type personal message
        typeText(By.id("PersonalMessage"), "good product");
        //Click on send email
        clickTheElement(By.xpath("//div[@class=\"buttons\"]/button"));
        //Capture the message
        String actualMessage = getTextFromElement(By.xpath("//div[@class=\"page-body\"]/div[2]"));
        System.out.println("Display message is: " + actualMessage);
        //Capture the product name
        String productname = getTextFromElement(By.xpath("//a[@href = \"/apple-macbook-pro-13-inch\"]"));
        System.out.println("Product name is: " + productname);
        //Assert to verify the actual message and expected message
        Assert.assertEquals(actualMessage, expectedMessage, "Display message is not same");
    }

    @Test
    public static void VerifyRegisteredUserShouldBeAbleToVotSuccessfully() {
        String expectedMsg = "8 vote(t)";
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        //Click on register
        clickTheElement(By.className("ico-register"));
        //Type first name
        typeText(By.id("FirstName"), "First name");
        //Type lastname
        typeText(By.id("LastName"), "Last name");
        //Type email address
        typeText(By.name("Email"), "testmail12@gmail.com");
        //Type password
        typeText(By.id("Password"), "test12345");
        //Type confirm password
        typeText(By.id("ConfirmPassword"), "test12345");
        //Click on register submit button
        clickTheElement(By.id("register-button"));
        wait.until(ExpectedConditions.elementToBeClickable(By.className("ico-login")));
        //Click on login button
        clickTheElement(By.className("ico-login"));
        //Type email address
        typeText(By.id("Email"), "testmail12@gmail.com");
        //Type password
        typeText(By.id("Password"), "test12345");
        //Click on login button
        clickTheElement(By.xpath("//button[@class=\"button-1 login-button\"]"));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//ul[@class=\"poll-options\"]/li[2]")));
        //Click on good button
        clickTheElement(By.xpath("//ul[@class=\"poll-options\"]/li[2]"));
        //Click on vote button
        clickTheElement(By.id("vote-poll-1"));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class = \"home-page-polls\"]/div[2]/ul/li[1]")));
        String excellentVote = getTextFromElement(By.xpath("//div[@class = \"home-page-polls\"]/div[2]/ul/li[1]"));
        System.out.println("Excellent vote: " + excellentVote);
        String goodVote = getTextFromElement(By.xpath("//div[@class = \"home-page-polls\"]/div[2]/ul/li[2]"));
        System.out.println("Good vote: " + goodVote);
        String poorVote = getTextFromElement(By.xpath("//div[@class = \"home-page-polls\"]/div[2]/ul/li[3]"));
        System.out.println("Poor vote: " + poorVote);
        String varyBadVote = getTextFromElement(By.xpath("//div[@class = \"home-page-polls\"]/div[2]/ul/li[4]"));
        System.out.println("Very bad vote: "+ varyBadVote);
        String totalVotes = getTextFromElement(By.className("poll-total-votes"));
        System.out.println("Total votes are: " + totalVotes);
        //Assert to verify the actual message and expected message
        Assert.assertEquals(totalVotes, expectedMsg, "Thanks for voting");

    }
    @Test
    public static void VerifyUserShouldBeAbleToCompareTwoProductSuccessfully() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        String expectedDisplayMsg = "have no items to compare.";
        //Click on HTC One M* Android L %.0 Lollipop
        clickTheElement(By.xpath("//img[@alt = \"Picture of HTC One M8 Android L 5.0 Lollipop\"]"));
        //Click on add to compare list button
        clickTheElement(By.xpath("//div[@class=\"overview-buttons\"]/div[2]/button"));
        //Click on product compare
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class=\"bar-notification success\"]/p/a")));
        clickTheElement(By.xpath("//div[@class=\"bar-notification success\"]/p/a"));
        //Click on homepage
        clickTheElement(By.className("header-logo"));
        //Click on £25 virtual gift card
        clickTheElement(By.xpath("//img[@alt = \"Picture of $25 Virtual Gift Card\"]"));
        //Click on add to compare list button
        clickTheElement(By.xpath("//div[@class=\"overview-buttons\"]/div[2]/button"));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class=\"bar-notification success\"]/p/a")));
        //Click on product compare
        clickTheElement(By.xpath("//div[@class=\"bar-notification success\"]/p/a"));
        //Capture first product name
        String productName1 = getTextFromElement(By.xpath("//table[@class = \"compare-products-table\"]/tbody/tr[3]/td[3]"));
        System.out.println("Poroduct name 1 is : " + productName1);
        //Capture second product name
        String productName2 = getTextFromElement(By.xpath("//table[@class = \"compare-products-table\"]/tbody/tr[3]/td[2]"));
        System.out.println("Product name 2 is: " + productName2);
        //Click on clear list
        clickTheElement(By.xpath("//div[@class=\"page-body\"]/a"));
        //capture the message
        String actualMsg = getTextFromElement(By.className("no-data"));
        System.out.println("Display message is: " + actualMsg);
        Assert.assertEquals(actualMsg, expectedDisplayMsg, "Display message is wrong");


    }
}

