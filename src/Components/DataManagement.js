import React, { useEffect, useState } from 'react';

function DataManagement() {
    // Starea pentru datele utilizatorului
    const [userData, setUserData] = useState({
        nume: '',
        prenume: '',
        email: '',
        adresa: '',
        telefon: ''
    });

    // Utilizați useEffect pentru a încărca datele utilizatorului (exemplu de date fictive)
    useEffect(() => {
        // Aceasta ar putea fi înlocuită cu o cerere API pentru a obține datele reale
        const fetchUserData = () => {
            // Exemplar de date utilizator
            const data = {
                nume: 'Popescu',
                prenume: 'Ion',
                email: 'ion.popescu@example.com',
                adresa: 'Strada Exemplu, Nr. 10, București',
                telefon: '0712345678'
            };
            setUserData(data);
        };
        fetchUserData();
    }, []);

    return (
        <div>
            <h2>Date Personale</h2>
            <p>Nume: {userData.nume}</p>
            <p>Prenume: {userData.prenume}</p>
            <p>Email: {userData.email}</p>
            <p>Adresă: {userData.adresa}</p>
            <p>Număr de telefon: {userData.telefon}</p>
        </div>
    );
}

export default DataManagement;
