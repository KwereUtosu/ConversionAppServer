package com.flexion.converter.usecase;

import com.flexion.converter.domain.GenericResponseBody;
import com.flexion.converter.domain.TeacherRequest;
import com.flexion.converter.domain.Volumes;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class VolumeServiceTest {
    @Autowired
    private VolumeService volumeService;

    @Test
    public void result_should_be_invalid() {

        val teacherRequest = new TeacherRequest();
        teacherRequest.setInputNumericalValue(20.3);
        teacherRequest.setInputUnitOfMeasure("gallons");
        teacherRequest.setTargetUnitOfMeasure("kelvin");
        teacherRequest.setStudentResponse("40.6");

        val response = volumeService.effectVolumeConversions(teacherRequest);
        val invalid = GenericResponseBody.builder().response("invalid").build();
        Assertions.assertEquals(response, invalid);
    }

    @Test
    public void result_should_be_incorrect() {

        val teacherRequest = new TeacherRequest();
        teacherRequest.setInputNumericalValue(6.5);
        teacherRequest.setInputUnitOfMeasure("gallons");
        teacherRequest.setTargetUnitOfMeasure("cups");
        teacherRequest.setStudentResponse("dog");

        val response = volumeService.effectVolumeConversions(teacherRequest);
        val incorrect = GenericResponseBody.builder().response("incorrect").build();
        Assertions.assertEquals(response, incorrect);
    }

    @Test
    public void result_should_be_correct() {

        val teacherRequest = new TeacherRequest();
        teacherRequest.setInputNumericalValue(25.6);
        teacherRequest.setInputUnitOfMeasure("cups");
        teacherRequest.setTargetUnitOfMeasure("liters");
        teacherRequest.setStudentResponse("6.1");

        val response = volumeService.effectVolumeConversions(teacherRequest);
        val correct = GenericResponseBody.builder().response("correct").build();
        Assertions.assertEquals(response, correct);
    }

    @Test()
    public void numberFormatException() {
        Assertions.assertThrows(NumberFormatException.class, () -> {
            Volumes.TABLESPOONS.convert(Double.parseDouble("boy"), Volumes.GALLONS);
        });

    }

}
