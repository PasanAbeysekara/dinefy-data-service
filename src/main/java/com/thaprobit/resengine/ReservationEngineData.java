package com.thaprobit.resengine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author Tharinda Wickramaarachchi
 */
@SpringBootApplication(scanBasePackages={
        "com.thaprobit.resengine.controller",
        "com.thaprobit.resengine.repo",
        "com.thaprobit.resengine.app.config",
        "com.thaprobit.resengine.messaging"
})
@EnableScheduling
@EnableAsync
public class ReservationEngineData {
    public static void main(String[] args) {
        SpringApplication.run(ReservationEngineData.class, args);
    }
}
