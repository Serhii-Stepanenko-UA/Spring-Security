
ТЕХСТЕК

JDK
https://www.oracle.com/java/technologies/downloads/
(якщо встановлена, то Apache Tomcat має підтягнути встановлену)

Apache Tomcat
https://tomcat.apache.org/
(якщо Apache Tomcat вже встановлено, то можна скористатися встановленим)

Spring Boot
https://spring.io/projects/spring-boot

Spring Security
https://spring.io/projects/spring-security

Spring Data JPA
https://spring.io/projects/spring-data-jpa

MySQL 8
https://www.mysql.com/

Lombok
https://projectlombok.org/

Thymeleaf
https://www.thymeleaf.org/

Bootstrap
https://getbootstrap.com/

----------------------

JAVA ПРОЕКТ

(1) Налаштовуємо БД
INFO/SQLs.sql

(2) Переходимо до Spring Initializr
https://start.spring.io/

(3) Конфігурація проекту
(цифрові позначення версій можуть
змінюватись з розвитком фреймворку):
  (a) Project: Maven
  (b) Spring Boot: обираємо стабільну версію
  (c) Project Metadata:
    Group: залишаємо так
    Artifact: Spring-Boot-Security
    Name: AppDemo
    Description: залишаємо так
    Packaging name: залишаємо так
    Packaging: War
    Java: 21
  (d) Dependencies:
	Spring Boot DevTools
    Lombok
    Spring Web
    Thymeleaf
    Spring Security
    Spring Data JPA
    MySQL Driver
    Validation

(4) Перевіряємо. Клікаємо GENERATE.

(5) Зформований zip-файл розпакуємо,
переміщуємо в папку наших проектів.

(6) Відкриваємо проект в IDE.
Досліджуємо
	src/main/
    pom.xml

(7) В src/main формуємо та структуруємо
необхідний контент.

-----------------------

РОЗГОРТАННЯ (ДЕПЛОЙ) ПРОЕКТУ

Запустимо Apache Tomcat.
Створимо секцію Git Bash.
В IDE внизу

Terminal >  <іконка-випадаючого-списку>  > Git Bash

В секції буде шлях до директорії поточного проекту

<your-base-path>/<your-project-name>

Переходимо до директорії, де розташовані файли запуску
та припинення роботи Apache Tomcat

$ cd <your-base-path>/apache-tomcat-<version>/bin

Виконуємо команду

$ ./startup.bat

УВАГА.
Запуск Apache Tomcat:
startup.bat - для Windows,
startup.sh - для MacOS/Linux.
Припинення Apache Tomcat:
shutdown.bat - для Windows,
shutdown.sh - для MacOS/Linux.

Окремо з'явиться інформаційне вікно,
де відображається інформація про роботу
Apache Tomcat та програми.

Можемо згорнути його.

В IDE відкриваємо бокову праворуч вкладку Maven.
Через меню вкладки відкриваємо вікно команд,
де послідовно знаходимо та подвійним кліком
запускаємо відповідні Maven-команди

mvn clean

mvn install

В директорії проекту target, знаходимо файл
Your-Project-Name-0.0.1-SNAPSHOT.war
та копіюємо його у відповідну директорію Apache Tomcat,
в файловій системі комп'ютера

<your-base-path>/apache-tomcat-<version>/webapps

Через декілька секунд, в цій директорії, має з'явитися
папка Your-Project-Name-0.0.1-SNAPSHOT.
Apache Tomcat розархівував архівний файл проекту.

-----------------------

ТЕСТ ПРОЕКТУ

В Web-браузері запускаємо

http://localhost:8080/Your-Project-Name-0.0.1-SNAPSHOT/

та тестуємо функціонал програми.

-----------------------

РЕСУРСИ

https://docs.spring.io/spring-security/reference/index.html
https://docs.spring.io/spring-security/reference/servlet/configuration/java.html
https://jakarta.ee/specifications/persistence/3.1/apidocs/jakarta.persistence/jakarta/persistence/joincolumn
https://jakarta.ee/specifications/persistence/3.1/apidocs/jakarta.persistence/jakarta/persistence/jointable
https://jakarta.ee/specifications/persistence/3.1/apidocs/jakarta.persistence/jakarta/persistence/manytomany
https://www.baeldung.com/spring-boot-devtools
https://www.baeldung.com/the-persistence-layer-with-spring-data-jpa
https://www.baeldung.com/java-validation
https://www.baeldung.com/spring-data-repositories
https://www.baeldung.com/registration-with-spring-mvc-and-spring-security
https://www.baeldung.com/spring-security-registration-password-encoding-bcrypt
https://www.baeldung.com/spring-security-login
https://www.baeldung.com/spring-security-logout
https://www.baeldung.com/spring-security-csrf
https://www.baeldung.com/jpa-join-column



