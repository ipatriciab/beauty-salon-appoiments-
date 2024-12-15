API pentru aplicația Beauty Salon și endpoint-urile funcționale:

Endpoint-uri Implementate
GET /appointments

Descriere: Returnează o listă cu toate programările.
Parametri: Niciun parametru necesar.
GET /appointments/{id}

Descriere: Returnează detalii despre o programare specifică pe baza ID-ului.
Parametri:
Path Variable: {id} - ID-ul programării dorite.
POST /appointments

Descriere: Adaugă o nouă programare.
Body (JSON): Obiect de tip AppointmentDto cu următorul format:
json
Copy code
{
    "customerId": 1,
    "serviceCategory": "Haircut",
    "appointmentDate": "2024-12-20T15:30:00",
    "price": 50.0
}
PUT /appointments/{id}

Descriere: Actualizează o programare existentă.
Parametri:
Path Variable: {id} - ID-ul programării de actualizat.
Body (JSON): Obiect de tip AppointmentDto cu date actualizate.
DELETE /appointments/{id}

Descriere: Șterge o programare existentă.
Parametri:
Path Variable: {id} - ID-ul programării de șters.
GET /customers

Descriere: Returnează o listă cu toți clienții.
Parametri: Niciun parametru necesar.
GET /customers/{id}

Descriere: Returnează detalii despre un client specific.
Parametri:
Path Variable: {id} - ID-ul clientului.
POST /customers

Descriere: Adaugă un nou client.
Body (JSON): Obiect de tip CustomerDto cu următorul format:
json
Copy code
{
    "firstName": "Maria",
    "lastName": "Popescu",
    "email": "maria.popescu@gmail.com",
    "phone": "0712345678"
}
PUT /customers/{id}

Descriere: Actualizează un client existent.
Parametri:
Path Variable: {id} - ID-ul clientului de actualizat.
Body (JSON): Obiect de tip CustomerDto cu date actualizate.
DELETE /customers/{id}

Descriere: Șterge un client existent.
Parametri:
Path Variable: {id} - ID-ul clientului de șters.
POST /register

Descriere: Înregistrează un utilizator nou (client sau staff).
Body (JSON): Obiect de tip UserDto cu următorul format:
json
Copy code
{
    "username": "john.doe",
    "password": "password123",
    "role": "CUSTOMER"
}
POST /login

Descriere: Autentifică un utilizator.
Body (JSON): Credențiale utilizator:
json
Copy code
{
    "username": "john.doe",
    "password": "password123"
}
GET /services

Descriere: Returnează o listă de servicii oferite.
Parametri: Niciun parametru necesar.
