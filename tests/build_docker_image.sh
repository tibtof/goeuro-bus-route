#!/bin/bash
DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
pushd $DIR > /dev/null
docker build -t dev-test --build-arg repo=$1 docker
popd > /dev/null
