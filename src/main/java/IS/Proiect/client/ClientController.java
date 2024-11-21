package IS.Proiect.client;

import IS.Proiect.car.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/client")
@CrossOrigin(origins = "http://localhost:3000")
public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping(path = "{idUser}")
    public Client getClient(@PathVariable int idUser) {
        return clientService.getClient(idUser);
    }

    @PostMapping(path = "{idClient}")
    public void registerNewCar(@RequestBody Client client) {
        clientService.addNewClient(client);
    }

    @DeleteMapping(path = "{idClient}")
    public void deleteClient(@PathVariable("idClient") Integer id) {
        clientService.deleteClient(id);
    }

    @PutMapping(path ="{idUser}")
    public ResponseEntity<Client> updateClient(@PathVariable Integer idUser, @RequestBody Client updatedClient) {
        Client client = clientService.getClient(idUser);
        if (client == null) {
            return ResponseEntity.notFound().build();
        }
        client.setFirstName(updatedClient.getFirstName());
        client.setLastName(updatedClient.getLastName());
        client.setEmail(updatedClient.getEmail());
        client.setAddress(updatedClient.getAddress());
        client.setPhone(updatedClient.getPhone());
        clientService.save(client);
        return ResponseEntity.ok(client);
    }
}
