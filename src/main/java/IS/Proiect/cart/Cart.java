package IS.Proiect.cart;

import IS.Proiect.car.Car;
import IS.Proiect.client.Client;
import IS.Proiect.payment.Payment;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class Cart {
    @Id
    @SequenceGenerator(
            name = "cart_sequence",
            sequenceName = "cart_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "cart_sequence"
    )
    private Integer idCart;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "cart_id")
    private List<Car> cars = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "idClient")
    @JsonBackReference
    private Client client;

    @OneToOne(mappedBy = "cart", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Payment payment;

    public Cart() {
    }

    public Cart(Integer idCart, List<Car> cars) {
        this.idCart = idCart;
        this.cars = cars;
    }

    public Integer getIdCart() {
        return idCart;
    }

    public void setIdCart(Integer idCart) {
        this.idCart = idCart;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public void addCar(Car car) {
        this.cars.add(car);
    }

    public void removeCar(Car car) {
        this.cars.remove(car);
    }
    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "idCart=" + idCart +
                ", cars=" + cars +
                '}';
    }
}
