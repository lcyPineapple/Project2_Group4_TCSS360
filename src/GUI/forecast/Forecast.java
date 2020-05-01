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

public class Forecast extends JComponent {
    private List<Weather> weatherList;
    private Weather currentWeather;
    private BufferedImage cardImage;

    public Forecast() {
        this.weatherList = new ArrayList<>();
        this.currentWeather = new MostlyClear();
        initWeathers();
        updateImagePath();
    }

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
        updateImagePath();
    }

    private void updateImagePath() {
        try {
            cardImage = ImageIO.read(new File(currentWeather.getImageFilePath()));
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void paintComponent(final Graphics g) {
        super.paintComponent(g);
        g.drawImage(cardImage, 0, 0, this.getWidth(), this.getHeight(), null);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(60, 60);
    }

    private void initWeathers() {
        weatherList.add(new MostlyClear());
        weatherList.add(new MostlyCloudy());
        weatherList.add(new PartlyCloudy());
        weatherList.add(new Rain());
        weatherList.add(new Snow());
    }
}
