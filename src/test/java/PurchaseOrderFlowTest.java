import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class PurchaseOrderFlowTest {

    @Test
    public void purchaseOrderFlowTest() {
        System.setProperty("webdriver.gecko.driver", "src/test/resources/geckodriver.exe");
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();
        PageMethods pageMethods = new PageMethods(driver);

        pageMethods.openWebShopPage();
        pageMethods.clickLogInButton();
        pageMethods.inputLogIn();
        pageMethods.clickSamsungGalaxyS6ToSeeDetails();
        pageMethods.addProductToCart();
        pageMethods.dismissConfirmationAlert();
        pageMethods.goToCartPage();
        pageMethods.sendOrderFromCartView();
        pageMethods.enterPersonalDetailsNeededToFinishOrder();
        pageMethods.clickButtonFromConfirmationOrderPopUp();
        pageMethods.closeBrowser();
    }
}
