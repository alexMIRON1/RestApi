# My REST API's project

## Description

It is a REST API's application written in java. We have two entities gift_certificate(with fields- id, name, description, price, duration, create date, last update date ) and tags(with fields - id, name). They are connected with relationship many to many. Some functions were realized like CRUD operations for GiftCertificate. If new tags are passed during creation/modification – they should be created in the DB. For update operation - update only fields, that pass in requests, others should not be updated.CRD operations for the tag. Also, get certificates with tags by tag name, search by part of description, sort by date ASC.

## Technologies

1. Java Core (Collections, Generics, Java 8, java.time.*).
2. Apache Tomcat 9 as servlet container.
3. To store data used relation database - MySQL.
4. For access to data used Hibernate.
5. Application container: Spring IoC. Spring Framework, Spring MVC.
6. Lombok, Model Mapper.
7. Build tool: Maven.
8. Documented code and commented code(using log4j).
9. JSON
