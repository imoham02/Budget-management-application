# FinCore CLI

## Table of Contents

- [Description](#description)
- [Features](#features)
- [Installation](#installation)
- [Project Structure](#project-structure)
- [Testing](#testing)


## Description

Fincore CLI is a Java-based Command Line Interface (CLI) application for budget management. It is a console based menu system which allows users to do budget operations and view transactions. It utilises Java control structures and data structures to manage user budget accounts. 

## Features

- User-friendly CLI interface
- Includes unit tests using JUnit 5
- Built with Gradle for dependency management and easy building

## Installation

1. Clone the repository:
   ```bash
    git clone https://github.com/imoham02/Budget-management-application.git
   ```
2. Navigate to the project directory
   ```bash
    cd Budget-management-application
   ```
3. Build the project using Gradle:
   ```bash
    ./gradlew build
   ```
4. Run the application:
   ```bash
    ./gradlew run
   ```

## Project Structure

- **src/**
    - **main/**
        - **java/**
            - **com.fincore.budgetapp/**
                - **model/** 
                    - `BudgetEntry.java`
                    - `Transaction.java`
                    - `User.java`
                - **service/** 
                    - `BudgetManager.java`
                    - `IBudgetManager.java`
                - **util/** 
                    - `Console.java`
                    - `TransactionComparators.java`
    - **test/**
        - **java/**
            - **com.fincore.budgetapp/**
                - **model/**
                    - `BudgetEntry.java`
                - **service/**
                    - `BudgetManager.java`
- **build.gradle** 
- **settings.gradle**
- **README.md**
- **.gitignore**

## Testing
Run the unit tests with Gradle:
```bash
    ./gradlew test
```
