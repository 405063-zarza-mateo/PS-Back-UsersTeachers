package org.utn.tup.ps.Dto.Login;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PasswordChangeDto {
    private String currentPassword;
    private String newPassword;
}
