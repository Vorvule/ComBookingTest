import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class PlacementsMinimumQuantityTest {

    private WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new DriverSetup().getDriver();
        new IndexPageObject(driver).getIndexPage("Minsk");
    }

    @Test (description = "Checks for search results quantity is greater than their minimum set")
    public void PlacementsMinimumQuantity() {
        final int MINIMUM = 20;
        SearchPageObject searchPage = new SearchPageObject(driver).getSearchPage(driver);

        Assert.assertTrue(searchPage.countPlacementsQuantity() + 1 > MINIMUM,
                "Placements quantity is lesser than minimum allowed");
    }

    @AfterMethod
    public void close() {
        driver.close();
        driver = null;
    }
}