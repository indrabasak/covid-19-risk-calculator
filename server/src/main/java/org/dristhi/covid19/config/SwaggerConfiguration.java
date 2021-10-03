package org.dristhi.covid19.config;

import java.util.LinkedList;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * {@code SwaggerConfiguration} is a configuration class for enabling swagger.
 * You can access the Swagger UI at http://&lt;host&gt;:&lt;port&gt;/swagger-ui
 * <p>
 *
 * @author Indra Basak
 * @since 10/03/21
 */
@Configuration
public class SwaggerConfiguration {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        Contact contact = new Contact("Indra Basak", "", "indra.basak1@gmail.com");

        return new ApiInfo("COVID-19 Risk Calculator API",
                "Calculates COVID-19 risk",
                "1.0.0",
                "terms of service url",
                contact,
                "license",
                "license url",
                new LinkedList<>());
    }
}
