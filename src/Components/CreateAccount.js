import React, { useState } from 'react';
import {useNavigate} from "react-router-dom";

function Register({setAuthenticated,setUserRole}) {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');

    const navigate = useNavigate();
    const handleRegister = (event) => {
        event.preventDefault();

        fetch('http://localhost:8080/api/register', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ username, password })
        })
            .then(response => {
                if (response.ok) {
                    navigate('/');
                    setAuthenticated(true);
                    localStorage.setItem('isAuthenticated', 'true');
                    localStorage.setItem('userRole', "Client");
                    setUserRole("Client");
                } else {
                    alert('A apărut o problemă la înregistrare.');
                }
            })
            .catch(error => {
                console.error('Eroare:', error);
                alert('Eroare la conexiunea cu serverul.');
            });
    };

    return (
        <div className="register-container">
            <h2>Înregistrare</h2>
            <form onSubmit={handleRegister}>
                <div className="form-group">
                    <label htmlFor="username">Nume Utilizator:</label>
                    <input
                        type="text"
                        id="username"
                        value={username}
                        onChange={(e) => setUsername(e.target.value)}
                        placeholder="Introdu numele de utilizator"
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
                <button type="submit" className="register-button">Înregistrează-te</button>
            </form>
        </div>
    );
}

export default Register;
