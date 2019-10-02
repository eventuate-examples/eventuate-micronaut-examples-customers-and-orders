docker="./gradlew mysqlbinlogCompose"
dockercdc="./gradlew mysqlbinlogcdcCompose"



${docker}Down

${dockercdc}Build
${dockercdc}Up

./wait-for-services.sh $DOCKER_HOST_IP "8099" "actuator/health"


${docker}Build
${docker}Up

./wait-for-services.sh $DOCKER_HOST_IP "8081 8082 8083" "health"
