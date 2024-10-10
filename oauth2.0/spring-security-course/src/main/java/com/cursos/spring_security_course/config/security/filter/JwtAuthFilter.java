package com.cursos.spring_security_course.config.security.filter;

import com.cursos.spring_security_course.entity.User;
import com.cursos.spring_security_course.repository.UserRepository;
import com.cursos.spring_security_course.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

// OncePerRequestFilter, se asegura que una petición solo sea procecada una sola vez, si llega una peticion
// repetida, entonces ya no la procesa
// Para que nuestro filtro se ejecute, lo tenemos que registrar en el SecurityFilterChain que
// se encuentra en nuestra clase (HttpSecurityConfig)
@Component
public class JwtAuthFilter extends OncePerRequestFilter {
    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 1.- Obtener el Header que contiene el token
        String authHeader = request.getHeader("Authorization");

        // 1.1.- Validando que se envie el token mediante el header
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            // Tambien necesitamos que pase por el Authorization filter para ver si este endpoint es
            // publico, si es publico no necesitamos mandar el Authorization con el jwt
            filterChain.doFilter(request, response);

            // Retornamos el control, que se salga del método y que no ejecute el codigo de abajo
            return;
        }

        // 2.- Obtener el jwt
        // split genera un array de Strings en base a un punto de separacion, en este caso un espacio
        // vacio debido a que en el Header viene separado el tipo Bearer con el token jwt. y accedemos
        // a la posicion 1 que es donde se encuentra el token.
        String jwt = authHeader.split(" ")[1];

        // 3.- Obtener el subject/username desde el jwt
        String username = jwtService.extractSubject(jwt);

        // 3.1.- Obtener los authorities del usuario, .get() para que no mande esa exception
        Optional<User> userOptional = userRepository.findByUsername(username);
        User user;

        if (userOptional.isPresent()) {
            user = userOptional.get();
        } else {
            throw new RuntimeException("User not found in method JwtAuthFilter");
        }

        // 4.- Setear un objeto Authentication dentro del SecurityContext
        // Credentials = null, Las credenciales solo son necesarias cuando mandamos a llamar el metodo
        // authenticate del AuthenticationManager y eso se hizo cuando generamos el token en el login.
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                username, null, user.getAuthorities()
        );

        SecurityContextHolder.getContext().setAuthentication(authToken);

        // 5.- Ejecutar el resto de filtros
        filterChain.doFilter(request, response);
    }
}
