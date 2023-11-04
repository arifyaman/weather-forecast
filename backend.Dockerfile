FROM gradle:8.3-jdk17 AS build


COPY settings.gradle /src/settings.gradle
COPY build.gradle /src/build.gradle
COPY backend /src/backend

WORKDIR /src

RUN gradle :backend:build -x test --no-daemon

FROM openjdk:17-alpine

RUN mkdir /app

COPY --from=build /src/backend/build/libs/backend-0.0.1.jar /app/application.jar

ENV SPRING_PROFILE $SPRING_PROFILE
ENV OVERRIDE_PROPS $OVERRIDE_PROPS

EXPOSE 8080

CMD [ "sh", "-c", "java -jar -Dspring.profiles.active=${SPRING_PROFILE} ${OVERRIDE_PROPS:+$OVERRIDE_PROPS} -Dspring.datasource.url=jdbc:postgresql://db:5432/weather /app/application.jar" ]
