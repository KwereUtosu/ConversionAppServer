package com.flexion.converter.usecase;

import com.flexion.converter.domain.GenericResponseBody;
import com.flexion.converter.domain.TeacherRequest;
import com.flexion.converter.domain.Temperatures;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.NoSuchElementException;

@SpringBootTest
@Slf4j
public class TemperatureServiceTest {
    @Autowired
    private TemperatureService temperatureService;

    @Test
    public void result_should_be_invalid() {

        val teacherRequest = new TeacherRequest();
        teacherRequest.setInputNumericalValue(20.3);
        teacherRequest.setInputUnitOfMeasure("dog");
        teacherRequest.setTargetUnitOfMeasure("celsius");
        teacherRequest.setStudentResponse("40.6");

        val response = temperatureService.effectTemperatureConversion(teacherRequest);
        val invalid = GenericResponseBody.builder().response("invalid").build();
        Assertions.assertEquals(response, invalid);
    }
    @Test
    public void result_should_be_incorrect() {

        val teacherRequest = new TeacherRequest();
        teacherRequest.setInputNumericalValue(317.33);
        teacherRequest.setInputUnitOfMeasure("Kelvin");
        teacherRequest.setTargetUnitOfMeasure("Fahrenheit");
        teacherRequest.setStudentResponse("111.554");

        val response = temperatureService.effectTemperatureConversion(teacherRequest);
        val incorrect = GenericResponseBody.builder().response("incorrect").build();
        Assertions.assertEquals(response, incorrect);
    }
    @Test
    public void result_should_be_correct() {

        val teacherRequest = new TeacherRequest();
        teacherRequest.setInputNumericalValue(84.2);
        teacherRequest.setInputUnitOfMeasure("Fahrenheit");
        teacherRequest.setTargetUnitOfMeasure("Rankine");
        teacherRequest.setStudentResponse("543.94");

        val response = temperatureService.effectTemperatureConversion(teacherRequest);
        val correct = GenericResponseBody.builder().response("correct").build();
        Assertions.assertEquals(response, correct);
    }
    @Test()
    public void noSuchElementException(){
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            Temperatures.getTemperatures("car");
        });

    }
}
