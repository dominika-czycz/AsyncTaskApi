# async Task Api

> A simple REST API for asynchronous Tasks Processing.

## Table of contents
* [General info](#general-info)
* [Technologies](#technologies)
* [Example](#example)
* [How to start the application](#how-to-start-the-application)
* [How to use the application](#how-to-use-the-application)
* [Tips for IntelliJ users](#tips-for-intellij-users)
* [Contact](#contact)

## General info
>Task accepts two strings as the parameters: the pattern and the input. Task should find the first best match 
– position of the pattern in the input with the least number of different characters

## Technologies
* Java 17
* Spring 3.0.2
* Gradle
* Docker
* Caching Abstraction in Spring with Caffeine 3.1.2

## Example
* Input: ABCD, pattern: BCD -> position:1, typos:0 (‘BCD’ matches exactly substring on position 1)
* Input: ABCD, pattern: BWD -> position:1, typos:1 (‘BCD’ matches, ‘W’ is a typo here)
* Input: ABCDEFG, pattern: CFG -> position:4, typos:1 (‘EFG’ is better match than ‘CDE’)
* Input: ABCABC, pattern: ABC -> position:0, typos:0 (matches exactly twice, selects first)
* Input: ABCDEFG, pattern: TDD -> position:1, typos:2 (match first - BCD, not CDE)

## How to start the application
In terminal in the application folder enter:
1. gradle bootJar
2. docker-compose up --build

## How to use the application
[here](postman) you can find postman collection to test the application.

## Tips for IntelliJ users 
If gradle bootJar throws an exception related to 'apiElements' capability org.springframework.boot:spring-boot-gradle-plugin:3.0.2
1. Go to the settings --> Build, Execution, Deployment --> Build Tools --> Gradle.
2. Click on your gradle project under 'Gradle Projects'.
3. Choose your Gradle JVM for the project. In my case it was openjdk-17.

## Contact
Created by [@dominika-czycz](https://github.com/dominika-czycz)

[dominika-czycz@gmail.com](dominika.czycz@gmail.com) _feel free to contact!_
