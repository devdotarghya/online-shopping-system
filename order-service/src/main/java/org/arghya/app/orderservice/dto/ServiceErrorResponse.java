package org.arghya.app.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.bind.annotation.ControllerAdvice;

@Data
@AllArgsConstructor
@Builder
public class ServiceErrorResponse {
    private String errorMessage;
}
