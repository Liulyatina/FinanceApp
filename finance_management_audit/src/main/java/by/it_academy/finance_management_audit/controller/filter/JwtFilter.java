package by.it_academy.finance_management_audit.controller.filter;

import by.it_academy.finance_management_audit.controller.utils.JwtTokenHandler;
import by.it_academy.finance_management_audit.controller.utils.UserDetailsImpl;
import by.it_academy.finance_management_audit.core.enums.UserRole;
import by.it_academy.finance_management_audit.service.feign.api.UserClientFeign;
import by.it_academy.finance_management_audit.service.feign.dto.UserDTO;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

import static org.apache.logging.log4j.util.Strings.isEmpty;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtTokenHandler jwtHandler;

    private final UserClientFeign userClientFeign;


    public JwtFilter(JwtTokenHandler jwtHandler, UserClientFeign userClientFeign) {
        this.jwtHandler = jwtHandler;
        this.userClientFeign = userClientFeign;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain)
            throws ServletException, IOException {

        final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (isEmpty(header) || !header.startsWith("Bearer ")) {
            chain.doFilter(request, response);
            return;
        }

        // Get jwt token and validate
        final String token = header.split(" ")[1].trim();
        if (!jwtHandler.validate(token)) {
            chain.doFilter(request, response);
            return;
        }

        String newToken = jwtHandler.generateAccessToken(jwtHandler.getUsername(token),
                UserRole.ADMIN);

        ResponseEntity<UserDTO> userDTOResponse = userClientFeign.
                aboutUser("Bearer "+newToken);

        if (userDTOResponse==null|| userDTOResponse.getBody()==null||
                !userDTOResponse.getStatusCode().equals(HttpStatusCode.valueOf(200))){
            chain.doFilter(request, response);
            return;
        }


        UserDetailsImpl userDetails = new UserDetailsImpl(userDTOResponse.getBody(), header);

        UsernamePasswordAuthenticationToken
                authentication = new UsernamePasswordAuthenticationToken(
                userDetails, null,
                userDetails == null ?
                        List.of() : userDetails.getAuthorities()
        );

        authentication.setDetails(
                new WebAuthenticationDetailsSource().buildDetails(request)
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
    }
}