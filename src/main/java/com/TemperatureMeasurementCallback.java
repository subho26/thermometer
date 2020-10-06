package com;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.*;
import org.springframework.scheduling.*;
import org.springframework.scheduling.annotation.*;
import org.springframework.scheduling.concurrent.*;
import org.springframework.stereotype.*;
@FunctionalInterface
interface TemperatureMeasurementCallback {
    public void temperatureMeasured(int temperature);
}
interface Thermometer {
    public int measure();
}


@Configuration
@EnableScheduling
@Import({FakeThermometer.class, WeatherForecastService.class})
class Config {

    @Bean
    public TemperatureMeasurementCallback callback() {
        return (temperature) -> System.out.println(temperature);
    }
}
@Component
class FakeThermometer implements Thermometer {
    private int currentTemperature = 21;

    @Override
    public int measure() { return currentTemperature++; }
}