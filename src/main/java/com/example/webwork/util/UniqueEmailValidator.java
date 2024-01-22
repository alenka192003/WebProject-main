package com.example.webwork.util;

        import com.example.webwork.repo.UsersRepository;
        import com.example.webwork.util.UniqueEmail;
        import jakarta.validation.ConstraintValidator;
        import jakarta.validation.ConstraintValidatorContext;
        import org.springframework.beans.factory.annotation.Autowired;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    private UsersRepository usersRepository;

    public UniqueEmailValidator() {
    }

    @Autowired
    public void setUsersRepository(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }


    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {

        return usersRepository.findByEmail(email).isEmpty();
    }
}
