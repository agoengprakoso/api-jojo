package apiRequests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class weather {

    @Test
    public void testGetCurrentWeather() {
        String url = "https://api.weatherbit.io/v2.0/current?lat=40.730610&lon=-73.935242&key=2eacabfccbb74d0fa0099164670d7eb8";

        Response resp = RestAssured.get(url);
        String statecode = resp.jsonPath().get("data[0].state_code");

        Assert.assertEquals(resp.getStatusCode(), 200);
        Assert.assertEquals(statecode, "NY");
    }

    @Test
    public void testGetForecast() {
        String url = "https://api.weatherbit.io/v2.0/forecast/hourly?postal_code=28546&key=2eacabfccbb74d0fa0099164670d7eb8";

        Response resp = RestAssured.get(url);
        String timestamp = resp.jsonPath().get("data[0].timestamp_utc");
        String wheather = resp.jsonPath().get("data[0].weather").toString();

        Assert.assertEquals(resp.getStatusCode(), 200);
        Assert.assertTrue(timestamp.contains("2022-10"));
        Assert.assertTrue(wheather.contains("description"));
    }
}
