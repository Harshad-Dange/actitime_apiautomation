package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class LoginStepDefinition {
    WebDriver driver;
    @Given("I login to application with valid credentials")
    public void performLogin(){
        //launch browser
        //Navigate to website
        //perform login
        System.out.println("Code to perform login operation");
//        WebDriverManager.chromedriver().setup();
//        driver = new ChromeDriver();
//        driver.get("https://demo.actitime.com");

    }

    @Then("I verify user is logged in to application successfully")
    public void iVerifyUserIsLoggedInToApplicationSuccessfully(List<String> data) {

        //verify login successfully
        System.out.println("Code to verify successfull login ");
        System.out.println(data);
//        driver.getTitle();
    }

    @Given("I launch the {string} browser")
    public void launchBrowser(String browser){
        //code to launch the browser
        System.out.println("The browser is: "+ browser);

        if(browser.equals("chrome")){
            //launch chrome browser
        } else if (browser.equals("edge")) {
            //launch edge browser
        }

    }

    @When("I enter username {string} and password {string} and perform login")
    public void login(String username, String password){
//        Objects.nonNull(username);
       if(username !=null && !username.isEmpty()){
//           driver.findElement(By.xpath("username")).sendKeys(username);
       }
       if (password !=null && !password.isEmpty()){
//           driver.findElement(By.xpath("password")).sendKeys(username);
       }
        System.out.println("username is : " + username );
        System.out.println("The password is : "+ password);
    }


    @When("I enter below credentials")
    public void iEnterBelowCredentials(Map<String,String> creds) {
        driver.findElement(By.xpath("username")).sendKeys(creds.get("username"));
        driver.findElement(By.xpath("password")).sendKeys(creds.get("password"));
        driver.findElement(By.xpath("login")).click();

    }
}
