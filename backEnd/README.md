# Shopping cart backend project

## Getting started
### IDE
1. Install IDE: IntelliJ
2. Install IntelliJ plugin: Lombok

### Running application
1. In terminal, at backend project root, run the following command.
    ```
    ./mvnw clean compile spring-boot:run
    ```
2. Application will be running on host URL: http://localhost:8080

## Coding convention
### Naming
1. Class -> Upper camel case
2. Non-static variable -> lower camel case
3. Static variable -> upper snake case
4. Method -> lower camel case

### Project structure
    
    backEnd/
        src/main/java/com/happy/shoppingcart/
            api/
                controller/
                    ProductController.java
                    TransactionController.java
                    domain/
                        ProductRequest.java
                        ProductResponse.java
                        TransactionRequest.java
                        TransactionResponse.java
                service/
                    ProductService.java
                    TransactionService.java
            common/
                service/
                    DatatimeService.java
                repo/
                entity/
