package GFO.Spring.domain.email.service;

public interface EmailAuthService {
    void execute(String email, String authKey);
}
