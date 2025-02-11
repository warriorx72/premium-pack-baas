package com.premiumpack.web.domain.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SuccessRs {

    private String code;
    private Object data;
    private String message;
    private String UUID;
}
