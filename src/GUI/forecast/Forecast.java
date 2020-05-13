package GUI.forecast;

import GUI.forecast.weathers.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Forecast is a JComponent which display current weather condition with image.
 * @author Aaron Lam
 * @version 05-01-2020
 */
public class Forecast extends JComponent {
    private List<Weather> weatherList;
    private Weather currentWeather;
    private BufferedImage weatherImage;

    private static final int IMAGE_WIDTH = 60;

    public Forecast() {
        this.weatherList = new ArrayList<>();
        this.currentWeather = new MostlyClear();
        initWeathers();
        updateImageFilePath();
    }

    /**
     * Update weather condition with new weather data.
     * @param humidity
     * @param windSpeed
     * @param temperature
     * @param rainfall
     */
    public void updateWeather(int humidity, int windSpeed, int temperature, int rainfall) {
        Map<String, Integer> weatherData = new HashMap<>();
        weatherData.put("humidity", humidity);
        weatherData.put("windSpeed", windSpeed);
        weatherData.put("temperature", temperature);
        weatherData.put("rainfall", rainfall);
        for (Weather weather : weatherList) {
            if (weather.isConditionMatch(weatherData)) {
                currentWeather = weather;
                break;
            }
        }
        updateImageFilePath();
    }

    public Weather getCurrentWeather() {
        return currentWeather;
    }

    /**
     * Update weather image file path to the current weather condition.
     */
    private void updateImageFilePath() {
        try {
            weatherImage = ImageIO.read(new File(currentWeather.getImageFilePath()));
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Render the weather image.
     * @param g
     */
    @Override
    public void paintComponent(final Graphics g) {
        super.paintComponent(g);
        g.drawImage(weatherImage, 0, 0, this.getWidth(), this.getHeight(), null);
    }

    /**
     * Set the preferred size of forecast component.
     * @return Dimension of forecast component
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(IMAGE_WIDTH, IMAGE_WIDTH);
    }

    /**
     * Add default weather conditions.
     */
    private void initWeathers() {
        weatherList.add(new MostlyClear());
        weatherList.add(new MostlyCloudy());
        weatherList.add(new PartlyCloudy());
        weatherList.add(new Rain());
        weatherList.add(new Snow());
    }
}
