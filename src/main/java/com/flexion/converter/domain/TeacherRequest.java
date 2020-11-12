package com.flexion.converter.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class TeacherRequest {
private Double inputNumericalValue;
private String inputUnitOfMeasure;
private String targetUnitOfMeasure;
private String studentResponse;

}
