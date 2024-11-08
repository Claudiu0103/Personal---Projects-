import React from 'react';

function CarItem({ car }) {
    return (
        <div className="car-item">
            <div className="car-image">
                {/* Afișează imaginea doar dacă imageUrl este disponibil */}
                {car.imageUrl ? (
                    <img src={car.imageUrl} alt={car.model} />
                ) : (
                    <p>Imagine indisponibilă</p>
                )}
            </div>
            <div className="car-info">
                <h3>ID: {car.idCar || "N/A"}</h3>
                <p>Kilometers: {car.kilometers}</p>
                <p>Release Date: {car.releaseDate}</p>
                <p>Model: {car.model}</p>
                <p>Vehicle Type: {car.vehicleType}</p>
                <p>Price: {car.price} €</p>
                <p>Color: {car.color}</p>
            </div>
        </div>
    );
}

export default CarItem;
