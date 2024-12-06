function handleAddToCart(carId) {
    const userId = localStorage.getItem('idUser'); // Obține id-ul utilizatorului
    if (!userId) {
        alert("User ID nu este disponibil.");
        return;
    }

    // Obține ID-ul coșului din backend
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

            // Trimite cererea pentru a adăuga mașina în coș
            return fetch(`http://localhost:8080/api/cart/${cartId}/add-car/${carId}`, {
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
}
