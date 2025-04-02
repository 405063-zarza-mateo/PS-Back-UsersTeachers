package org.utn.tup.ps.Dto.Login;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.utn.tup.ps.Enum.UserRole;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponseDto {
    private String token;
    private String tokenType = "Bearer";
    private String email;
    private String role;
}
