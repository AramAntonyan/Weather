import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class AramAntonyanTest {

    //  TC_1_1 - Тест кейс:
    //  1. Открыть страницу https://openweathermap.org/
    //  2. Набрать в строке поиска город Paris
    //  3.Нажать пункт меню Search
    //  4.Из выпадающего списка выбрать Paris, FR
    //  5.Подтвердить, что заголовок изменился на  "Paris, FR"

    @Test
    public void testH2TagText_WhenSearchingCityCountry() throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\alexa\\Documents\\ARAM\\ChromeDriver\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();

        //Arrange
        String url = "https://openweathermap.org/";
        String cityName = "Paris";
        String expectedResult = "Paris, FR";

        //Act
        driver.get(url);
        Thread.sleep(5000);

        WebElement searchCityField = driver.findElement(
                By.xpath("//div[@id='weather-widget']//input[@placeholder = 'Search city']")
        );

        searchCityField.click();
        searchCityField.sendKeys(cityName);

        WebElement searchButton = driver.findElement(
                By.xpath("//button[@type ='submit']")
        );

        searchButton.click();

        Thread.sleep(1000);

        WebElement parisFRChoiceInDropdownMenu = driver.findElement(
                By.xpath("//ul[@class='search-dropdown-menu']/li/span[text()='Paris, FR ']")
        );

        parisFRChoiceInDropdownMenu.click();

        WebElement h2CityCountryHeader = driver.findElement(
                By.xpath("//div[@id='weather-widget']//h2")
        );

        Thread.sleep(2000);


        String actualResult = h2CityCountryHeader.getText();
        Thread.sleep(5000);


        //Assert
        Assert.assertEquals(actualResult, expectedResult);


        Thread.sleep(5000);


        driver.quit();
    }
    //     TC_11_01
//    1. Открыть базовую ссылку
//    2. Нажать на пункт меню Guide
//    3. Подтвердить, что вы перешли на страницу со ссылкой https://openweathermap.org/guide
//    и что title этой страницы OpenWeatherMap API guide - OpenWeatherMap

    @Test
    public void testTitleGuide() throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\alexa\\Documents\\ARAM\\ChromeDriver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        //Arrange
        String url = "https://openweathermap.org/";
        String expectedResultTitle = "OpenWeatherMap API guide - OpenWeatherMap";
        String expectedResultUrl = "https://openweathermap.org/guide";

        //Act
        driver.get(url);
        Thread.sleep(5000);
        driver.manage().window().maximize();

        WebElement guideButton = driver.findElement(
                By.xpath("//div[@id='desktop-menu']//a[text()='Guide']")
        );

        guideButton.click();
        Thread.sleep(5000);

        String actualResultUrl = driver.getCurrentUrl();
        String actualResultTitle = driver.getTitle();

        //Assert
        Assert.assertEquals(actualResultTitle, expectedResultTitle);
        Assert.assertEquals(actualResultUrl, expectedResultUrl);


        driver.quit();
    }

    //TC_11_02
    //  1.  Открыть базовую ссылку
    //  2.  Нажать на единицы измерения Imperial: °F, mph
    //  3.  Подтвердить, что температура для города показана в Фарингейтах

    @Test
    public void testTempIsDisplayedInFahrenheit() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\alexa\\Documents\\ARAM\\ChromeDriver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();


        //Arrange
        String url = "https://openweathermap.org/";
        String expectedResult1 = "F";
        String fTempSymbol = "°F";
        driver.get(url);
//        driver.manage().window().maximize();
        Thread.sleep(5000);

        //Act
        WebElement imperialFahrenheitButton = driver.findElement(
                By.xpath("//div[text()='Imperial: °F, mph']")
                //div[@class='controls']//div[3]
        );

        imperialFahrenheitButton.click();
        Thread.sleep(5000);


        WebElement tempF = driver.findElement(
                By.xpath("//div[@class='grid-container grid-4-5']/div/div/div/span")
        );


        String actualResult1 = tempF.getText();

        //Assert
        Assert.assertEquals(actualResult1.substring(actualResult1.length() - 1), expectedResult1);
        Assert.assertTrue(tempF.getText().contains(fTempSymbol));


        driver.quit();

    }


    //TC_11_03
//      1. Открыть базовую ссылку
//      2. Подтвердить, что внизу страницы есть панель с текстом
//      “We use cookies which are essential for the site to work.
//      We also use non-essential cookies to help us improve our services.
//      Any data collected is anonymised. You can allow all cookies or manage them individually.”
//      3. Подтвердить, что на панели внизу страницы есть 2 кнопки “Allow all” и “Manage cookies”


    @Test
    public void testApprovePolicyOfCookiesAndTwoButtonsInPanel() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\alexa\\Documents\\ARAM\\ChromeDriver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();


        //Arrange
        String url = "https://openweathermap.org/";
        String expectedResult = "We use cookies which are essential for the site to work. "
                + "We also use non-essential cookies to help us improve our services. "
                + "Any data collected is anonymised. "
                + "You can allow all cookies or manage them individually.";

        //div[@class='stick-footer-panel']


        //Act
        driver.get(url);
        Thread.sleep(3000);

        WebElement stickFooterPanel = driver.findElement(
                By.xpath("//div[@class='stick-footer-panel']")
        );

        WebElement textCookies = driver.findElement(
                By.xpath("//p[@class='stick-footer-panel__description']")
        );


        WebElement allowAllButton = driver.findElement(
                By.xpath("//button[@class='stick-footer-panel__link']")
        );

        WebElement manageCookiesButton = driver.findElement(
                By.xpath("//a[@class='stick-footer-panel__link']")
        );


        String actualResult = textCookies.getText();

        Assert.assertEquals(
                driver.findElements(By.xpath("//div[@class='stick-footer-panel__btn-container']/*"))
                        .size(), 2
        );

        Assert.assertTrue(allowAllButton.isDisplayed());
        Assert.assertTrue(manageCookiesButton.isDisplayed());
        Assert.assertEquals(actualResult, expectedResult);
        Assert.assertTrue(stickFooterPanel.isDisplayed());


        driver.quit();

    }


    //TC_11_03
//      1. Открыть базовую ссылку
//      2. Подтвердить, что в меню Support есть 3 подменю
//      с названиями “FAQ”, “How to start” и “Ask a question”

    @Test
    public void testSupportMenuHasFaqHowToStartAskQuestionNames() throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\alexa\\Documents\\ARAM\\ChromeDriver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String expectedResultFaq = "FAQ";
        String expectedResultHowToStart = "How to start";
        String expectedResultAskQuestion = "Ask a question";
        driver.get(url);
        driver.manage().window().maximize();
        Thread.sleep(3000);

        WebElement supportButton = driver.findElement(
                By.xpath("//div[@id='support-dropdown']")
        );

        Thread.sleep(3000);
        supportButton.click();

        WebElement faqButton = driver.findElement(
                By.xpath("//ul[@id='support-dropdown-menu']//a[text()= 'FAQ']")
        );
        WebElement howToStartButton = driver.findElement(
                By.xpath("//ul[@class='dropdown-menu dropdown-visible']//a[text()= 'How to start']")
        );

        WebElement askQuestionButton = driver.findElement(
                By.xpath("//ul[@class='dropdown-menu dropdown-visible']//a[text()= 'Ask a question']")
        );
        String actualResultFaq = faqButton.getText();
        String actualResultHowToStartButton = howToStartButton.getText();
        String actualResultAskQuestion = askQuestionButton.getText();


        Assert.assertEquals(
                driver.findElements(By.xpath("//ul[@id='support-dropdown-menu']/*")).size(), 3
        );
        Assert.assertEquals(actualResultFaq, expectedResultFaq);
        Assert.assertEquals(actualResultHowToStartButton, expectedResultHowToStart);
        Assert.assertEquals(actualResultAskQuestion, expectedResultAskQuestion);


        driver.quit();


    }

    //TC_11_05
//    1. Открыть базовую ссылку
//    2. Нажать пункт меню Support → Ask a question
//    3. Заполнить поля Email, Subject, Message
//    4. Не подтвердив CAPTCHA, нажать кнопку Submit
//    5. Подтвердить, что пользователю будет показана ошибка
//    “reCAPTCHA verification failed, please try again.”

    @Test
    public void testErrorRecaptchaIsDisplayed() throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\alexa\\Documents\\ARAM\\ChromeDriver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String email = "qa_05test@gmail.com";
        String message = "Hello Java!";
        String expectedResult = "reCAPTCHA verification failed, please try again.";

        driver.get(url);
        Thread.sleep(3000);
        driver.manage().window().maximize();

        WebElement supportButton = driver.findElement(
                By.xpath("//div[@id='support-dropdown']")
        );


        Thread.sleep(3000);
        supportButton.click();


        WebElement askQuestionButton = driver.findElement(
                By.xpath("//ul[@class='dropdown-menu dropdown-visible']//a[text()= 'Ask a question']")
        );
        askQuestionButton.click();
        Thread.sleep(3000);

        ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());   //????????????????????
        driver.switchTo().window(tabs2.get(1));  //??????????????????????????????????????????

        WebElement emailField = driver.findElement(
                By.xpath("//input[@id='question_form_email']")
        );
        Thread.sleep(3000);

        emailField.sendKeys(email);


        WebElement subjectFieldDropDown = driver.findElement(
                By.xpath("//select[@id='question_form_subject']")
        );
        subjectFieldDropDown.click();


        WebElement selectFieldChoice = driver.findElement(
                By.xpath("//option[@value='Data Issue']")
        );
        selectFieldChoice.click();

        WebElement messageTextField = driver.findElement(
                By.xpath("//textarea[@id='question_form_message']")
        );
        messageTextField.click();
        messageTextField.sendKeys(message);

        WebElement submitButton = driver.findElement(
                By.xpath("//input[@name='commit']")
        );
        submitButton.click();

        WebElement reCaptchaText = driver.findElement(
                By.xpath("//div[@class='help-block']")
        );

        String actualResult = reCaptchaText.getText();

        Thread.sleep(3000);


        Assert.assertEquals(actualResult, expectedResult);
        Thread.sleep(3000);


        driver.quit();
    }


    //TC_11_06
//    1.  Открыть базовую ссылку
//    2.  Нажать пункт меню Support → Ask a question
//    3.  Оставить значение по умолчанию в checkbox Are you an OpenWeather user?
//    4. Оставить пустым поле Email
//    5. Заполнить поля  Subject, Message
//    6. Подтвердить CAPTCHA
//    7. Нажать кнопку Submit
//    8. Подтвердить, что в поле Email пользователю будет показана ошибка “can't be blank”

    @Test
    @Ignore
    public void testEmptyEmailFieldDisplayedCantBeBlank() throws InterruptedException {

        String url = "https://openweathermap.org/";
        String expectedResult = "can't be blank";
        String message = "Hello Java!";

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\alexa\\Documents\\ARAM\\ChromeDriver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();


        driver.get(url);
        Thread.sleep(3000);
        driver.manage().window().maximize();

        WebElement supportButton = driver.findElement(
                By.xpath("//div[@id='support-dropdown']")
        );

        Thread.sleep(3000);
        supportButton.click();

        String originalWindow = driver.getWindowHandle();

        WebElement askQuestionButton = driver.findElement(
                By.xpath("//ul[@class='dropdown-menu dropdown-visible']//a[text()= 'Ask a question']")
        );
        askQuestionButton.click();
        Thread.sleep(3000);


//        ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());   //????????????????????
//        driver.switchTo().window(tabs2.get(1));  //??????????????????????????????????????????
//

        for (String windowHandle : driver.getWindowHandles()) {
            if (!originalWindow.contentEquals(windowHandle)) {
                driver.switchTo().window(windowHandle);
                break;

            }

        }


        WebElement subjectFieldDropDown = driver.findElement(
                By.xpath("//select[@id='question_form_subject']")
        );
        subjectFieldDropDown.click();

        Thread.sleep(3000);


        WebElement selectFieldChoice = driver.findElement(
                By.xpath("//option[@value='Data Issue']")
        );
        selectFieldChoice.click();

        WebElement messageTextField = driver.findElement(
                By.xpath("//textarea[@id='question_form_message']")
        );
        messageTextField.click();
        messageTextField.sendKeys(message);


        String window2 = driver.getWindowHandle();

        driver.switchTo().frame(driver.findElement(
                By.xpath("//iframe[@title='reCAPTCHA']")
        ));


        WebElement enterCaptcha = driver.findElement(
                By.xpath("//span[@class='recaptcha-checkbox goog-inline-block recaptcha-checkbox-unchecked rc-anchor-checkbox']")
        );

        enterCaptcha.click();

        Thread.sleep(10000);

        driver.switchTo().window(window2);

        WebElement submitButton = driver.findElement(
                By.xpath("//input[@name='commit']")
        );

        submitButton.click();

        Thread.sleep(10000);


        WebElement emailErrorAlert = driver.findElement(
                By.xpath("//span[@class='help-block']")
        );

        String actualResult = emailErrorAlert.getText();


        Assert.assertEquals(actualResult, expectedResult);


        Thread.sleep(3000);
        driver.quit();
    }


    //TC_11_07
//    1.  Открыть базовую ссылку
//    2.  Нажать на единицы измерения Imperial: °F, mph
//    3.  Нажать на единицы измерения Metric: °C, m/s
//    4.  Подтвердить, что в результате этих действий, единицы измерения температуры изменились с F на С
    @Test
    public void testChangingTemperatureValues() throws InterruptedException {


        String url = "https://openweathermap.org/";
        String temperatureValue = "°C";

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\alexa\\Documents\\ARAM\\ChromeDriver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();


        driver.get(url);
        Thread.sleep(3000);
        driver.manage().window().maximize();

        WebElement changingTemperatureToF = driver.findElement(
                By.xpath("//div[text() = 'Imperial: °F, mph']")
        );
        Thread.sleep(5000);
        changingTemperatureToF.click();

        Thread.sleep(5000);

        WebElement changingTemperatureToC = driver.findElement(
                By.xpath("//div[text() = 'Metric: °C, m/s']")
        );

        changingTemperatureToC.click();

        Thread.sleep(3000);

        String temperatureC = driver.findElement(
                By.xpath("//span[@class='heading'][contains(text(), 'C')]")
        ).getText();

        Boolean actualResult = temperatureC.contains(temperatureValue);

        Assert.assertTrue(actualResult);


        Thread.sleep(1000);
        driver.quit();
    }


    //TC_11_08
//    1.  Открыть базовую ссылку
//    2.  Нажать на лого компании
//    3.  Дождаться, когда произойдет перезагрузка сайта,
//    и подтвердить, что текущая ссылка не изменилась.


    @Test
    public void testLogoIsCurrentUrl() throws InterruptedException {


        System.setProperty("webdriver.chrome.driver", "C:\\Users\\alexa\\Documents\\ARAM\\ChromeDriver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String expectedResult = "https://openweathermap.org/";


        driver.get(url);
        Thread.sleep(3000);
        driver.manage().window().maximize();

        WebElement logo = driver.findElement(
                By.xpath("//img[@src='/themes/openweathermap/assets/img/logo_white_cropped.png']")
        );

        Thread.sleep(3000);
        logo.click();

        String actualResult = driver.getCurrentUrl();

        Assert.assertEquals(actualResult, expectedResult);


        driver.quit();

    }


//    TC_11_09
//    1.  Открыть базовую ссылку
//    2.  В строке поиска в навигационной панели набрать “Rome”
//    3.  Нажать клавишу Enter
//    4.  Подтвердить, что вы перешли на страницу в ссылке которой содержатся слова “find” и “Rome”
//    5. Подтвердить, что в строке поиска на новой странице вписано слово “Rome”

    @Test
    public void testRomeWeather() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\alexa\\Documents\\ARAM\\ChromeDriver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String urlCity = "Rome";
        String urlFind = "find";
        String expectedResult = "Rome";

        driver.get(url);
        Thread.sleep(3000);
        driver.manage().window().maximize();

        WebElement weatherInYourCityField = driver.findElement(
                By.xpath("//div[@id='desktop-menu']//input[@name='q']")
        );
        weatherInYourCityField.click();
        weatherInYourCityField.sendKeys(urlCity);
        Thread.sleep(3000);
        weatherInYourCityField.sendKeys(Keys.ENTER);


        Thread.sleep(7000);


        String secondPage = driver.getCurrentUrl();

        Boolean expectedResult2 = secondPage.contains(urlFind) && secondPage.contains(urlCity);
        Assert.assertTrue(expectedResult2);


        String actResult = driver.findElement(
                By.xpath("//input[@id='search_str']")
        ).getAttribute("value");

        Assert.assertEquals(actResult, expectedResult);


        driver.quit();


    }

//    TC_11_10
//    1.  Открыть базовую ссылку
//    2.  Нажать на пункт меню API
//    3.  Подтвердить, что на открывшейся странице пользователь видит 30 оранжевых кнопок

    @Test
    public void testApiLinkHas30OrangeButtons() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\alexa\\Documents\\ARAM\\ChromeDriver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();


        String url = "https://openweathermap.org/";
        int expectedResult = 30;

        driver.get(url);
        Thread.sleep(3000);
        driver.manage().window().maximize();

        WebElement apiLink = driver.findElement(
                By.xpath("//div[@id='desktop-menu']//ul//li//a[text()='API']")
        );
        Thread.sleep(3000);

        apiLink.click();


//
//        int orangeButtons = driver.findElements(
//                By.xpath("//a[contains(@class, 'orange')]")
//        ).size();

        int orangeButtons = driver.findElements(
                By.xpath("//a[@class='btn_block orange round'" + "or @class='ow-btn round btn-orange']")
        ).size();

        int actualResult = orangeButtons;


        Assert.assertEquals(actualResult, expectedResult);


        Thread.sleep(3000);
        driver.quit();
    }


}


//    @Test
//    public void test_name() {
//        System.setProperty("webdriver.chrome.driver", "C:\\Users\\alexa\\Documents\\ARAM\\ChromeDriver\\chromedriver.exe");
//        WebDriver driver = new ChromeDriver();
//
//
//
//
//        driver.quit();
//    }