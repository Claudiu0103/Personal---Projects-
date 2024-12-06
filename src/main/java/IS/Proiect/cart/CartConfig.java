package IS.Proiect.cart;

import IS.Proiect.car.Car;
import IS.Proiect.car.CarRepository;
import IS.Proiect.cart.Cart;
import IS.Proiect.user.User;
import IS.Proiect.user.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@Configuration
public class CartConfig {
    @Bean
    @Order(5)
    CommandLineRunner commandLineRunner6(CartRepository repository, CarRepository carRepository) {
        return args -> {
            Car car1 = carRepository.findById(1).orElseThrow(() -> new IllegalStateException("Car1 Not Found"));
            Car car2 = carRepository.findById(2).orElseThrow(() -> new IllegalStateException("Car2 Not Found"));
            Car car3 = carRepository.findById(3).orElseThrow(() -> new IllegalStateException("Car3 Not Found"));
            Car car4 = carRepository.findById(4).orElseThrow(() -> new IllegalStateException("Car4 Not Found"));
            Cart cart1 = new Cart(1, List.of(car1, car2));
            Cart cart2 = new Cart(2, List.of(car3, car4));
            repository.saveAll(List.of(cart1, cart2));
        };
    }
}
