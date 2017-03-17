#!/bin/bash
docker run -d -p 27017:27017 --name=testmongo mongo
./gradlew clean
./gradlew unitTest
./gradlew clientTest
docker kill testmongo
docker rm -v testmongo

curl -i -X POST \
    -H "Content-Type: multipart/form-data" \
    -F "file=@./build/mca/mpd.json" \
    http://$MGP/api/upload

./gradlew pactPublish