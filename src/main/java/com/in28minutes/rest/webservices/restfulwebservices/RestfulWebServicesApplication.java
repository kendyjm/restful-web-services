package com.in28minutes.rest.webservices.restfulwebservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@SpringBootApplication
public class RestfulWebServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestfulWebServicesApplication.class, args);
	}


	/* INTERNATIONALIZATION - START */
	/**
	 * If no Accept-Language header found, fallback to the defined default locale (US)
	 * @return AcceptHeader LocaleResolver
	 */
	@Bean
	public LocaleResolver localeResolver() {
		// AcceptHeaderLocaleResolver is a LocaleResolver implementation that simply uses the primary locale specified in the "accept-language" header of the HTTP request
		// (that is, the locale sent by the client browser, normally that of the client's OS).
		AcceptHeaderLocaleResolver localeResolver = new AcceptHeaderLocaleResolver();
		localeResolver.setDefaultLocale(Locale.US);
		return localeResolver;
	}

	/**
	 * REPLACED BY spring.messages.basename=messages
	 * Where to find the messages.properties
	 * See resouces/<basename>[_locale].properties
	 * @return
	 */
	/*public  ResourceBundleMessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("messages");
		return messageSource;
	}*/
	/* INTERNATIONALIZATION - END */
}
