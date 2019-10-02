#! /bin/bash

. ./set-env-eventuate-local-postgres-polling.sh

export database=postgres
export mode=polling

./_build-and-test-all.sh $*
