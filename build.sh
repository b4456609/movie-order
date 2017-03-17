#!bin/bash
./gradlew bootRepackage
docker build -t movie-order:latest .