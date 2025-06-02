package Utils;

import java.time.Duration;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import com.github.javafaker.Faker;

public class TestBase {

	public  WebDriver driver;
	private static final Logger LOG = LogManager.getLogger(TestBase.class);

	public  WebDriver getdriver() {

		if (driver == null) {
			if (PropertiesFile.getProperty("browser").equalsIgnoreCase("edge")) {
				driver = new EdgeDriver();

			} else if (PropertiesFile.getProperty("browser").equalsIgnoreCase("chrome")) {
				driver = new ChromeDriver();
			} else {
				driver = new ChromeDriver();
			}
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			driver.get(PropertiesFile.getProperty("url"));
			driver.manage().window().maximize();

		}
		return driver;

	}

	Faker faker=new Faker();

	public String CreatefirstName() {
		String name = faker.name().firstName();

		return name;
	}

	public String CreateLastName() {
		String name = faker.name().lastName();

		return name;
	}

	public String createid() {
	    return String.valueOf(faker.number().numberBetween(10000, 99999));
	}


	public String Createusername() {
		String name = faker.name().username();

		return name;
	}

	public String Createpassword() {
		String pwd = faker.internet().password(10, 15, true, true, true);

		return pwd;
	}

}
