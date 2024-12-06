package IS.Proiect.cart;

import IS.Proiect.admin.Admin;
import IS.Proiect.admin.AdminService;
import IS.Proiect.car.Car;
import IS.Proiect.car.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/cart")
@CrossOrigin(origins = "http://localhost:3000")
public class CartController {

    private final CartService cartService;
    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public List<Cart> getCarts() {
        return cartService.getCarts();
    }
    @GetMapping(path = "{idCart}")
    public List<Car> getCars(@PathVariable Integer idCart) {
        return cartService.getCarsFromCart(idCart);
    }

    @PostMapping
    public ResponseEntity<Cart> registerNewCart(@RequestBody Cart cart) {
        try {
            Cart savedCart = cartService.addNewCart(cart);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedCart);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    @PostMapping("/{cartId}/add-car/{carId}")
    public ResponseEntity<?> addCarToCart(@PathVariable Integer cartId, @PathVariable Integer carId) {
        try {
            cartService.addCarToCart(cartId, carId);
            return ResponseEntity.ok("Car added to cart successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
        }
    }

    @DeleteMapping(path = "{idCart}")
    public void deleteCart(@PathVariable("idCart") Integer id) {
        cartService.deleteCart(id);
    }

    @PutMapping(path = "{idCart}")
    public ResponseEntity<Cart> updateCart(
            @PathVariable("idCart") Integer idCart,
            @RequestBody Cart updatedCart) {
        try {
            cartService.updateCart(idCart, updatedCart);

            return ResponseEntity.ok(updatedCart);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }



}
