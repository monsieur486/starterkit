package com.mr486.starterkit.configuration;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import java.util.Locale;


/**
 * The type 18 n configuration.
 */
@Configuration
public class I18nConfiguration implements WebMvcConfigurer {

  /**
   * Message source message source.
   *
   * @return the message source
   */
  @Bean("messageSource")
  public MessageSource messageSource() {
    ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
    messageSource.setBasenames("i18n/messages","i18n/webUI");
    messageSource.setDefaultEncoding("UTF-8");
    return messageSource;
  }

  /**
   * Locale resolver locale resolver.
   *
   * @return the locale resolver
   */
  @Bean
  public LocaleResolver localeResolver(){
    SessionLocaleResolver localeResolver = new SessionLocaleResolver();
    localeResolver.setDefaultLocale(Locale.FRANCE);
    return  localeResolver;
  }


  /**
   * Locale interceptor locale change interceptor.
   *
   * @return the locale change interceptor
   */
  @Bean
  public LocaleChangeInterceptor localeInterceptor() {
    LocaleChangeInterceptor localeInterceptor = new LocaleChangeInterceptor();
    localeInterceptor.setParamName("lang");
    return localeInterceptor;
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(localeInterceptor());
  }

}
