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
    public class VolumeEnumTest  {
        @Autowired
        private TemperatureService temperatureService;
        @Test
        public void correctVolumeConversion() {
            val result = Volumes.CUPS.convert(25.6, Volumes.LITERS);
            Assertions.assertEquals(result, 6.0566528);
        }
        @Test
        public void getVolumeFromString() {
            val getVolumeFromString = Volumes.getVolumes("cubic-inches");
            Assertions.assertNotEquals(getVolumeFromString, Volumes.CUPS);
        }
        @Test
        public void roundedToTenth() {
            val authoritativeAnswer = Volumes.CUPS.convert(25.6, Volumes.LITERS);
            val roundedToTenthResult = temperatureService.roundToTenths(authoritativeAnswer);

            Assertions.assertEquals(roundedToTenthResult, 6.1);

        }
}
