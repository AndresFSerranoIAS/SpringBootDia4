package co.com.ias.SpringBootDia4.infraestructure.configuration;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DBSecret {
    private final String url;
    private final String username;
}
