package IS.Proiect.showroom;

import IS.Proiect.car.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/showroom")
@CrossOrigin(origins = "http://localhost:3000")
public class ShowroomController {

    private final ShowroomService showroomService;

    @Autowired
    public ShowroomController(ShowroomService showroomService) {
        this.showroomService = showroomService;
    }

    @GetMapping
    public List<Showroom> getShowrooms() {
        return showroomService.getShowrooms();
    }

    @PostMapping
    public ResponseEntity<Showroom> registerNewShowroom(@RequestBody Showroom showroom) {
        try {
            Showroom savedShowroom = showroomService.addNewShowroom(showroom);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedShowroom);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }    }

    @DeleteMapping(path = "{idShowroom}")
    public void deleteShowroom(@PathVariable("idShowroom") Integer id) {
        showroomService.deleteShowroom(id);
    }

    @PutMapping(path = "{idShowroom}")
    public Showroom updateShowroom(
            @PathVariable("idShowroom") Integer id,
            @RequestBody Showroom updatedShowroom) {
        return showroomService.updateShowroom(id, updatedShowroom);
    }

}
