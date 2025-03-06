# 📖 **Address Book App Setup** 📱<br><br><br>

**Date:** 📅 _March 6, 2025_<br><br>

---****<br><br>

## 🗓️ **Section 1: Address Book App Setup** 🔧<br><br>

### **UC 1 - Address Book Spring Project Setup** 🏗️<br><br>

**Objective** 🎯:  <br>
Create an Address Book Spring Project to cater to **REST Requests** from the Address Book UI. 🌐<br>

- Each use case (UC) should be in its **own branch** 🌱, and then merged into the **Main Branch**. 🌳<br>
- **GIT Version History** will be reviewed during the code review 🔍.<br>
- Reuse the **Address Book Frontend UI** 🖥️ that was previously developed.<br>
- Instead of making REST calls to **JSONServer** 📡, make server calls to the **Address Book Spring App** 🚀.<br>
- Test the **REST API** using **CURL Commands** 📝 to ensure everything works correctly.<br><br>

---<br><br>

### **UC 2 - REST Controller to Demonstrate HTTP Methods** 🔧<br><br>

**Objective** 🎯:  <br>
Create a **REST Controller** to demonstrate the various HTTP Methods such as `GET`, `POST`, `PUT`, and `DELETE`. 🔄<br>

- Before starting, set **application.properties** 🔑 to configure MySQL Driver and Credentials 🗝️.<br>
- Use **ResponseEntity** to return **JSON responses** from the backend. 💻<br>
- Test the **REST API calls** using **CURL** 📡.<br>
- Focus on establishing **connectivity** and ensuring **data transmission** through REST calls 💬.<br>
- **Test all CURL calls** ✅:  <br>
  - `GET`: Retrieve data 📥<br>
  - `GET by ID`: Retrieve data by specific ID 🔍<br>
  - `POST`: Create data 🆕<br>
  - `PUT`: Update data by ID 🔄<br>
  - `DELETE`: Delete data ❌<br><br>

---<br><br>

## 🗓️ **Section 2: Handling AddressBook DTO and Model in AddressBook Service Layer** 🧑‍💻<br><br>

### **UC 3 - Introducing DTO and Model** 📑<br><br>

**Objective** 🎯:  <br>
Introducing **DTO (Data Transfer Object)** and **Model** in the AddressBook App. 🧾<br>

- **DTO and Model** will be kept **simple** with only a few fields to begin with. 🧩<br>
- As the layers are being set up, you can expand the **DTO and Models** into full-fledged objects for the frontend to use. 🏗️<br>
- Use **ResponseEntity** to return **JSON responses** from the controller. 💬<br>
- Test the **REST Calls** using **CURL** 🧪 to ensure they are working as expected.<br><br>

---<br><br>

### **UC 4 - Introducing Services Layer** ⚙️<br><br>

**Objective** 🎯:  <br>
Introduce the **Services Layer** in the AddressBook App. 🏢<br>

- In UC1, the controller was directly creating and returning the Model. 📝<br>
- The **Services Layer** will now handle the **business logic** and manage the **Model**. 💼<br>
- Use the **@Autowired** annotation to inject the **Services Object** into the Controller. 🧩<br><br>

---<br><br>

### **UC 3 - Ability for Services Layer to Store Data** 💾<br><br>

**Objective** 🎯:  <br>
Enable the **Services Layer** to store **AddressBook data**. 📚<br>

- In UC2, the Services Layer did not store, update, or delete data. This will change in UC3. 🔄<br>
- The **Services Layer** will store data **in-memory** as a List. 🧠<br>
- Eventually, the data will be persisted into a **database** (MySQL) 💾 as you move forward.<br>
- Ensure that all **CURL calls** work: ✅<br>
  - `GET` 📥<br>
  - `GET by ID` 🔍<br>
  - `POST` 🆕<br>
  - `PUT` 🔄<br>
  - `DELETE` ❌<br><br>

---<br><br>

## 🎯 **Objective Overview** 📌<br><br>

The Address Book Spring Project will:<br>

- **Handle REST API requests** 🌐 from the frontend UI.<br>
- **Integrate with MySQL database** 💾 for persistent storage of address book entries.<br>
- Use **DTO and Model objects** 🧾 to structure the data and maintain separation of concerns between different layers (Controller, Service, Repository).<br>
- Implement **CURL commands** 📝 to test and ensure the functionality of the APIs.<br>
- Use **Spring Boot best practices** ⚙️ and ensure the application is scalable, maintainable, and testable. 🔒<br><br>

---<br><br>

### 🛠️ **Tech Stack Used**:<br><br>

- **Spring Boot** ⚙️ (for RESTful backend API)<br>
- **MySQL** 💾 (for database storage)<br>
- **CURL** 🧪 (for testing REST API calls)<br>
- **Postman** 📬 (alternative for testing APIs)<br>
- **Git** 🦠 (for version control)<br>

---<br><br>

