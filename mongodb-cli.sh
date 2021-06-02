#! /bin/bash

docker run --network eventuateexamplesjavacustomersandorders_default --link eventuateexamplesjavacustomersandorders_mongodb_1:mongodb -i -t mongo:3.6 /usr/bin/mongo --host mongodb
