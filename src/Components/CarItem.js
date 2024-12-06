import React from 'react';
import car1 from '../assets/images/car1.jpg';
import car2 from '../assets/images/car2.jpg';
import car3 from '../assets/images/car3.jpg';
import car4 from '../assets/images/car4.jpg';
import car5 from '../assets/images/car5.jpg';

function CarItem({car, viewCar}) {
    const handleAddToCart = () => {
        console.log(car);
    }
    return (
        <div className="car-item">
            <div className="car-image">
                {/* Afișează imaginea doar dacă imageUrl este disponibil */}
                {car.imageUrl ? (
                    <img src={car.imageUrl} alt={car.model}/>
                ) : (
                    <p>Imagine indisponibilă</p>
                )}
            </div>
            <div className="car-info">
                <h3>{car.model}</h3>
                <p>Kilometers: {car.kilometers}</p>
                <p>Release Date: {car.releaseDate}</p>
                <p>Vehicle Type: {car.vehicleType}</p>
                <p>Price: {car.price} €</p>
                <p>Color: {car.color}</p>
                {viewCar && <button onClick={handleAddToCart}>Adauga in Cos</button>}
            </div>
        </div>
    );
}

export default CarItem;
