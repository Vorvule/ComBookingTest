import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverSetup {

    private WebDriver webDriver;

    // Constructor
    public DriverSetup() {
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
        webDriver = new ChromeDriver();
        webDriver.get("https://www.booking.com/");
        webDriver.manage().window().maximize();
    }

    // Getter
    public WebDriver getDriver() {
        return webDriver;
    }
}
