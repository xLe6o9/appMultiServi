package xfactor.org.app.controllers;

import org.apache.tomcat.util.http.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api")
public class mainController {

    @Autowired
    private SessionRegistry sessionRegistry;

    @GetMapping("/")
    public String index() {
        return "Bienvenido a la aplicación .... ";
    }

    @GetMapping("/Home")
    public String index2() {
        return "Bienvenido a la Home .... ";
    }

//    ==========================================================================================================
//  PROBANDO CONTROLADOR PARA [SRPING SECURITY - LOGIN]
    @GetMapping("/session")
    public ResponseEntity<?> getDetailSession() {
        String sessionID = "";
        User userObjt = null;
        List<Object> sessions = sessionRegistry.getAllPrincipals();
        Map<String, Object> response = null;

        for (Object session : sessions) {
            if (session instanceof User) {
                userObjt = (User) session;
            }
            List<SessionInformation> sessionInformations = sessionRegistry.getAllSessions(session, false);
            for (SessionInformation sessionInformation : sessionInformations) {
                sessionID = sessionInformation.getSessionId();
            } // AHORA RECUPERANDO LAS COSAS JSON
            response = new HashMap<>();
            response.put("response", "Hello World");
            response.put("sessionID", sessionID);
            response.put("sessionUser", userObjt);
        }
        return ResponseEntity.ok(response);
    }

//    ==========================================================================================================
//  PROBANDO CONTROLADOR PARA Control de ERRORES y EXCEPPCIONES
    @GetMapping(value = "/throwException")
    public void throwException() {
        throw new IllegalArgumentException("Soy un Mensaje de Error, que proviene del controlador.");
    }

//    ==========================================================================================================
//  PROBANDO CONTROLADOR PARA

}
