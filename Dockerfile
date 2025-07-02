# Сборочный образ с Gradle и JDK 17
FROM gradle:8.4-jdk17 AS build

# Копируем всё в контейнер
COPY . /app
WORKDIR /app

# Собираем fat JAR
RUN gradle installDist --no-daemon

# Минимальный runtime-образ с JDK
FROM openjdk:17-jdk-slim

WORKDIR /app

# Копируем скомпилированное приложение
COPY --from=build /app/build/install/* ./

# Открываем порт
EXPOSE 8080

# Запускаем приложение
CMD ["bin/ktor-sample-triangles"]