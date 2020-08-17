package com.budget.manager.config;

import com.budget.manager.shared.type.ItemCategory;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringToEnumConverter());
    }

    // credits:
    // https://www.baeldung.com/spring-enum-request-param
    public static class StringToEnumConverter implements Converter<String, ItemCategory> {
        @Override
        public ItemCategory convert(String source) {
            return ItemCategory.valueOf(source.toUpperCase());
        }
    }

}
