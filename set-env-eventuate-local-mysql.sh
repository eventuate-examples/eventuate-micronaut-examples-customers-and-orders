#! /bin/bash

. ./set-env.sh

export DB_URL=jdbc:mysql://${DOCKER_HOST_IP}/eventuate
export DB_USERNAME=mysqluser
export DB_PASSWORD=mysqlpw
export DB_DRIVERCLASSNAME=com.mysql.jdbc.Driver
