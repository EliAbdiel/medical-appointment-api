package xyz.eliabdiel.model.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class JwtAuthResponse {

    private String accessToken;
    private final String tokenType = "Bearer";
}