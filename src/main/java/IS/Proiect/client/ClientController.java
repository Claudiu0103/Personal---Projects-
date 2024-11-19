package IS.Proiect.client;

import IS.Proiect.car.Car;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping
    public List<Client> getClients() {
        return clientService.getClients();
    }

    @PostMapping
    public void registerNewCar(@RequestBody Client client) {
        clientService.addNewClient(client);
    }

    @DeleteMapping(path = "{idClient}")
    public void deleteClient(@PathVariable("idClient") Integer id) {
        clientService.deleteClient(id);
    }

    @PutMapping(path = "{idClient}")
    public void updateCar(@PathVariable("idClient") Integer id, @RequestParam(required = false) String firstName) {
        clientService.updateClient(id, firstName);

    }
}
