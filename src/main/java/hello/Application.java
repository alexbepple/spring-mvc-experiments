package hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@ComponentScan
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public EndearmentService endearmentService() {
        return new EndearmentService() {
            @Override
            public String decorate(String name) {
                return "dear " + name;
            }
        };
    }

    @Bean
    public DictatorDetector dictatorDetector() {
        return new DictatorDetector() {
            @Override
            public boolean isDictator(String name) {
                return name.contains("Adolf");
            }
        };
    }
}
