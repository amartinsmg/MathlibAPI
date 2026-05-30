FROM eclipse-temurin:21-jdk as builder

RUN apt-get update && apt-get install -y \
    git \
    make \
    gcc \
    g++ \
    maven \
    && rm -rf /var/lib/apt/lists/*

WORKDIR /build

COPY pom.xml .
COPY src ./src
COPY scripts ./scripts

RUN chmod +x scripts/setup-native.sh \
    && bash scripts/setup-native.sh \
    && mvn package -DskiptTests -q

FROM eclipse-temurin:21-jre

WORKDIR /app

COPY --from=builder /build/target/mathlib-api-0.1.jar app.jar
COPY --from=builder /build/src/main/resources/libmathlib.so lib/libmathlib.so

EXPOSE 8000

ENTRYPOINT ["java", "-Djava.library.apth=/app/lib", "-jar", "/app/app.jar"]
