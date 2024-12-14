package IS.Proiect.payment;

import IS.Proiect.car.Car;
import IS.Proiect.client.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/payment")
@CrossOrigin(origins = "http://localhost:3000")
public class PaymentController {

    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }
    @GetMapping
    public ResponseEntity<List<Payment>> getAllPayments() {
        List<Payment> payments = paymentService.getAllPayments();
        if (payments.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(payments);
    }
//    @GetMapping(path = "{idUser}")
//    public Payment getPayment(@PathVariable int idUser) {
//        return paymentService.getClient(idUser);
//    }

    @PostMapping(path = "{idPayment}")
    public void registerNewPayment(@RequestBody Payment payment) {
        paymentService.addNewPayment(payment);
    }

    @GetMapping(path = "/user/{idUser}")
    public ResponseEntity<List<Payment>> getPaymentsByClientId(@PathVariable Integer idUser) {
        try {
            List<Payment> payments = paymentService.getPaymentsByUserId(idUser);
            if (payments.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(payments);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
    /*@GetMapping(path = "/user/{idUser}")
    public ResponseEntity<Payment> getPaymentByClientId(@PathVariable Integer idUser) {
        Payment payment = paymentService.getPaymentByUserId(idUser);
        return ResponseEntity.ok(payment);
    }*/
//    @DeleteMapping(path = "{idClient}")
//    public void deleteClient(@PathVariable("idClient") Integer id) {
//        clientService.deleteClient(id);
//    }

//    @PutMapping(path ="{idUser}")
//    public ResponseEntity<Client> updateClient(@PathVariable Integer idUser, @RequestBody Client updatedClient) {
//        Client client = clientService.getClient(idUser);
//        if (client == null) {
//            return ResponseEntity.notFound().build();
//        }
//        client.setFirstName(updatedClient.getFirstName());
//        client.setLastName(updatedClient.getLastName());
//        client.setEmail(updatedClient.getEmail());
//        client.setAddress(updatedClient.getAddress());
//        client.setPhone(updatedClient.getPhone());
//        clientService.save(client);
//        return ResponseEntity.ok(client);
//    }
}
