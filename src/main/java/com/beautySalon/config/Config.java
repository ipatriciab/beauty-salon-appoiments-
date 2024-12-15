package com.beautySalon.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

@Configuration
public class Config {

    @Bean
    public ITemplateResolver templateResolver() {
        SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
        resolver.setPrefix("classpath:/templates/"); // Calea către fișierele HTML
        resolver.setSuffix(".html"); // Sufixul fișierelor (HTML)
        resolver.setTemplateMode("HTML"); // Mod de interpretare
        resolver.setCacheable(false); // Dezactivează cache-ul (util pentru dezvoltare)
        return resolver;
    }
}
