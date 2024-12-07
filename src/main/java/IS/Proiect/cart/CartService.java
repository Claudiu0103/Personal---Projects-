package IS.Proiect.cart;

import IS.Proiect.car.Car;
import IS.Proiect.car.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {
    @Autowired
    private final CartRepository cartRepository;
    private final CarRepository carRepository;

    public CartService(CartRepository cartRepository, CarRepository carRepository) {
        this.cartRepository = cartRepository;
        this.carRepository = carRepository;
    }

    public List<Cart> getCarts() {
        return cartRepository.findAll();
    }

    public Cart addNewCart(Cart cart) {
        cartRepository.save(cart);
        return cart;
    }

    public void deleteCart(Integer id) {
        boolean exists = cartRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("Cart with id " + id + " doesn't exists");
        }
        cartRepository.deleteById(id);
    }

    public void updateCart(Integer id, Cart updatedcart) {
        Cart cart = cartRepository.findById(id).orElseThrow(() -> new IllegalStateException("cart with id " + id + " doesn't exist"));
        cartRepository.save(cart);
    }

    public List<Car> getCarsFromCart(Integer idCart) {
        Cart cart = cartRepository.findById(idCart)
                .orElseThrow(() -> new IllegalStateException("Cart not found for ID: " + idCart));

        return cart.getCars();
    }

    public void addCarToCart(Integer cartId, Integer carId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new IllegalStateException("Cart not found"));
        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new IllegalStateException("Car not found"));
        cart.addCar(car);
        cartRepository.save(cart);
    }

    public void removeCarFromCart(Integer cartId, Integer carId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new IllegalArgumentException("Coșul nu a fost găsit"));
        Car carToRemove = cart.getCars().stream()
                .filter(car -> car.getIdCar().equals(carId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Mașina nu a fost găsită în coș"));
        cart.getCars().remove(carToRemove);
        System.out.println(cart.getCars());
        cartRepository.save(cart);
    }
}
