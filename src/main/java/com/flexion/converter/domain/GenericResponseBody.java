package com.flexion.converter.domain;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
public class GenericResponseBody {
    private String response;
}
