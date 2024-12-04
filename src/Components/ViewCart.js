import React from 'react';
import { useNavigate } from 'react-router-dom';

function ViewCart() {
    const handleGoToOrder = () => {
        navigate('/order');
    };
    const navigate = useNavigate();
    return (
        <div>
            <h2>Vezi Cosul</h2>
            <p>Aici poți găsi date despre cosul tau</p>
            <button onClick={handleGoToOrder}>Comanda</button>
        </div>
    );
}

export default ViewCart;