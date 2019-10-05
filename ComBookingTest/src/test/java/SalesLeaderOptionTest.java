import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SalesLeaderOptionTest {

    private WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new DriverSetup().getDriver();
        new IndexPageObject(driver).getIndexPage("Lida");
    }

    @Test (description = "Checks for Sales Leader ribbon is the only one or absent on the page")
    public void SalesLeaderOption() {

        SearchPageObject searchPage = new SearchPageObject(driver).getSearchPage(driver);

        Assert.assertTrue(searchPage.salesLeaderRibbonsList.size() <= 1,
                "Sales Leader quantity exceeds one placement");
    }

    @AfterMethod
    public void close() {
        driver.close();
        driver = null;
    }
}