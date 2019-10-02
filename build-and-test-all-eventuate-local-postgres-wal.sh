#! /bin/bash

. ./set-env-eventuate-local-postgres-wal.sh

export database=postgres
export mode=wal

./_build-and-test-all.sh $*
