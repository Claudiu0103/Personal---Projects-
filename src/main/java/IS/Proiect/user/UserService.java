package IS.Proiect.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class UserService {
    @Autowired
    private final UserRepository userRepository;

    private  BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }


    public void registerUser(String username, String rawPassword) {
        String encryptedPassword = passwordEncoder.encode(rawPassword); // Criptează parola
        User user = new User(username, encryptedPassword,"Client");
        userRepository.save(user);
    }

    public void deleteUser(Integer id) {
        boolean exists = userRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("User with id " + id + " doesn't exists");
        }
        userRepository.deleteById(id);
    }
    public User authenticate(String username, String password) {
        User user = userRepository.findByUsername(username);
        System.out.println(username);
        System.out.println(user);
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            return user; // Returnează utilizatorul dacă autentificarea este reușită
        } else {
            return null; // Returnează null dacă autentificarea eșuează
        }
    }
    @Transactional
    public void updateUser(Integer id,String password) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalStateException("User with id " + id + "doesn't exists"));
        if (password != null && !Objects.equals(user.getPassword(), password)) {
            if (user.getPassword().length() < 5) {
                throw new IllegalStateException("Invalid Password");
            }
            user.setPassword(password);
        }
    }
}
