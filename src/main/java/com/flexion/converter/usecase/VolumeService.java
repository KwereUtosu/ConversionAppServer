package com.flexion.converter.usecase;

import com.flexion.converter.domain.GenericResponseBody;
import com.flexion.converter.domain.TeacherRequest;
import com.flexion.converter.domain.Temperatures;
import com.flexion.converter.domain.Volumes;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Slf4j
@Service
@AllArgsConstructor
public class VolumeService {
    private TemperatureService temperatureService;
    public GenericResponseBody effectVolumeConversions(TeacherRequest teacherRequest) {
        Volumes targetVolume;
        Volumes getVolumeFromString;


        try {
            // Output list via forEach method and Logging::log method reference.
            getVolumeFromString = Volumes.getVolumes(teacherRequest.getInputUnitOfMeasure());


            targetVolume = Volumes.getVolumes(teacherRequest.getTargetUnitOfMeasure());
        } catch (NoSuchElementException exception) {
            // Output expected NoSuchElementExceptions.
            return GenericResponseBody.builder().response("invalid").build();
        }
        //if (getVolumeFromString )
        try {
            val result = getVolumeFromString.convert(teacherRequest.getInputNumericalValue(), targetVolume);
            val authoritativeAnswer = temperatureService.roundToTenths(result);
            val studentResponse = temperatureService.roundToTenths(Double.parseDouble(teacherRequest.getStudentResponse()));

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

}
