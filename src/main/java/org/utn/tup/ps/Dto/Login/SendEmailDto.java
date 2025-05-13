package org.utn.tup.ps.Dto.Login;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SendEmailDto {
    private String to;
    private String subject;
    private String body;

}
