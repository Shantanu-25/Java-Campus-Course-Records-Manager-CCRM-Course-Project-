# Campus Course & Records Manager (CCRM) - Minimal Working Implementation

This repository contains a runnable **Java SE** console application skeleton implementing
the Campus Course & Records Manager (CCRM) project requirements. It includes:
- Domain classes: Person, Student, Instructor, Course, Enrollment
- Services: In-memory student/course/enrollment services
- Simple CLI: menu-driven interface (edu.ccrm.cli.CCRMApp)
- NIO.2-based import/export and backup utilities
- Examples of Singleton (AppConfig), Builder (Course.Builder), Enums, lambdas, Streams

## How to run
- JDK 17+ recommended.
- Compile:
  ```
  javac -d out $(find src -name "*.java")
  ```
- Run:
  ```
  java -cp out edu.ccrm.cli.CCRMApp
  ```

## Project layout
See /src/edu/ccrm for packages: cli, config, domain, io, service.

## Notes
This is a pared-down but functional skeleton. Expand each service to add full validations,
custom exceptions, and file parsing per the full project specification.
