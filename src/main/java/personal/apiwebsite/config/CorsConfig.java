package personal.apiwebsite.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
@EnableWebMvc
public class CorsConfig implements WebMvcConfigurer{

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:63342") // Substitua com o seu domínio ou "*" para permitir de qualquer lugar.
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Métodos HTTP permitidos.
                .allowedHeaders("*") // Headers permitidos.
                .allowCredentials(true);
    }


}
