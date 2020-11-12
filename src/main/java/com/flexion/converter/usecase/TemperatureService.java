package com.flexion.converter.usecase;

import com.flexion.converter.domain.GenericResponseBody;
import com.flexion.converter.domain.TeacherRequest;
import com.flexion.converter.domain.Temperatures;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Slf4j
@Service
public class TemperatureService {

    public GenericResponseBody effectTemperatureConversion(TeacherRequest teacherRequest) {
        Temperatures targetTemp;
        Temperatures getTemp;

        try {
            // Output list via forEach method and Logging::log method reference.
            getTemp = Temperatures.getTemperatures(teacherRequest.getInputUnitOfMeasure());


            targetTemp = Temperatures.getTemperatures(teacherRequest.getTargetUnitOfMeasure());
        } catch (NoSuchElementException exception) {
            // Output expected NoSuchElementExceptions.
            return GenericResponseBody.builder().response("invalid").build();
        }
        //if (getTemp )
        try {
            val result = getTemp.convert(teacherRequest.getInputNumericalValue(), targetTemp);
            val authoritativeAnswer = roundToTenths(result);
            val studentResponse = roundToTenths(Double.parseDouble(teacherRequest.getStudentResponse()));

            log.info("Teacher's temperature result ==> " + authoritativeAnswer);
            log.info("Student temperature result ==> " + studentResponse);



            if (!(authoritativeAnswer == (studentResponse))) {
                return GenericResponseBody.builder().response("incorrect").build();
            }

        }
        catch (NumberFormatException ne) {
            return GenericResponseBody.builder().response("incorrect").build();

        }

        return GenericResponseBody.builder().response("correct").build();

    }

    public double roundToTenths(double value) {
        String str = String.format("%1.1f", value);
        return Double.parseDouble(str);
    }

}
