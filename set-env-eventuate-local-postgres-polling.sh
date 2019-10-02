. ./set-env.sh

export DB_URL=jdbc:postgresql://${DOCKER_HOST_IP}/eventuate
export DB_USERNAME=eventuate
export DB_PASSWORD=eventuate
export DB_DRIVERCLASSNAME=org.postgresql.Driver
export SPRING_PROFILES_ACTIVE=EventuatePolling
