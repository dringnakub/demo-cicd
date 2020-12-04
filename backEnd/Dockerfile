FROM hirokimatsumoto/alpine-openjdk-11

EXPOSE 8080

ARG JAR_FILE=target/shopping-cart-0.0.1-SNAPSHOT.jar

ADD ${JAR_FILE} shopping-cart.jar

ENTRYPOINT ["java", "-jar", "/shopping-cart.jar"]