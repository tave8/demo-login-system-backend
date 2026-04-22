// package giuseppetavella.demo_login_system.services;
//
// import giuseppetavella.demo_login_system.security.TokenTools;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.crypto.password.PasswordEncoder;
//
// public class AuthService {
//
//     @Autowired
//     private TokenTools tokenTools;
//
//     @Autowired
//     private PasswordEncoder bcrypt;
//
//
//     public String checkCredentialsAndGenerateToken(LoginMandatoDTO body) throws NonTrovatoException {
//
//
//         try {
//
//             Dipendente dipendenteTrovato = dipendentiService.findByEmail(body.email());
//
//             // we compare the password coming from the request's body
//             // with the actual password found in the database
//             boolean isPasswordMatch = this.bcrypt.matches(body.password(), dipendenteTrovato.getPassword());
//
//             // se la password dell'utente corrisponde a quella che si trova
//             // nell'utente che ha questa email, vuol dire che l'utente si è loggato
//             // con successo, quindi crea il token
//             if (isPasswordMatch) {
//                 return tokenTools.generateToken(dipendenteTrovato);
//             }
//
//             else {
//                 throw new UnauthorizedException("Credenziali errate");
//             }
//
//         } catch(NonTrovatoException ex) {
//             throw  new UnauthorizedException("Credenziali errate");
//         }
//
//
//
//     }
