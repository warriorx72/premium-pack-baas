package com.premiumpack.web.domain.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRs {

    private String userName;
    private LocalDateTime createdAt;
    private Boolean isActive;
    private List<String> roles;
}
