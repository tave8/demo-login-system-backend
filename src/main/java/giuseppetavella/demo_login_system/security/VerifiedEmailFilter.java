package giuseppetavella.demo_login_system.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@Order(2)
public class VerifiedEmailFilter extends OncePerRequestFilter {
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // System.out.println("CALLED FILTER: VERIFIED EMAIL FILTER");
        
        filterChain.doFilter(request, response);
    }

    /**
     * This filter does NOT apply only to the / endpoint (root).
     */
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        AntPathMatcher matcher = new AntPathMatcher();
        String path = request.getServletPath();

        // the /auth/** endpoint is excluded from the filter
        // boolean isAuthPath = matcher.match("/auth/**", path);
        // the / endpoint is excluded from the filter
        boolean isRoot = matcher.match("/", path);

        return isRoot;
    }
    
}
