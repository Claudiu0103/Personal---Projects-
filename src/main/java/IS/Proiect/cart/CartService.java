package IS.Proiect.cart;

import IS.Proiect.car.Car;
import IS.Proiect.car.CarRepository;
import IS.Proiect.client.Client;
import IS.Proiect.client.ClientRepository;
import IS.Proiect.payment.Payment;
import IS.Proiect.payment.PaymentRepository;
import IS.Proiect.payment.PaymentRequest;
import IS.Proiect.payment.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {
    @Autowired
    private final CartRepository cartRepository;
    private final CarRepository carRepository;
    private final ClientRepository clientRepository;
    private final PaymentRepository paymentRepository;
    @Autowired
    private PaymentService paymentService;

    public CartService(CartRepository cartRepository, CarRepository carRepository, PaymentRepository paymentRepository, PaymentService paymentService, ClientRepository clientRepository) {
        this.cartRepository = cartRepository;
        this.carRepository = carRepository;
        this.paymentRepository = paymentRepository;
        this.paymentService = paymentService;
        this.clientRepository = clientRepository;
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

    public Cart createNewCartForClient(Integer clientId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new IllegalArgumentException("Clientul cu ID-ul " + clientId + " nu există."));
        Cart newCart = new Cart();
        newCart.setClient(client);
        return cartRepository.save(newCart);
    }

    public void clearCart(Integer cartId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new IllegalStateException("Cart not found"));

        cart.getCars().clear();
        cartRepository.save(cart);
    }

    public Payment createPayment(Integer cartId, PaymentRequest paymentRequest) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new IllegalArgumentException("Coșul nu există!"));
        if (cart.getPayment() != null) {
            throw new IllegalArgumentException("Coșul are deja o plată asociată!");
        }
        Payment payment = new Payment();
        payment.setCart(cart);

        payment.setShippingAddress(paymentRequest.getShippingAddress());
        payment.setCardNumber(paymentRequest.getCardNumber());
        payment.setCvv(paymentRequest.getCvv());
        payment.setExpiryDate(paymentRequest.getExpiryDate());
        payment.setCardHolderName(paymentRequest.getCardHolderName());
        payment.setDateOfDelivery(paymentService.calculateDeliveryDate());

        paymentRepository.save(payment);
        cart.setPayment(payment);
        cartRepository.save(cart);
        return payment;
    }
}
