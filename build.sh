#!bin/bash
./gradlew bootRepackage
docker build -t movie-order:latest -t movie-order:${1} .