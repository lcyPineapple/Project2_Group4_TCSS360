import GUI.forecast.Forecast;
import GUI.forecast.weathers.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ForecastTest {

    private Forecast forecast;

    @Test
    void testWeatherConditions() {
        this.forecast = new Forecast();
        testWeatherCondition(25, 0, 60, 40, new MostlyClear());
        testWeatherCondition(30, 30, 40, 0, new MostlyCloudy());
        testWeatherCondition(30, 20, 20, 0, new PartlyCloudy());
        testWeatherCondition(60, 20, 30, 20, new Rain());
        testWeatherCondition(70, 20, 0, 10, new Snow());
    }

    private void testWeatherCondition(int humidity, int windspeed, int temperature, int rainfall, Weather expectedWeather) {
        forecast.updateWeather(humidity, windspeed, temperature, rainfall);
        Assertions.assertEquals(forecast.getCurrentWeather(), expectedWeather);
    }
}
