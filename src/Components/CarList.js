import { useEffect, useState } from 'react';
import React from 'react';
import CarItem from './CarItem';
import '../Styles/CarList.css';
import car1 from '../assets/images/car1.jpg';
import car2 from '../assets/images/car2.jpg';
import car3 from '../assets/images/car3.jpg';
import car4 from '../assets/images/car4.jpg';
import car5 from '../assets/images/car5.jpg';

// Imagini locale fallback
const carImages = [car1, car2, car3, car4, car5];

function CarList({ setViewCarList }) {
    const [cars, setCars] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        setViewCarList(true);

        fetch("http://localhost:8080/api/car")
            .then(response => {
                if (!response.ok) {
                    throw new Error("Eroare la încărcarea datelor");
                }
                return response.json();
            })
            .then(data => {
                console.log("Date primite din backend:", data);
                // Combină datele cu fallback-ul pentru imagini
                const carsWithImages = data.map((car, index) => ({
                    ...car,
                    imageUrl: car.imageUrl || (index < carImages.length ? carImages[index] : null),
                }));
                setCars(carsWithImages);
            })
            .catch(error => {
                console.error("Eroare:", error);
                setError(error.message);
            })
            .finally(() => {
                setLoading(false);
            });
    }, [setViewCarList]);

    if (loading) {
        return <p>Se încarcă lista de mașini...</p>;
    }

    if (error) {
        return <p>Eroare: {error}</p>;
    }

    return (
        <div>
            <h2>Mașinile Disponibile</h2>
            <div className="car-list">
                {cars.length > 0 ? (
                    cars.map((car, index) => (
                        <CarItem key={index} car={car} viewCarList={true} />
                    ))
                ) : (
                    <p>Nu există mașini disponibile.</p>
                )}
            </div>
        </div>
    );
}

export default CarList;
