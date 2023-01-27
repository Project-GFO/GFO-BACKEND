package GFO.Spring.domain.email.presentation.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import javax.validation.constraints.Email;

@Getter
public class EmailDto {
    @Email
    private final String email;

    @JsonCreator
    public EmailDto(@JsonProperty("email") String email){
        this.email = email;
    }
}
