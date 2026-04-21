package giuseppetavella.demo_login_system.controllers;


import giuseppetavella.demo_login_system.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private DipendentiService dipendentiService;


    @PostMapping("/login")
    public LoginDaMandareDTO login(@RequestBody @Validated LoginMandatoDTO body) {
        String accessToken = authService.checkCredentialsAndGenerateToken(body);
        return new LoginDaMandareDTO(accessToken);
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public NuovoDipendenteDaMandareDTO register(@RequestBody @Validated NuovoDipendenteMandatoDTO body,
                                                BindingResult validation) {

        if (validation.hasErrors()) {
            List<String> errors = validation.getFieldErrors().stream().map(error -> error.getDefaultMessage()).toList();
            throw new ValidazionePayloadException(errors);
        }

        Dipendente nuovoDipendente = this.dipendentiService.aggiungiNuovoDipendente(body);

        return new NuovoDipendenteDaMandareDTO(nuovoDipendente);

    }

}