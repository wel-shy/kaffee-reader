# Kaffee Reader
> CLI application for scanning RFID tags and uploading to kaffee-api.

## Requirements

- Java 12

## Build 

Run:
```shell script
./gradlew build
mkdir ./store && touch ./store/rfid_store.json && touch ./store/user_store.json
```

## Run

Run:
```shell script
java -jar ./kaffee-reader.jar ./store < /dev/stdin
```