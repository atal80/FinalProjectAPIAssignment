import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class PositiveCurrencyConverterAPITest {

    private static Response response;

//    @BeforeAll
//    public static void setup() {
//        response = given().contentType("application/json").get(Consts.URL + Consts.API_ACCESS_KEY);
//        System.out.println(response.asString());
//    }

    // API Access Code Test
    @Test
    public void apiAccessCodeTest() {
        response = given().contentType("application/json").get(Consts.URL + Consts.API_ACCESS_KEY);
        System.out.println(response.asString());
        response.then().statusCode(200);
    }

    // USD to CAD Currency Test
    @Test
    public void CurrencyTestUSDCAD() {
        response = given().contentType("application/json").get(Consts.URL + Consts.API_ACCESS_KEY + Consts.CAD_CURRENCY);
        System.out.println(response.asString());
        response.then().statusCode(200);
        response.then().body("source", containsString("USD"));
        response.then().body("quotes.USDCAD", equalTo(1.20659f));
    }

    // USD to EUR Currency Test
    @Test
    public void CurrencyTestUSDEUR() {
        response = given().contentType("application/json").get(Consts.URL + Consts.API_ACCESS_KEY + Consts.EUR_CURRENCY);
        System.out.println(response.asString());
        response.then().statusCode(200);
        response.then().body("source", containsString("USD"));
        response.then().body("quotes.USDEUR", equalTo(0.820934f));
    }

    // USD to RUB Currency Test
    @Test
    public void CurrencyTestUSDRUB() {
        response = given().contentType("application/json").get(Consts.URL + Consts.API_ACCESS_KEY + Consts.RUB_CURRENCY);
        System.out.println(response.asString());
        response.then().statusCode(200);
        response.then().body("source", containsString("USD"));
        response.then().body("quotes.USDRUB", equalTo(73.622604f));
    }

    // USD to ILS Currency Test
    @Test
    public void CurrencyTestUSDILS() {
        response = given().contentType("application/json").get(Consts.URL + Consts.API_ACCESS_KEY + Consts.ILS_CURRENCY);
        System.out.println(response.asString());
        response.then().statusCode(200);
        response.then().body("source", containsString("USD"));
        response.then().body("quotes.USDILS", equalTo(3.25582f));
    }

    // Test API with Correct Data
    @Test
    public void CurrencyTestUSDCADwithCorrectData() {
        response = given().contentType("application/json").get(Consts.URL + Consts.API_ACCESS_KEY + Consts.CAD_CURRENCY + Consts.CORRECT_DATA);
        System.out.println(response.asString());
        response.then().statusCode(200);
        response.then().body("source", containsString("USD"));
        response.then().body("quotes.USDCAD", equalTo(1.20659f));

    }

    //CAD, EUR, RUB, ILS Currencies Test
    @ParameterizedTest
    @ValueSource(strings = {"&currencies=cad", "&currencies=eur", "&currencies=ils", "&currencies=rub"})
    public void currenciesResultTest(String currency) {
        response = given().contentType("application/json").get(Consts.URL + Consts.API_ACCESS_KEY + currency);
        System.out.println(response.asString());
        response.then().statusCode(200);
        response.then().body("source", containsString("USD"));
    }
}
