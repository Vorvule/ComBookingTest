import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class OnlyAvailableVariantsOptionTest {
    private WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new DriverSetup().getDriver();
        new IndexPageObject(driver).getIndexPage("Kemer");
    }

    @Test(description = "Checks for only available placement variants are depicted on the page when option is on")
    public void OnlyAvailableVariantsOption() {

        SearchPageObject searchPage = new SearchPageObject(driver).getSearchPage(driver);
        searchPage.selectOnlyAvailableVariants();

        new WebDriverWait(driver, 8)
                .until(ExpectedConditions.elementToBeClickable(searchPage.onlyAvailableVariantsRecommendedCheckbox));

        Assert.assertTrue(searchPage.checkOnlyAvailableVariantsOption(),
                "Not only available variants are selected while option is on");
    }

    @AfterMethod
    public void close() {
        driver.close();
        driver = null;
    }
}
