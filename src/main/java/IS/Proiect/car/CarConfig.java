package IS.Proiect.car;


import IS.Proiect.showroom.Showroom;
import IS.Proiect.admin.Admin;
import IS.Proiect.user.User;
import IS.Proiect.user.UserRepository;
import IS.Proiect.admin.AdminRepository;
import IS.Proiect.showroom.ShowroomRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@Configuration
public class CarConfig {
    @Bean
    @Order(4)
    CommandLineRunner commandLineRunner(CarRepository repository, UserRepository userRepository, AdminRepository adminRepository, ShowroomRepository showroomRepository) {
        return args -> {
            Showroom showroom = showroomRepository.findById(1).orElseThrow(() -> new IllegalStateException("Showroom not found"));
            Car Audi = new Car(1, 260000, "14-11-2014", "Audi A5", "Sedan", 14000, "White", showroom);
            Car BMW = new Car(2, 280000, "21-05-2010", "BWM 3 Series ", "Sedan", 12000, "Black", showroom);
            Car Mercedes = new Car(3, 300000, "10-03-2012", "Mercedes C-Class", "Sedan", 16000, "Black", showroom);
            Car Volkswagen = new Car(4, 250000, "15-07-2016", "Volkswagen Passat", "Sedan", 12000, "Blue", showroom);
            Car Ford = new Car(5, 220000, "20-10-2018", "Ford Mondeo", "Hatchback", 10000, "Red", showroom);
            repository.saveAll(List.of(Audi, BMW, Mercedes, Volkswagen, Ford));
        };
    }
}
