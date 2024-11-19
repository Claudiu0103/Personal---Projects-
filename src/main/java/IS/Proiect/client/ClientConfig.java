package IS.Proiect.client;

import IS.Proiect.car.Car;
import IS.Proiect.user.User;
import IS.Proiect.user.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@Configuration
public class ClientConfig {
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);
    private UserRepository userRepository;
    @Bean
    CommandLineRunner commandLineRunner3(ClientRepository repository, UserRepository userRepository) {
        return args -> {
            String rawPassword = "1234567v";
            String encodedPassword = passwordEncoder.encode(rawPassword);
            User user = new User(3,"vlad28",encodedPassword,"Client");
            userRepository.save(user);
            Client vlad  = new Client(1, "Vlad", "Popescu", "0726644649", "Constantin Noica Nr 2", "vladpopescu@yahoo.com",user);
            repository.saveAll(List.of(vlad));
        };
    }
}
