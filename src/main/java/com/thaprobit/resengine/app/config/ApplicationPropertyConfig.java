package com.thaprobit.resengine.app.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author Tharinda Wickramaarachchi
 * @since 6/9/2020 12:21 AM
 */
@Configuration
@PropertySource("classpath:application.properties")
@PropertySource("classpath:thread.properties")
@PropertySource("classpath:system.properties")
public class ApplicationPropertyConfig
{
	@Value("${sys.contract.bookable_horizon}")
	public Short BOOKABLE_HORIZON;
}
