
# RobotController Application

## Overview
This project simulates a robot navigating a floor using commands entered via a command-line interface. The robot's state includes position, pen status (up/down), and direction (north, south, east, west). When the pen is down and the robot moves, the floor is marked with a trace. The project follows an iterative and incremental development approach and includes comprehensive testing, code analysis, and QA processes.
---

## Features
- Initialize a 2D floor of size *n x n*
- Control the robot's pen (up/down)
- Move the robot in the current direction
- Turn the robot left or right
- Display the robot's current status
- Print the marked floor
- View command history
- Quit the application

---

## Technologies Used
- Java 23
- Maven
- JUnit 5.3.1
- JaCoCo (for code coverage)
- PIT (for mutation testing)
- Eclipse / IntelliJ (IDE)

---

## Project Structure

```
RobotController/
│
├── src/
│   ├── main/java/
│   │   └── robot/
│   │       └── RobotController.java
|   |        └── Main.java
│   └── test/java/
│       └── robot/
│           └── RobotControllerTest.java
|            └── MainTest.java
│
├── pom.xml
├── README.md

```
---

## Setup and Execution

### Prerequisites

- Java 17 or higher
- Maven

### Run the Application

```bash
git clone https://github.com/yourusername/robotcontroller.git
cd robotcontroller
mvn clean compile
mvn exec:java -Dexec.mainClass="robot.RobotController"
```

### Run Tests

```bash
mvn test
```

### Generate Code Coverage Report

```bash
mvn jacoco:prepare-agent test jacoco:report
```

### Run Mutation Testing

```bash
mvn org.pitest:pitest-maven:mutationCoverage
```

---

## Project Milestones & Deliverables

### ✅ Task 1: Development and Verification 

- [*] Implemented robot movement and pen logic
- [*] Command-based interaction through CLI
- [*] History tracking and grid display
- [*] Unit tests with JUnit
- [*] Time tracking with WakaTime
- [*] Maven setup with all dependencies
- [*] Delivered report with:
  - User Stories & Requirements (R1–R9)
  - Requirement–test case mapping


### ✅ Task 2: Code Analysis and Software Release (Week 6)
- [*] Integrated JaCoCo for code coverage
- [*] Collected metrics for:
  - Function Coverage
  - Statement Coverage
  - Path Coverage
  - Condition Coverage
  - Line Coverage
- [*] Defined threshold for code release


### ✅ Task 3: Black-box and White-box Testing (Week 10)

- [*] QA team performed:
  - Statement, Decision, Condition, and Multiple Condition coverage
  - Mutation Testing on movement logic
  - Data Flow Testing on floor drawing logic
- [*] QA report includes:
  - Test cases and results with screenshots
  - QA coverage conclusion
  - Comments to Dev team
- [*] Tools for white-box testing included in Maven

### ✅ Task 4: Regression Testing and Presentation (Week 12)

- [*] Dev team analyzed QA feedback
- [*] Implemented code fixes for:
  - Preventing `move` before initialization
  - Blocking negative/zero step movement
- [*] Created regression test cases
- [*] Confirmed test metrics and coverage
- [*] Delivered joint presentation and final report

---

## GitHub Repository

All code, test cases, and reports are available here:

👉 https://github.com/tanishaf28/Coverage
---

## Authors

- Tanisha Fonseca
- Vraj Shah
- Harshilsinh Solanki
- Teja Sayila

*Master’s students, Concordia University*

---

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

---
