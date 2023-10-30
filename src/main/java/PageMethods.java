import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.logging.Logger;

import static org.assertj.core.api.Assertions.assertThat;

public class PageMethods {

    private static final Logger LOGGER = Logger.getLogger(PageMethods.class.getName());

    private static final String WEBSHOP_URL = "https://www.demoblaze.com/index.html";
    private static final String LOGIN = "TestingAccount";
    private static final String PASSWORD = "Test123!";
    private static final String NAME = "Agnieszka";
    private static final String COUNTRY = "Poland";
    private static final String CITY = "Wroclaw";
    private static final String CREDIT_CARD = "1234567890123456789";
    private static final String MONTH = "May";
    private static final String YEAR = "2026";

    private final WebDriver driver;
    private final WebDriverWait wait;

    public PageMethods(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 30);
    }

    public void openWebShopPage() {
        driver.navigate().to(WEBSHOP_URL);

        WebElement pageHeader = driver.findElement(By.id("nava"));
        assertThat(pageHeader.isDisplayed()).isTrue();
        LOGGER.info("Page opens correctly.");
    }

    public void clickLogInButton() {
        driver.findElement(By.id("login2")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("logInModalLabel")));
        LOGGER.info("Log in header is visible. Pop-up to log in is shown correctly.");

        WebElement logInHeaderInPopUp = driver.findElement(By.id("logInModalLabel"));
        assertThat(logInHeaderInPopUp.isDisplayed()).isTrue();
        LOGGER.info("Button has been clicked. Pop-up for credentials appeared.");
    }

    public void inputLogIn() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@onclick='logIn()']")));

        driver.findElement(By.id("loginusername")).sendKeys(LOGIN);
        LOGGER.info("Login has been provided.");

        driver.findElement(By.id("loginpassword")).sendKeys(PASSWORD);
        LOGGER.info("Password has been provided.");

        driver.findElement(By.xpath("//button[@onclick='logIn()']")).click();

        wait.until(ExpectedConditions.not(ExpectedConditions.textToBe(By.id("signin2"), "Sign up")));
        WebElement userName = driver.findElement(By.id("nameofuser"));
        assertThat(userName.isDisplayed()).isTrue();
        LOGGER.info("Button to log in has been clicked. User is logged in to his account.");
    }

    public void clickSamsungGalaxyS6ToSeeDetails() {
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Samsung galaxy s6")));
        WebElement product = driver.findElement(By.linkText("Samsung galaxy s6"));
        assertThat(product.isDisplayed()).isTrue();

        product.click();

        LOGGER.info("User was redirected to page with phone details.");
    }


    public void addProductToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Add to cart")));
        WebElement addToCartButton = driver.findElement(By.linkText("Add to cart"));
        assertThat(addToCartButton.isDisplayed()).isTrue();

        addToCartButton.click();

        LOGGER.info("Samsung Galaxy S6 has been correctly added to cart.");
    }

    public void dismissConfirmationAlert() {
        wait.until(ExpectedConditions.alertIsPresent());
        assertThat(ExpectedConditions.alertIsPresent()).isNotNull();
        LOGGER.info("Confirmation alert appeared correctly.");

        driver.switchTo().alert().dismiss();
        LOGGER.info("Confirmation alert has been closed.");
    }

    public void goToCartPage() {
        driver.findElement(By.id("cartur")).click();

        assertThat(driver.findElement(By.className("table-responsive")).isDisplayed()).isTrue();
        LOGGER.info("User was redirected to cart.");
    }

    public void sendOrderFromCartView() {
        driver.findElement(By.xpath("//button[contains(text(), 'Place Order')]")).click();

        WebElement purchaseButton = driver.findElement(By.xpath("//button[@onclick='purchaseOrder()']"));
        wait.until(ExpectedConditions.visibilityOf(purchaseButton));
        assertThat(purchaseButton.isDisplayed()).isTrue();
        LOGGER.info("Pop-up to enter personal data appeared correctly.");
    }

    public void enterPersonalDetailsNeededToFinishOrder() {
        driver.findElement(By.id("name")).sendKeys(NAME);
        driver.findElement(By.id("country")).sendKeys(COUNTRY);
        driver.findElement(By.id("city")).sendKeys(CITY);
        driver.findElement(By.id("card")).sendKeys(CREDIT_CARD);
        driver.findElement(By.id("month")).sendKeys(MONTH);
        driver.findElement(By.id("year")).sendKeys(YEAR);

        driver.findElement(By.xpath("//button[@onclick='purchaseOrder()']")).click();
        LOGGER.info("Order has been sent.");
    }

    public void clickButtonFromConfirmationOrderPopUp() {
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.className("sa-confirm-button-container")))
                .pause(Duration.ofSeconds(2)).click().perform();
        LOGGER.info("Order had been made.");

        wait.until(ExpectedConditions.urlContains("index"));
        String currentUrl = driver.getCurrentUrl();
        assertThat(currentUrl.equalsIgnoreCase(WEBSHOP_URL)).isTrue();
        LOGGER.info("User was redirected to main page.");
    }

    public void closeBrowser() {
        driver.close();
        LOGGER.info("Browser has been closed. Test ended.");
    }
}
