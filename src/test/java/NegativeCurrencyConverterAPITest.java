import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class NegativeCurrencyConverterAPITest {

    private static Response response;

    // Wrong API Access Code Test
    @Test
    public void wrongAPIAccessCodeTest() {
        response = given().contentType("application/json").get(Consts.URL + Consts.WRONG_API_ACCESS_KEY);
        System.out.println(response.asString());
        response.then().statusCode(200);
        response.then().body("error.code", equalTo(101));
        response.then().body("error.type", equalTo("invalid_access_key"));
        response.then().body("error.info", containsString("You have not supplied a valid API Access Key"));
    }

    // Wrong Currency Test
    @Test
    public void wrongCurrencyTest() {
        response = given().contentType("application/json").get(Consts.URL + Consts.API_ACCESS_KEY + Consts.WRONG_CURRENCY);
        System.out.println(response.asString());
        response.then().statusCode(200);
        response.then().body("error.code", equalTo(202));
        response.then().body("error.info", containsString("You have provided one or more invalid Currency Codes"));

    }

    // Test API with Incorrect Year Number
    @Test
    public void CurrencyTestUSDCADwithIncorrectYear() {
        response = given().contentType("application/json").get(Consts.HISTORICAL_URL + Consts.API_ACCESS_KEY + Consts.CAD_CURRENCY + Consts.INCORRECT_YEAR_NUMBER);
        System.out.println(response.asString());
        response.then().statusCode(200);
        response.then().body("error.code", equalTo(302));
        response.then().body("error.info", containsString("You have entered an invalid date"));
    }

    // Test API with Incorrect Month Number
    @Test
    public void CurrencyTestUSDILSwithIncorrectMonth() {
        response = given().contentType("application/json").get(Consts.HISTORICAL_URL + Consts.API_ACCESS_KEY + Consts.CAD_CURRENCY + Consts.INCORRECT_MONTH_NUMBER);
        System.out.println(response.asString());
        response.then().statusCode(200);
        response.then().body("error.code", equalTo(302));
        response.then().body("error.info", containsString("You have entered an invalid date"));

    }

    // Test API with Incorrect Day Number
    @Test
    public void CurrencyTestUSDILSwithIncorrectDay() {
        response = given().contentType("application/json").get(Consts.HISTORICAL_URL + Consts.API_ACCESS_KEY + Consts.CAD_CURRENCY + Consts.INCORRECT_DAY_NUMBER);
        System.out.println(response.asString());
        response.then().statusCode(200);
        response.then().body("error.code", equalTo(302));
        response.then().body("error.info", containsString("You have entered an invalid date"));

    }

    // Test API with not specify a date
    @Test
    public void CurrencyTestUSDILSwithNoDate() {
        response = given().contentType("application/json").get(Consts.HISTORICAL_URL + Consts.API_ACCESS_KEY + Consts.CAD_CURRENCY);
        System.out.println(response.asString());
        response.then().statusCode(200);
        response.then().body("error.code", equalTo(301));
        response.then().body("error.info", containsString("You have not specified a date"));

    }

    //  Incorrect HTTP Test
    @Test
    public void IncorrectHTTPTest() {
        response = given().contentType("application/json").get("https://api.currencylayer.com/live?access_key=3e2c592197fbd50387dc1dee8dfef341&currencies=CAD");
        System.out.println(response.asString());
        response.then().statusCode(200);
        response.then().body("error.code", equalTo(105));
        response.then().body("error.info", containsString("Access Restricted - Your current Subscription Plan does not support HTTPS Encryption."));
    }
}