import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

class SearchPageObject {

    // Constructor
    SearchPageObject(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    // Getter
    SearchPageObject getSearchPage(WebDriver driver) {
        this.setCalendarStartDate();
        this.endDateOpen();
        this.setCalendarEndDate();
        this.setChildrenQuantity();
        this.setChildAge();
        this.clickSearchSubmitButton();
        return this;
    }

    // List of prices elements
    @FindBys(@FindBy(xpath = "//*[@class=\'bui-price-display__value prco-inline-block-maker-helper \']"))
    private List<WebElement> priceElementsList;

    // List of placement cards
    @FindBys(@FindBy(xpath = "//*[@class=\"sr_item  sr_item_new sr_item_default sr_property_block  sr_flex_layout                 \"]"))
    private List<WebElement> placementElementsList;

    @FindBy(xpath = "//*[@id=\"frm\"]/div[3]/div/div[1]/div[1]/div/div/div[2]/div[2]/div[3]/div/div/div[2]/table/tbody/tr[3]/td[1]/span")
    @CacheLookup
    private WebElement startDateCell;

    @FindBy(xpath = "//*[@id=\"frm\"]/div[3]/div/div[1]/div[2]/div/div/div[1]/div/i")
    @CacheLookup
    private WebElement endDateExpandIcon;

    @FindBy(xpath = "//*[@id=\"frm\"]/div[3]/div/div[1]/div[2]/div/div/div[2]/div[2]/div[3]/div/div/div[2]/table/tbody/tr[4]/td[4]/span")
    @CacheLookup
    private WebElement endDateCell;

    @FindBy(xpath = "//*[@id=\"group_children\"]/option[2]")
    @CacheLookup
    private WebElement childrenQuantityField;

    @FindBy(xpath = "//*[@id=\"frm\"]/div[4]/div/div/div/div[2]/div[2]/div/div/select/option[4]")
    @CacheLookup
    private WebElement childAgeField;

    @FindBy(xpath = "//*[@id=\"frm\"]/div[5]/div[2]/button/span[1]")
    @CacheLookup
    private WebElement submitSearchDataButton;

    @FindBy(xpath = "//*[@id=\"sort_by\"]/ul/li[3]/a")
    @CacheLookup
    private WebElement sortPricesAscendingButton;

    // Seven plus score checkbox
    @FindBy(xpath = "//*[@id=\"filter_review\"]/div[2]/a[3]/label/div")
    private WebElement sevenPlusScoreCheckbox;

    // List of ribbons with placement scores
    @FindBys(@FindBy(xpath = "//div[@class=\"bui-review-score__badge\"]"))
    private List<WebElement> scoreRibbonsList;

    // Show only available variants opiton
    @FindBy(xpath = "//*[@id=\"filter_out_of_stock\"]/div[2]/a/label/div")
    private WebElement onlyAvailableVariantsCheckbox;

    // List of Sold out property notifications
    @FindBys(@FindBy(xpath = "//span[@class=\"sold_out_property\"]"))
    private List<WebElement> soldOutPropertyNotes;

    // List of Sales Leader ribbons
    @FindBys(@FindBy(xpath = "//div[@class=\"ribbon js-fly-content-tooltip\"]"))
    List<WebElement> salesLeaderRibbonsList;

    // Sort ascending button
    @FindBy(xpath = "//*[@id=\"sort_by\"]/ul/li[3]")
    WebElement sortAscendingButton;

    // Recommended score seven plus checkbox
    @FindBy(xpath = "//*[@id=\"filter_recent\"]//span[contains(text(),'Хорошо: 7+')]")
    WebElement sevenPlusScoreRecommendedCheckbox;

    // Recommended to show only available variants checkbox
    @FindBy(xpath = "//*[@id=\"filter_recent\"]//span[contains(text(),'Показать только доступные варианты')]")
    WebElement onlyAvailableVariantsRecommendedCheckbox;

    // Methods
    private void setCalendarStartDate() {
        startDateCell.click();
    }

    private void endDateOpen() {
        endDateExpandIcon.click();
    }

    private void setCalendarEndDate() {
        endDateCell.click();
    }

    private void setChildrenQuantity() {
        childrenQuantityField.click();
    }

    private void setChildAge() {
        childAgeField.click();
    }

    private void clickSearchSubmitButton() {
        submitSearchDataButton.click();
    }

    void sortPricesAscending() {
        sortPricesAscendingButton.click();
    }

    void selectOnlyAvailableVariants() {
        onlyAvailableVariantsCheckbox.click();
    }

    boolean checkOnlyAvailableVariantsOption() {
        return soldOutPropertyNotes.size() < 1;
    }

    boolean checkSortPricesAscending() {
        String textPrice;
        int integerPrice;
        List<Integer> prices = new ArrayList<Integer>();
        boolean checkResult = true;
        for (WebElement i : priceElementsList) {
            textPrice = i.getText();
            textPrice = textPrice.substring(4);
            textPrice = textPrice.replaceAll("\\s", "");
            integerPrice = Integer.parseInt(textPrice);
            prices.add(integerPrice);
        }
        for (int j : prices) {
            if ((j - 1) > j) {
                checkResult = false;
                break;
            }
        }
        return checkResult;
    }

    void filterMinimumScoreSeven() {
        sevenPlusScoreCheckbox.click();
    }

    boolean checkPlacementScoresExceedSeven() {
        boolean checkResult = true;
        String textScore;
        float floatScore;
        for (WebElement i : scoreRibbonsList) {
            textScore = i.getText();
            textScore = textScore.replaceAll(",", ".");
            floatScore = Float.parseFloat(textScore);
            if (floatScore < 7.0) {
                checkResult = false;
                break;
            }
        }
        return checkResult;
    }

    int countPlacementsQuantity() {
        return placementElementsList.size();
    }
}