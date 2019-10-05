import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AscendingPriceSortingTest {

    private WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new DriverSetup().getDriver();
        new IndexPageObject(driver).getIndexPage("Kemer");
    }

    @Test(description = "Checks for prices are sorted ascending properly")
    public void AscendingPriceSorting() {

        SearchPageObject searchPage = new SearchPageObject(driver).getSearchPage(driver);
        searchPage.sortPricesAscending();

        new WebDriverWait(driver, 16)
                .until(ExpectedConditions.attributeContains(searchPage.sortAscendingButton,
                        "class", "selected"));

        Assert.assertTrue(searchPage.checkSortPricesAscending(), "Incorrect ascending prices sorting");
    }

    @AfterMethod
    public void close() {
        driver.close();
        driver = null;
    }

}