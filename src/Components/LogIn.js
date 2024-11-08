import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import '../Styles/LogIn.css'; // Asigură-te că acest fișier CSS există pentru stilizare

function LogIn({ setAuthenticated }) {
    // State pentru a gestiona datele de intrare ale utilizatorului
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const navigate = useNavigate(); // Hook pentru navigarea în cadrul aplicației

    // Funcția care este apelată la trimiterea formularului
    const handleSubmit = (event) => {
        event.preventDefault(); // Previne reîncărcarea paginii la trimiterea formularului
        const credentials = {
            username: username,
            password: password
        };

        // Trimiterea unei cereri `POST` la backend pentru autentificare
        fetch('http://localhost:8080/api/user/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(credentials)
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Eroare la autentificare'); // Aruncă o eroare dacă răspunsul nu este ok
                }
                return response.json(); // Transformă răspunsul în JSON
            })
            .then(data => {
                console.log('Autentificare reușită:', data);
                // Salvează token-ul de autentificare în localStorage
                localStorage.setItem('authToken', data.token);
                setAuthenticated(true); // Actualizează starea autentificării
                navigate('/'); // Redirecționează utilizatorul către pagina principală
            })
            .catch(error => {
                console.error('Eroare:', error); // Afișează eroarea în consolă
            });
    };

    // Interfața utilizator pentru autentificare
    return (
        <div className="login-container">
            <h2>Autentificare</h2>
            <p>Introdu datele necesare pentru a te autentifica:</p>
            <form onSubmit={handleSubmit}>
                <div className="form-group">
                    <label htmlFor="username">Nume utilizator sau Email:</label>
                    <input
                        type="text"
                        id="username"
                        value={username}
                        onChange={(e) => setUsername(e.target.value)}
                        placeholder="Introdu numele de utilizator sau email-ul"
                        required
                    />
                </div>
                <div className="form-group">
                    <label htmlFor="password">Parolă:</label>
                    <input
                        type="password"
                        id="password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        placeholder="Introdu parola"
                        required
                    />
                </div>
                <button type="submit" className="login-button">Autentificare</button>
            </form>
        </div>
    );
}

export default LogIn;
