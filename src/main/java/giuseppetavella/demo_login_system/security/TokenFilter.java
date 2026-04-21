package giuseppetavella.demo_login_system.security;

import giuseppetavella.D5.entities.Dipendente;
import giuseppetavella.D5.exceptions.NonTrovatoException;
import giuseppetavella.D5.exceptions.UnauthorizedException;
import giuseppetavella.D5.services.DipendentiService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;


@Component
public class TokenFilter extends OncePerRequestFilter {

    @Autowired
    private TokenTools tokenTools;

    @Autowired
    private DipendentiService dipendentiService;


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException,
            IOException,
            UnauthorizedException
    {

        // verify that header contains token etc.
        String authHeader = request.getHeader("Authorization");
        if(authHeader == null || !authHeader.startsWith("Bearer ")) {

            // oggetto response manda degli errori 
            response.sendError(401, "Manca il token nell'authorization header, o non è nel formato corretto.");
            return;
            // throwing exceptions in the security filter 
            // does have the expected behavior, meaning that
            // the exception does not get caught by 
            // an exception handler
            // throw new UnauthorizedException("Manca il token nell'authorization header, o non è nel formato corretto.");
        }



        // we get the token
        String accessToken = authHeader.replace("Bearer ", "");

        // if token verification fails
        try {
            // verify that token is valid etc.
            tokenTools.verifyToken(accessToken);

        } catch(UnauthorizedException ex) {
            response.sendError(401, "Token non valido.");
            return;
        }


        // ******** AUTHORIZATION ************

        // 1. extract user's ID from token
        UUID dipendenteId = this.tokenTools.extractIdFromToken(accessToken);
        
        Dipendente dipendenteAttuale;
        
        // 2. find user in DB
        try {
            dipendenteAttuale = this.dipendentiService.findById(dipendenteId);
        } catch (NonTrovatoException ex) {
            response.sendError(401, "Il token era valido ma l'utente associato ad esso non esiste più.");
            return;
        }

        // 3. now we need to make this user available to the Security Context
        Authentication  authentication = new UsernamePasswordAuthenticationToken(dipendenteAttuale, null, dipendenteAttuale.getAuthorities());
        // we now set the current user of this request
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // System.out.println("do filter internal called!");

        filterChain.doFilter(request, response);

    }


    // we can specify which paths not to filter,
    // for example the paths starting with /auth   

    /**
     * In this method we specify in which cases
     * our security filter should not be called.
     * For example, we might want to disable this security filter
     * for all endpoints starting with /auth
     */
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        // TODO: experiment with a different logic:
        //    example: use "not" instead of negating with AntPathMatcher 
        return new AntPathMatcher().match("/auth/**", request.getServletPath());

    }

}