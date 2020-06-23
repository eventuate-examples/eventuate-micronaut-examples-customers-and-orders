ARG baseImageVersion
FROM eventuateio/eventuate-examples-docker-images-java-example-base-image:$baseImageVersion
COPY build/libs/customers-service.jar service.jar
HEALTHCHECK --start-period=30s --interval=5s CMD curl -f http://localhost:8080/health || exit 1