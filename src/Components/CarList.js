import {useEffect, useState} from 'react';
import React from 'react';
import CarItem from './CarItem';
import '../Styles/CarList.css';
import car1 from '../assets/images/car1.jpg';
import car2 from '../assets/images/car2.jpg';
import car3 from '../assets/images/car3.jpg';
import car4 from '../assets/images/car4.jpg';
import car5 from '../assets/images/car5.jpg';

const carImages = [car1, car2, car3, car4, car5];

function CarList() {
    const [cars, setCars] = useState([]);

    const handleButtonClick = () => {
        console.log("Add Car button clicked");
    };


    useEffect(() => {
        fetch("http://localhost:8080/api/car")
            .then(response => {
                if (!response.ok) {
                    throw new Error("Eroare la încărcarea datelor");
                }
                return response.json();
            })
            .then(data => {
                console.log("Data received from backend:", data);
                const carsWithImages = data.map((car, index) => ({
                    ...car,
                    imageUrl: index < carImages.length ? carImages[index] : null
                }));
                setCars(carsWithImages);
            })
            .catch(error => console.error("Eroare:", error));
    }, []);

    return (
        <div>
            <h2>Mașinile Disponibile</h2>
            <div className="button-container">
                <button className="buttonAddCar" onClick={handleButtonClick}>Add Car</button>
            </div>
            <div className="car-list">
                {cars.length > 0 ? (
                    cars.map((car, index) => (
                        <CarItem key={index} car={car}/>
                    ))
                ) : (
                    <p>Nu există mașini disponibile.</p>
                )}
            </div>
        </div>
    );
}

export default CarList;
