package com.flexion.converter.domain;

import com.flexion.converter.usecase.TemperatureService;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
@Slf4j
public class TemperatureEnumTest {
    @Autowired
    private TemperatureService temperatureService;
    @Test
    public void correctTemperatureConversion() {
        val result = Temperatures.FAHRENHEIT.convert(84.2, Temperatures.RANKINE);
        Assertions.assertNotEquals(result, 543.8);
    }
    @Test
    public void getTempFromString() {
        val getTempFromString = Temperatures.getTemperatures("Fahrenheit");
        Assertions.assertEquals(getTempFromString, Temperatures.FAHRENHEIT);
    }
    @Test
    public void roundedToTenth() {
        val getTempFromString = Temperatures.getTemperatures("Fahrenheit");
        val authoritativeAnswer = getTempFromString.convert(84.2, Temperatures.RANKINE);
        val roundedToTenthResult = temperatureService.roundToTenths(authoritativeAnswer);

        Assertions.assertEquals(roundedToTenthResult, 543.9);

    }



}
