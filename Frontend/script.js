// ========================================
// Book Now Button (Home & Events Page)
// ========================================

let bookButtons = document.querySelectorAll(".book-btn");

bookButtons.forEach(function (button) {

    button.addEventListener("click", function () {

        let eventData = {
            event: button.dataset.event,
            location: button.dataset.location,
            date: button.dataset.date,
            seats: button.dataset.seats,
            price: button.dataset.price
        };

        console.log(button.dataset);
       console.log(eventData);

        // Save event details
        localStorage.setItem("selectedEvent", JSON.stringify(eventData));

        // Open booking page
        window.location.href = "booking.html?id=" + event.id;

    });

});


// ========================================
// Booking Page - Show Event Details
// ========================================

let eventName = document.getElementById("event-name");
let eventLocation = document.getElementById("event-location");
let eventDate = document.getElementById("event-date");
let eventSeats = document.getElementById("event-seats");
let eventPrice = document.getElementById("event-price");

if (eventName) {

    let params = new URLSearchParams(window.location.search);

    let eventId = params.get("id");

    fetch("http://localhost:1010/events/" + eventId)
    .then(function (response) {
        return response.json();
    })
    .then(function (event) {

        eventName.textContent = event.eventName;
        eventLocation.textContent = "📍 Location: " + event.location;
        eventSeats.textContent = "🎟️ Seats Left: " + event.availableSeats;
        eventPrice.textContent = "₹ " + event.ticketPrice;

    });

}


// ========================================
// Booking Form
// ========================================

let nameInput = document.getElementById("name");
let emailInput = document.getElementById("email");
let phoneInput = document.getElementById("phone");
let ticketsInput = document.getElementById("tickets");
let confirmButton = document.getElementById("confirm-btn");

if (confirmButton) {

    confirmButton.addEventListener("click", function () {

        let name = nameInput.value;
        let email = emailInput.value;
        let phone = phoneInput.value;
        let tickets = Number(ticketsInput.value);

        // Validation
        if (name === "" || email === "" || phone === "" || tickets <= 0) {
            alert("Please fill all fields correctly.");
            return;
        }

        let params = new URLSearchParams(window.location.search);
        let eventId = params.get("id");

        fetch("http://localhost:1010/events/" + eventId)
            .then(function (response) {
                return response.json();
            })
            .then(function (event) {

                let totalPrice = tickets * Number(event.ticketPrice);

                let ticket = {
                    eventId: Number(eventId),
                    customerName: name,
                    seatsBooked: tickets,
                    bookedAt: Date.now(),
                    totalPrice: totalPrice
                };

                return fetch("http://localhost:1010/tickets/book", {
                    method: "POST",
                    headers: {
                        "Content-Type": "application/json"
                    },
                    body: JSON.stringify(ticket)
                });

            })
            .then(function (response) {
                return response.json();
            })
            .then(function (savedTicket) {

                console.log(savedTicket);

                alert("Booking Confirmed!");

                window.location.href = "my-booking.html";

            })
            .catch(function (error) {
                console.error(error);
                alert("Booking Failed!");
            });

    });

}
let bookingList = document.getElementById("booking-list");

if (bookingList) {

    fetch("http://localhost:1010/tickets")
        .then(function (response) {
            return response.json();
        })
        .then(function (bookings) {

            for (let i = 0; i < bookings.length; i++) {

                let booking = bookings[i];

                let card = document.createElement("div");

                card.classList.add("booking-card");

                card.innerHTML = `
                    <h2>Booking #${booking.ticketId}</h2>

                    <p><strong>Customer:</strong> ${booking.customerName}</p>

                    <p><strong>Event ID:</strong> ${booking.eventId}</p>

                    <p><strong>Tickets:</strong> ${booking.seatsBooked}</p>

                    <p><strong>Total Price:</strong> ₹${booking.totalPrice}</p>

                    <button class="delete-btn">Delete</button>
                `;

                bookingList.appendChild(card);

            }

        });

}
let eventsContainer = document.getElementById("events-container");

if (eventsContainer) {

    let params = new URLSearchParams(window.location.search);

    let eventId = params.get("id");

    console.log(eventId);

    fetch("http://localhost:1010/events")
        .then(function(response) {
            return response.json();
        })
        .then(function(events) {

            for (let i = 0; i < events.length; i++) {

                let event = events[i];

                let card = document.createElement("div");

                card.classList.add("event-card");

                card.innerHTML = `
                    <h3>${event.eventName}</h3>

                    <div class="event-details">
                        <p>📍 ${event.location}</p>
                        <p>🎟️ Seats Left: ${event.availableSeats}</p>
                        <p>₹ ${event.ticketPrice}</p>
                    </div>

                    <button
                        class="book-btn"
                        data-id="${event.id}"
                        data-event="${event.eventName}"
                        data-location="${event.location}"
                        data-seats="${event.availableSeats}"
                        data-price="${event.ticketPrice}">
                        Book Now
                    </button>
                `;

                
                let bookButton=card.querySelector(".book-btn");

                bookButton.addEventListener("click",function(){
                    let eventData={
                        id: event.id,
                        event: event.eventName,
                        location: event.location,
                        seats: event.availableSeats,
                        price: event.ticketPrice
                    }
                    localStorage.setItem("selectedEvent",JSON.stringify(eventData));
                    window.location.href = "booking.html?id=" + event.id;
                });

                eventsContainer.appendChild(card);

                


                }

        });

}
let usernameInput = document.getElementById("username");
let passwordInput = document.getElementById("password");
let loginButton = document.getElementById("login-btn");

if (loginButton) {

    loginButton.addEventListener("click", function () {

        let username = usernameInput.value;
        let password = passwordInput.value;

        if (username === "" || password === "") {
            alert("Please fill all fields.");
            return;
        }

        let user={
            username: username,
            password: password
        };

        fetch("http://localhost:1010/user/login",{
            method: "POST",
            headers:{
                "Content-Type": "application/json"
            },
            body: JSON.stringify(user)
        })
        .then(function(response) {
            return response.text();
        })
       .then(function(token){

            localStorage.setItem("token", token);

            alert("Login Successful!");

            window.location.href = "index.html";

        });

    });

}
let registerButton = document.getElementById("register-btn");

if (registerButton) {

    registerButton.addEventListener("click", function () {

        let username = document.getElementById("reg-username").value;
        let password = document.getElementById("reg-password").value;

        if (username === "" || password === "") {
            alert("Please fill all fields.");
            return;
        }
        

        let user = {
            username: username,
            password: password
        };

        fetch("http://localhost:1010/user/register", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(user)
        })
        .then(function(response) {
            return response.json();
        })
        .then(function(savedUser) {

            console.log(savedUser);

            alert("Registration Successful!");

            window.location.href = "login.html";

        })
        .catch(function(error) {
            console.error(error);
            alert("Registration Failed!");
        });

    });

}