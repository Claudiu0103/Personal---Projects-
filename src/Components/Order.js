import React from 'react';
import {useNavigate} from 'react-router-dom';

function Order() {
    const handleGoHome = () => {
        navigate('/');
    };
    const navigate = useNavigate();

    return (
        <div>
            <h2>Comanda ta</h2>
            <p>Aici poți găsi informatii despre comanda ta</p>
            <button onClick={handleGoHome}>Inapoi la pagina prinncipala</button>
        </div>
    );
}

export default Order;