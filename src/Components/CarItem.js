function CarItem({car, viewCarList}) {
    const handleAddToCart = () => {
        const userId = localStorage.getItem('idUser');
        if (!userId) {
            alert("User ID nu este disponibil.");
            return;
        }

        fetch(`http://localhost:8080/api/client/${userId}`)
            .then((response) => {
                if (!response.ok) {
                    throw new Error("Eroare la obținerea datelor clientului");
                }
                return response.json();
            })
            .then((client) => {
                if (!client.cart || !client.cart.idCart) {
                    throw new Error("Coșul nu este disponibil pentru acest client");
                }
                const cartId = client.cart.idCart;
                return fetch(`http://localhost:8080/api/cart/${cartId}/add-car/${car.idCar}`, {
                    method: "POST",
                    headers: {
                        "Content-Type": "application/json",
                    },
                });
            })
            .then((response) => {
                if (!response.ok) {
                    throw new Error("Eroare la adăugarea mașinii în coș");
                }
                alert("Mașina a fost adăugată în coș cu succes!");
            })
            .catch((error) => {
                console.error("Eroare:", error);
                alert("A apărut o problemă: " + error.message);
            });
    };

    return (
        <div className="car-item">
            <div className="car-image">
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
                {viewCarList && <button onClick={handleAddToCart}>Adaugă în Coș</button>}
            </div>
        </div>
    );
}

export default CarItem;
