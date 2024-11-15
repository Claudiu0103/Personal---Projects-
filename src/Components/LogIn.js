import React, {useState} from 'react';
import {useNavigate} from 'react-router-dom';
import '../Styles/LogIn.css';

function LogIn({setAuthenticated,setUserRole}) {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');

    const navigate = useNavigate();

    const handleSubmit = (event) => {
        event.preventDefault();
        fetch('http://localhost:8080/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            },
            body: new URLSearchParams({username, password}),
            credentials: 'include'
        })
            .then(response => {
                if (response.ok) {
                    navigate('/');
                    setAuthenticated(true);
                    response.json().then(data => {
                        setUserRole(data.userRole);
                    });
                } else {
                    throw new Error('Eroare la autentificare');
                }
            })
            .catch(error => {
                console.error('Eroare:', error);
                alert('Autentificare eșuată. Te rog să verifici datele introduse.');
            });
    }

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
