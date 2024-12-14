function CarItem({car, viewCarList, handleRemoveFromCart, isViewCart, cartId}) {
    const isAuthenticated = localStorage.getItem("isAuthenticated");
    const storedUserRole = localStorage.getItem('userRole');
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
                console.log("Lista de coșuri:", client.carts);

                // Găsește un coș activ (de exemplu, fără Payment asociat)
                const activeCart = client.carts.find(cart => !cart.payment);
                if (!activeCart) {
                    throw new Error("Nu există un coș activ pentru acest client.");
                }

                console.log("Coș activ:", activeCart);

                const cartId = activeCart.idCart;

                return fetch(`http://localhost:8080/api/cart/${cartId}`)
                    .then((response) => {
                        if (!response.ok) {
                            throw new Error("Eroare la obținerea mașinilor din coș");
                        }
                        return response.json();
                    })
                    .then((cartCars) => {
                        // Verifică dacă mașina este deja în coș
                        const carExists = cartCars.some((cartCar) => cartCar.idCar === car.idCar);
                        if (carExists) {
                            throw new Error("Mașina este deja în coș");
                        }

                        // Adaugă mașina în coș
                        return fetch(`http://localhost:8080/api/cart/${cartId}/add-car/${car.idCar}`, {
                            method: "POST",
                            headers: {
                                "Content-Type": "application/json",
                            },
                        });
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
                {viewCarList && isAuthenticated && storedUserRole === "Client" && <button onClick={handleAddToCart}>Adaugă în Coș</button>}
                {isViewCart && isAuthenticated && handleRemoveFromCart &&
                    <button onClick={() => handleRemoveFromCart(car.idCar)}>Sterge din Cos</button>}
            </div>
        </div>
    );
}

export default CarItem;