package com.thaprobit.resengine.app.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @author Tharinda Wickramaarachchi
 * @since 6/8/2020 11:04 PM
 */
@Configuration
@Slf4j
public class ApplicationThreadConfig {
    @Bean
    public TaskExecutor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(4);
        executor.setMaxPoolSize(4);
        executor.setThreadNamePrefix("Avail-Flattener-");
        executor.initialize();
        return executor;
    }

    @Bean("async-avail-data")
    public AsyncTaskExecutor taskExecutor() {
        SimpleAsyncTaskExecutor t = new SimpleAsyncTaskExecutor("Avail-Flattener-");
        t.setConcurrencyLimit(100);

        log.info("SimpleAsyncTaskExecutor for \"Avail-Flattener\" created with 100 concurrency limit");
        return t;
    }
}
