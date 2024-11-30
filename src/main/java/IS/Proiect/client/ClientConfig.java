package IS.Proiect.client;

import IS.Proiect.car.Car;
import IS.Proiect.user.User;
import IS.Proiect.user.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@Configuration
public class ClientConfig {
    private UserRepository userRepository;
    @Bean
    @Order(5)
    CommandLineRunner commandLineRunner3(ClientRepository repository, UserRepository userRepository) {
        return args -> {
            User user = userRepository.findById(3).orElseThrow(() -> new IllegalStateException("User not found"));
            User user2 = userRepository.findById(4).orElseThrow(() -> new IllegalStateException("User not found"));
            Client vlad  = new Client(1, "Vlad", "Popescu", "0726644649", "Constantin Noica Nr 2", "vladpopescu@yahoo.com",user);
            Client dan  = new Client(2, "Dan", "Stefanescu", "0726644649", "Constantin Noica Nr 2", "vladpopescu@yahoo.com",user2);
            repository.saveAll(List.of(vlad,dan));
        };
    }
}
