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

▶️ Running from Eclipse

Open Eclipse → File → New → Java Project → Name: CCRM.

Copy src folder into project root.

Right-click project → Run As → Java Application → choose edu.ccrm.cli.CCRMApp.

🕰 Evolution of Java (Quick Timeline)

1995: Java 1.0 released (Sun Microsystems)

1998: Java 2 Platform introduced (J2SE, J2EE, J2ME)

2004: Java 5 (Generics, Enums, Annotations)

2014: Java 8 (Lambdas, Streams, Date/Time API)

2017: Java 9 (Modules)

2018+ : Six-month release cycle (Java 11 LTS, 17 LTS, 21 LTS)

🆚 Java ME vs SE vs EE
Aspect	Java ME	Java SE	Java EE (Jakarta EE)
Purpose	Mobile & Embedded devices	Standard Edition (desktop, CLI)	Enterprise Edition (web & server)
Scope	Limited API (small footprint)	Core Java libraries (collections, I/O, Streams)	Adds Servlets, JSP, EJB, JPA
Use Cases	IoT devices, feature phones	Desktop apps, CLI tools, APIs	Enterprise web apps, microservices
Examples	Android pre-Dalvik (MIDP)	Our CCRM project	Online banking systems, ERP
🖥 JDK / JRE / JVM Explained

JDK (Java Development Kit): Full toolkit including compiler (javac), debugger, and JRE.

JRE (Java Runtime Environment): Minimum runtime to run compiled .class files (JVM + core libraries).

JVM (Java Virtual Machine): Engine that converts bytecode → machine code. Platform-independent runtime.

Interaction:

Source Code (.java) --javac--> Bytecode (.class) --JVM--> Machine Code (Executes)

🪟 Windows Installation Steps

Download JDK from Oracle
 or Adoptium
.

Install using setup wizard (note installation path).

Add JAVA_HOME environment variable → set PATH to include %JAVA_HOME%\bin.

Verify installation:

java -version
javac -version


📷 [Insert screenshot here]

🛠 Eclipse Setup (with Screenshots)

Install Eclipse IDE (latest version).

Launch → Select workspace → New Java Project.

Copy src folder → Refresh project.

Right-click project → Run → Select edu.ccrm.cli.CCRMApp.
📷 [Insert Eclipse setup & run screenshot here]

📊 Mapping Table (Syllabus Topic → File/Class/Method)
Topic	Where Implemented
Encapsulation	Student.java (private fields + getters/setters)
Inheritance	Person.java (abstract) → Student.java, Instructor.java
Abstraction	Person.profile() (abstract method overridden)
Polymorphism	List<Person> can store Student or Instructor
Constructors & super	Student and Instructor call super()
Immutable Class	Course (final fields via Builder pattern)
Interface & Diamond Problem	StudentService, CourseService (with default methods if extended)
Functional Interfaces	Comparator lambda in CCRMApp.reports()
Streams API	Filtering + grouping in CCRMApp.reports()
NIO.2 File API	NioImportExportService.java, BackupService.java
Recursion Utility	BackupService.sizeRecursive()
Singleton Pattern	AppConfig.java
Builder Pattern	Course.Builder
Exception Handling	InMemoryEnrollmentService.enroll() (throws Exception)
Assertions	Person.java (assert id != null;)
Loops & Control	CLI menu (while loop, switch, break/continue)
✔ Enabling Assertions

Assertions help check invariants at runtime (disabled by default).
Run program with assertions enabled:

java -ea -cp out edu.ccrm.cli.CCRMApp

🧪 Sample Commands

Add student via menu:

id: s101
regNo: REG101
name: John Doe
email: john@example.com


Enroll a student:

enrollmentId: e101
studentId: s101
courseCode: CS101


Export data: choose an output directory path (e.g., exports/).

📸 Screenshots Checklist

✅ java -version terminal output

✅ Eclipse project structure

✅ Program running menu

✅ Sample export folder after backup

🙏 Acknowledgements

Java SE API Documentation

Oracle/Adoptium JDK Distributions

Eclipse Foundation (IDE)
