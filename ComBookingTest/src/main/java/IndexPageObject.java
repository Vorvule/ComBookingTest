import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

class IndexPageObject {

    @FindBy(id = "ss")
    @CacheLookup
    private WebElement searchInputField;

    // Constructor
    IndexPageObject(WebDriver driver) {
        String PAGE_URL = "https://www.booking.com";
        driver.get(PAGE_URL);
        PageFactory.initElements(driver, this);
    }

    // Getter
    IndexPageObject getIndexPage(String placementName) {
        searchInputField.sendKeys(placementName);
        searchInputField.submit();
        return this;
    }

}