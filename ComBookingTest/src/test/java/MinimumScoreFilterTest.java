import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class MinimumScoreFilterTest {

    private WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new DriverSetup().getDriver();
        new IndexPageObject(driver).getIndexPage("Kemer");
    }

    @Test(description = "Checks for placement scores are greater than their minimum")
    public void MinimumScoreFilter() {

        SearchPageObject searchPage = new SearchPageObject(driver).getSearchPage(driver);
        searchPage.filterMinimumScoreSeven();

        new WebDriverWait(driver, 16)
                .until(ExpectedConditions.elementToBeClickable(searchPage.sevenPlusScoreRecommendedCheckbox));

        Assert.assertTrue(searchPage.checkPlacementScoresExceedSeven(),
                "There are placement cards with the score lesser than minimum set");
    }

    @AfterMethod
    public void close() {
        driver.close();
        driver = null;
    }
}
