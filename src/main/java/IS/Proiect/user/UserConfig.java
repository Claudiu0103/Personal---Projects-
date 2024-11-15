package IS.Proiect.user;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class UserConfig {
    @Bean
    CommandLineRunner commandLineRunnerUser2(UserRepository repository) {
        return args -> {
            User Claudiu = new User(1,"claudiu30","1234567c","Client");
            User Ana = new User(2,"ana29","1234567a","Client");
            repository.saveAll(List.of(Claudiu,Ana));
        };
    }
}
