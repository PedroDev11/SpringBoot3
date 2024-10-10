package com.coach.springcoredemocoach.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.coach.springcoredemocoach.common.Coach;
import com.coach.springcoredemocoach.common.SwimCoach;

/* We configured as a Spring bean instaded of @Component annotation */
@Configuration
public class SportConfig {

    /* Config the bean manually, this returns a instances from SwimCoach and the bean id defauls 
    to the method name to inject to our controller or we can give a bean id with @Bean("asas") */
    @Bean("swim")
    public Coach swimCoach() {
        return new SwimCoach();
    }
}
