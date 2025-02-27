package xfactor.org.app.aop.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundException extends Exception {

  private static final String DESCRIPTION = "Not Found Exception (404): ";

    public NotFoundException(String message) {
        super(DESCRIPTION + message);
    }

}
