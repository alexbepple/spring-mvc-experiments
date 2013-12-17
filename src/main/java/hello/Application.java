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
    public GreetingService greetingService() {
        return new GreetingService() {
            @Override
            public String greet(String name) {
                return String.format("Hello, %s!",name);
            }
        };
    }

	@Bean
	public DictatorDetector dictatorDetector() {
		return new DictatorDetector() {
			@Override
			public boolean isDictator(String name) {
				return false;
			}
		};
	}

	@Bean LogService logService() {
		return new LogService() {
			@Override
			public void logWhatever(String whatever) {
				System.out.println(whatever);
			}
		};
	}

}
