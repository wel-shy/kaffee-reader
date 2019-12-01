# Kaffee Reader
> CLI application for scanning RFID tags and uploading to kaffee-api.

## Install
Run `./install.sh` to download dependencies.

## Build 
Run:
```shell script
ant -f kaffee-reader.xml && \ 
mkdir -p ./store && \
touch ./store/user_store.json && \
touch ./rfid_store.json
```

## Run
Run:
```shell script
java -cp kaffee-reader.jar uk.dwelsh.kaffee.Main $1 $2
```
Where:
- `$1` is the rfid tag.
- `$2` is the path to the store.