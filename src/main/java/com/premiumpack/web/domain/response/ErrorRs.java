package com.premiumpack.web.domain.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"code", "uuid", "path", "timestamp", "data"})
public class ErrorRs {

    private String code;
    private String UUID;
    private String path;
    private OffsetDateTime timestamp;
    private String message;

}
