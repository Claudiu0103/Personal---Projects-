import React, { useEffect, useState } from 'react';
import '../Styles/CarHistory.css';

function CarHistory() {
    const [payments, setPayments] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    const userId = localStorage.getItem('idUser');

    useEffect(() => {
        if (!userId) {
            setError("User ID nu este disponibil.");
            setLoading(false);
            return;
        }

        fetch(`http://localhost:8080/api/payment/user/${userId}`)
            .then((response) => {
                if (!response.ok) {
                    throw new Error("Eroare la încărcarea plăților.");
                }
                return response.json();
            })
            .then((data) => {
                setPayments(data);
            })
            .catch((error) => {
                console.error("Eroare:", error);
                setError(error.message);
            })
            .finally(() => {
                setLoading(false);
            });
    }, [userId]);

    if (loading) {
        return React.createElement('p', { className: 'message loading' }, 'Se încarcă istoricul...');
    }

    if (error) {
        return React.createElement('p', { className: 'message error' }, `Eroare: ${error}`);
    }

    const paymentItems = payments.map((payment) =>
        React.createElement(
            'div',
            { key: payment.idPayment, className: 'payment-item' },
            React.createElement('p', null, `ID Plată: ${payment.idPayment}`),
            React.createElement('p', null, `Adresa de livrare: ${payment.shippingAddress}`),
            React.createElement('p', null, `Data livrării: ${payment.dateOfDelivery}`),
            React.createElement('hr')
        )
    );

    return React.createElement(
        'div',
        { className: 'car-history-container' },
        React.createElement('h2', { className: 'car-history-header' }, 'Istoric Comenzi'),
        React.createElement(
            'div',
            { className: 'payment-list' },
            paymentItems.length > 0 ? paymentItems : React.createElement('p', null, 'Nu există plăți înregistrate.')
        )
    );
}

export default CarHistory;
