package br.com.credentialskol.config;

import br.com.credentialskol.controller.CredentialController;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.RequestHandlerSelectors.basePackage;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private static final String SWAGGER_API_VERSION = "1.0.0";
    private static final String LICENSE_TEXT = "MIT";
    private static final String title = "br.com.bancoKol";
    private static final String description = "gerenciador de credencial do banco kol";

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(title)
                .description(description)
                .version(SWAGGER_API_VERSION)
                .license(LICENSE_TEXT)
                .contact(new Contact("SCTO Innovations", "www.scto.com", "scto@scto.com"))
                .build();
    }

    @Value("${app.version}")
    private String appVersion;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(this.apiInfo())
                .enable(true)
                .select()
                .paths(PathSelectors.any())
                .apis(basePackage(CredentialController.class.getPackage().getName()))
                .build();
    }
}
