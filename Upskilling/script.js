// ===============================================
// Local Community Event Portal
// script.js
// Tasks 1 - 5
// ===============================================

// ===============================================
// 1. JavaScript Basics & Setup
// ===============================================

console.log("Welcome to the Community Portal");

window.onload = function () {
    alert("Welcome! Page Loaded Successfully.");
};

// ===============================================
// 2. Syntax, Data Types and Operators
// ===============================================

const eventName = "Community Music Festival";
const eventDate = "20 July 2026";
let availableSeats = 50;

console.log(`Event : ${eventName}`);
console.log(`Date : ${eventDate}`);
console.log(`Seats : ${availableSeats}`);

function registerSeat() {
    if (availableSeats > 0) {
        availableSeats--;
        console.log(`Seat Registered. Remaining Seats : ${availableSeats}`);
    } else {
        console.log("No seats available.");
    }
}

// ===============================================
// 3. Conditionals, Loops and Error Handling
// ===============================================

const events = [
    {
        name: "Music Festival",
        category: "Music",
        date: "2026-07-20",
        seats: 50
    },
    {
        name: "Sports Day",
        category: "Sports",
        date: "2026-07-25",
        seats: 0
    },
    {
        name: "Food Festival",
        category: "Food",
        date: "2026-08-01",
        seats: 30
    },
    {
        name: "Art Exhibition",
        category: "Art",
        date: "2025-12-10",
        seats: 20
    }
];

const today = new Date();

events.forEach(event => {

    const eventDay = new Date(event.date);

    if (eventDay > today && event.seats > 0) {
        console.log(`${event.name} is available.`);
    } else {
        console.log(`${event.name} is unavailable.`);
    }

});

function registerUser(event) {

    try {

        if (event.seats <= 0) {
            throw "Registration Failed. Event Full.";
        }

        event.seats--;

        console.log(`Registered Successfully for ${event.name}`);

    }

    catch (error) {

        console.log(error);

    }

}

// ===============================================
// 4. Functions, Scope, Closures
// ===============================================

function addEvent(name, category, date, seats) {

    events.push({
        name,
        category,
        date,
        seats
    });

    console.log(`${name} Added.`);

}

function filterEventsByCategory(category, callback) {

    const filtered = events.filter(event => event.category === category);

    callback(filtered);

}

// Closure Example

function registrationCounter(category) {

    let total = 0;

    return function () {

        total++;

        console.log(`${category} Registrations : ${total}`);

    };

}

const musicCounter = registrationCounter("Music");

musicCounter();
musicCounter();
musicCounter();

// Callback Example

filterEventsByCategory("Music", function (result) {

    console.log("Filtered Music Events");

    console.table(result);

});

// ===============================================
// 5. Objects and Prototypes
// ===============================================

class Event {

    constructor(name, category, date, seats) {

        this.name = name;
        this.category = category;
        this.date = date;
        this.seats = seats;

    }

}

Event.prototype.checkAvailability = function () {

    if (this.seats > 0) {

        return "Seats Available";

    }

    return "Event Full";

};

const event1 = new Event(

    "Coding Workshop",
    "Workshop",
    "2026-08-15",
    25

);

console.log(event1.checkAvailability());

console.log("Object Entries");

Object.entries(event1).forEach(entry => {

    console.log(entry[0], ":", entry[1]);

});

// Test Calls

registerSeat();

registerUser(events[0]);

addEvent(
    "Photography Workshop",
    "Workshop",
    "2026-09-15",
    40
);

console.table(events);

// ===============================================
// Tasks 6 - 10
// Arrays, DOM, Events, Fetch API, ES6
// ===============================================


// ===============================================
// 6. Arrays and Methods
// ===============================================

// Add a new event
events.push({
    name: "Yoga Camp",
    category: "Health",
    date: "2026-09-20",
    seats: 35
});

console.log("All Events");
console.table(events);

// Filter only Music Events
const musicEvents = events.filter(event => event.category === "Music");

console.log("Music Events");
console.table(musicEvents);

// Map Event Names
const eventCards = events.map(event => `Workshop on ${event.name}`);

console.log(eventCards);


// ===============================================
// 7. DOM Manipulation
// ===============================================

const eventSection = document.querySelector("#events");

function displayEvents(list = events) {

    // Remove previously generated cards
    const oldCards = document.querySelectorAll(".dynamic-card");

    oldCards.forEach(card => card.remove());

    list.forEach(event => {

        const card = document.createElement("div");

        card.className = "eventCard dynamic-card";

        card.innerHTML = `
            <h3>${event.name}</h3>

            <p><strong>Category:</strong> ${event.category}</p>

            <p><strong>Date:</strong> ${event.date}</p>

            <p><strong>Seats:</strong>
            <span id="${event.name.replace(/\s/g, "")}">
                ${event.seats}
            </span></p>

            <button onclick="register('${event.name}')">
                Register
            </button>

            <button onclick="cancelRegistration('${event.name}')">
                Cancel
            </button>

            <hr>
        `;

        eventSection.appendChild(card);

    });

}

displayEvents();


// Register Button

function register(name) {

    const event = events.find(e => e.name === name);

    if (!event)
        return;

    if (event.seats > 0) {

        event.seats--;

        document.getElementById(
            name.replace(/\s/g, "")
        ).textContent = event.seats;

        alert("Registration Successful!");

    }

    else {

        alert("No Seats Available");

    }

}


// Cancel Button

function cancelRegistration(name) {

    const event = events.find(e => e.name === name);

    if (!event)
        return;

    event.seats++;

    document.getElementById(
        name.replace(/\s/g, "")
    ).textContent = event.seats;

    alert("Registration Cancelled");

}


// ===============================================
// 8. Event Handling
// ===============================================


// Category Filter

const categorySelect = document.querySelector("select");

if (categorySelect) {

    categorySelect.onchange = function () {

        const category = this.value;

        if (category === "") {

            displayEvents(events);

        }

        else {

            const filtered = events.filter(

                event => event.name === category ||

                    event.category === category

            );

            displayEvents(filtered);

        }

    };

}


// Search

const searchBox = document.createElement("input");

searchBox.type = "text";

searchBox.placeholder = "Search Event";

document.body.insertBefore(searchBox, eventSection);

searchBox.addEventListener("keydown", function () {

    const keyword = searchBox.value.toLowerCase();

    const result = events.filter(event =>

        event.name.toLowerCase().includes(keyword)

    );

    displayEvents(result);

});


// ===============================================
// 9. Async JavaScript
// ===============================================


// Loading Message

const loading = document.createElement("h3");

loading.textContent = "Loading Events...";

document.body.appendChild(loading);


// Fetch using then()

fetch("events.json")

    .then(response => response.json())

    .then(data => {

        console.log("Fetched using THEN");
        console.table(data);
        // save data for search/filter
        window.eventsData = data;
        // render initial events into results container
        renderEvents(data);

    })

    .catch(error => {

        console.log(error);

    });


// Async Await

async function loadEvents() {

    try {

        loading.style.display = "block";

        const response = await fetch("events.json");

        const data = await response.json();

        console.log("Fetched using Async Await");

        console.table(data);

    }

    catch (error) {

        console.log(error);

    }

    finally {

        loading.style.display = "none";

    }

}

loadEvents();

// Helper: render events into #results
function renderEvents(list) {

    const container = document.getElementById("results");

    if (!container) return;

    container.style.display = "block";
    container.innerHTML = "";

    if (!list || list.length === 0) {
        container.innerHTML = '<p>No matching events found.</p>';
        return;
    }

    const ul = document.createElement("ul");
    ul.style.listStyle = 'none';
    ul.style.padding = '0';

    list.forEach(ev => {
        const li = document.createElement("li");
        li.style.padding = '8px 0';
        li.innerHTML = `<strong>${escapeHtml(ev.name)}</strong> — ${escapeHtml(ev.category)} — ${escapeHtml(ev.date)}`;
        ul.appendChild(li);
    });

    container.appendChild(ul);

}

// Simple HTML escape
function escapeHtml(str) {
    if (!str) return '';
    return String(str).replace(/[&<>"']/g, function (m) {
        return ({
            '&': '&amp;',
            '<': '&lt;',
            '>': '&gt;',
            '"': '&quot;',
            "'": '&#39;'
        })[m];
    });
}

// Wire search input
const searchInput = document.getElementById('search');
if (searchInput) {
    searchInput.addEventListener('input', (e) => {
        const q = e.target.value.toLowerCase().trim();
        const all = window.eventsData || [];
        if (!q) {
            renderEvents(all);
            return;
        }
        const filtered = all.filter(ev => (ev.name && ev.name.toLowerCase().includes(q)) || (ev.category && ev.category.toLowerCase().includes(q)) || (ev.date && ev.date.toLowerCase().includes(q)));
        renderEvents(filtered);
    });
}


// ===============================================
// 10. Modern JavaScript
// ===============================================


// Default Parameters

function welcome(name = "Guest") {

    console.log(`Welcome ${name}`);

}

welcome();

welcome("Saumitra");


// Destructuring

const {

    name,

    category,

    date,

    seats

} = events[0];

console.log(name);

console.log(category);

console.log(date);

console.log(seats);


// Spread Operator

const clonedEvents = [...events];

console.log("Original");

console.table(events);

console.log("Cloned");

console.table(clonedEvents);


// Arrow Function

const totalSeats = () => {

    let total = 0;

    events.forEach(event => {

        total += event.seats;

    });

    return total;

};

console.log("Total Seats =", totalSeats());

// ===============================================
// Tasks 11 - 14
// Forms, AJAX, Debugging, jQuery
// ===============================================


// ===============================================
// 11. Working with Forms
// ===============================================

const form = document.querySelector("form");

if (form) {

    form.addEventListener("submit", function (event) {

        // Prevent page reload
        event.preventDefault();

        console.log("Form Submitted");

        const name = form.elements["name"].value.trim();
        const email = form.elements["email"].value.trim();

        const eventSelect = document.querySelector("#event");

        const selectedEvent = eventSelect ?
            eventSelect.value : "";

        let valid = true;

        // Remove old error messages
        document.querySelectorAll(".error").forEach(e => e.remove());

        function showError(element, message) {

            const error = document.createElement("small");

            error.className = "error";

            error.style.color = "red";

            error.textContent = message;

            element.parentNode.appendChild(error);

        }

        if (name === "") {

            showError(
                form.elements["name"],
                "Name is required."
            );

            valid = false;

        }

        if (email === "") {

            showError(
                form.elements["email"],
                "Email is required."
            );

            valid = false;

        }

        if (selectedEvent === "") {

            showError(
                eventSelect,
                "Please select an event."
            );

            valid = false;

        }

        if (valid) {

            sendRegistration({

                name,

                email,

                event: selectedEvent

            });

        }

    });

}



// ===============================================
// 12. AJAX & Fetch API
// ===============================================

function sendRegistration(userData) {

    console.log("Sending Registration");

    console.log(userData);

    setTimeout(() => {

        fetch("https://jsonplaceholder.typicode.com/posts", {

            method: "POST",

            headers: {

                "Content-Type": "application/json"

            },

            body: JSON.stringify(userData)

        })

        .then(response => response.json())

        .then(data => {

            console.log("Registration Successful");

            console.log(data);

            alert("Registration Successful!");

        })

        .catch(error => {

            console.log(error);

            alert("Registration Failed.");

        });

    },2000);

}



// ===============================================
// 13. Debugging
// ===============================================

console.log("===== Debug Logs =====");

console.log("Events Array");

console.table(events);

console.log("Total Events");

console.log(events.length);

function debugRegistration(eventName){

    console.log("Register Button Clicked");

    console.log("Selected Event :",eventName);

    const event=events.find(e=>e.name===eventName);

    debugger;

    console.log(event);

}

console.log("Use Chrome DevTools");

console.log("Open Console");

console.log("Use Network Tab");

console.log("Check POST Payload");



// ===============================================
// 14. jQuery
// ===============================================

// Requires:
// <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>

if(window.jQuery){

$(document).ready(function(){

    $("#registerBtn").click(function(){

        $(".eventCard").fadeOut(800);

        $(".eventCard").fadeIn(800);

    });

});

}

console.log(
"React/Vue Benefit : Component-based architecture, reusable UI, faster rendering and easier state management."
);