# Country Route Finder

A simple **Spring Boot** application to calculate possible land routes between countries using border data from JSON.

---

## Project Objective

This application calculates land routes between countries based on the `borders` information from country data in JSON format.

- Each country is identified by the `cca3` code.
- If a land route exists, the service returns the route as a list of country codes.
- If no route exists, or the request is invalid, the service returns an HTTP 400 error.

---

## Data Source

Country data in JSON format:  
[https://raw.githubusercontent.com/mledoze/countries/master/countries.json](https://raw.githubusercontent.com/mledoze/countries/master/countries.json)

Each country object includes:
- `cca3` → country code (e.g., `CZE` for Czech Republic)
- `borders` → list of neighboring country codes

---

## Endpoints

**Base URL:** `http://localhost:8080/routing`

### 1. Get Route Between Countries
GET /routing/{origin}/{destination}

markdown
Copy code

**Path Parameters:**
- `origin` → 3-letter country code (cca3)
- `destination` → 3-letter country code (cca3)

**Responses:**

- **200 OK** → Returns a JSON list of country codes representing the land route.
```json
{
  "route": ["CZE", "AUT", "ITA"]
}
400 Bad Request → Invalid country code

json
Copy code
{
  "timestamp": "2025-12-08T16:46:01.049Z",
  "status": 400,
  "error": "Bad Request",
  "message": "Invalid country code",
  "path": "/routing/XXX/ITA"
}
400 Bad Request → No land route found

json
Copy code
{
  "timestamp": "2025-12-08T16:52:56.468Z",
  "status": 400,
  "error": "Bad Request",
  "message": "No land route found",
  "path": "/routing/ISL/NOR"
}
400 Bad Request → Origin and destination are the same

json
Copy code
{
  "timestamp": "2025-12-08T16:55:00.123Z",
  "status": 400,
  "error": "Bad Request",
  "message": "No land route found",
  "path": "/routing/USA/USA"
}
Example Requests
Direct Neighbor

bash
Copy code
GET http://localhost:8080/routing/CZE/AUT
Response:

json
Copy code
{
  "route": ["CZE", "AUT"]
}
Multi-step Route

bash
Copy code
GET http://localhost:8080/routing/CZE/ITA
Response:

json
Copy code
{
  "route": ["CZE", "AUT", "ITA"]
}
No Land Route

bash
Copy code
GET http://localhost:8080/routing/ISL/NOR
Response: 400 Bad Request

Invalid Country Code

bash
Copy code
GET http://localhost:8080/routing/XXX/ITA
Response: 400 Bad Request

Same Country

bash
Copy code
GET http://localhost:8080/routing/USA/USA
Response: 400 Bad Request

Build and Run Instructions
Requirements
Java 21+

Maven 3+

Internet connection (for downloading country data JSON)

Steps
bash
Copy code
# Clone the repository
git clone git@github.com:your-username/Country-route-finder.git
cd Country-route-finder

# Checkout your branch
git checkout fix/new-routes  # or your branch name

# Build the project
mvn clean install

# Run the application
mvn spring-boot:run
Testing with Postman
Download country-routing-tests.postman_collection.json
(contains all the valid/invalid route tests)

Open Postman → Click Import → Select the JSON file → Click Import

You can now run all pre-defined test endpoints.

