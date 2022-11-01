package GFO.Spring.domain.student.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SignupRequestDto {
    private String email;
    private String name;
    private String password;
    private String duty;
    private String classnum;
}
