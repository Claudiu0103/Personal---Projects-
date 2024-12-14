package IS.Proiect.payment;

import IS.Proiect.cart.Cart;
import IS.Proiect.client.Client;
import IS.Proiect.client.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final ClientRepository clientRepository;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository, ClientRepository clientRepository) {
        this.paymentRepository = paymentRepository;
        this.clientRepository = clientRepository;
    }

    // Adaugă un nou Payment
    public Payment addNewPayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    // Obține toate Payments
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    // Calculează data de livrare
    public String calculateDeliveryDate() {
        return LocalDate.now().plusWeeks(2).toString();
    }

    // Găsește toate Payments asociate coșurilor unui client
    public List<Payment> getPaymentsByUserId(Integer userId) {
        // Găsește clientul
        Client client = clientRepository.findByIdUser(userId)
                .orElseThrow(() -> new IllegalStateException("Client not found for user ID: " + userId));

        // Returnează toate payments asociate coșurilor clientului
        return client.getCarts().stream()
                .map(paymentRepository::findByCart)
                .flatMap(Optional::stream) // Filtrează Payments existente
                .collect(Collectors.toList());
    }

    // Adaugă un Payment pentru un coș specific
    public Payment addPaymentForCart(Cart cart, String shippingAddress, String deliveryDate) {
        Payment payment = new Payment(shippingAddress, cart, deliveryDate);
        return paymentRepository.save(payment);
    }
}
