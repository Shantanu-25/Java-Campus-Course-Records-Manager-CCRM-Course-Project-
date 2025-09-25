# Java-Campus-Course-Records-Manager-CCRM-Course-Project-

## 📌 Project Overview
The **Campus Course & Records Manager (CCRM)** is a **Java SE console application** that allows an institute to manage:
- Students (add/update, enroll/unenroll in courses, view transcripts)
- Courses (add/update, search/filter by department/instructor/semester)
- Enrollment & Grades (enforce credit limits, record marks, compute GPA)
- File operations (import/export students/courses, backup data with timestamp)
- Reports (top students, GPA distribution using Streams API)

This project demonstrates:
- Core Java syntax and constructs (variables, loops, decisions)
- **OOP principles** (Encapsulation, Inheritance, Abstraction, Polymorphism)
- **Design Patterns**: Singleton (`AppConfig`) and Builder (`Course.Builder`)
- **Java SE APIs**: Streams, Date/Time API, NIO.2 File I/O
- **Advanced Concepts**: assertions, lambdas, custom exceptions, recursion utility

---

## 🛠️ How to Run

### ✅ Prerequisites
- **JDK Version:** JDK 17 or later (LTS recommended)
- **IDE:** Eclipse IDE for Enterprise Java Developers (or any IDE of choice)

### 📦 Build & Run from Terminal
```bash
# 1. Compile
javac -d out $(find src -name "*.java")

# 2. Run
java -cp out edu.ccrm.cli.CCRMApp
