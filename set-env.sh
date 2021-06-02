if [ -z "$DOCKER_HOST_IP" ] ; then
    if [ -z "$DOCKER_HOST" ] ; then
      export DOCKER_HOST_IP=`hostname`
    else
      echo using ${DOCKER_HOST?}
      XX=${DOCKER_HOST%\:*}
      export DOCKER_HOST_IP=${XX#tcp\:\/\/}
    fi
fi

echo DOCKER_HOST_IP is $DOCKER_HOST_IP

export COMPOSE_HTTP_TIMEOUT=240
export SPRING_DATA_MONGODB_URI=mongodb://$DOCKER_HOST_IP/customers_and_orders
export EVENTUATELOCAL_KAFKA_BOOTSTRAP_SERVERS=${DOCKER_HOST_IP}:9092
