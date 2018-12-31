#!/bin/sh
protoc --plugin=protoc-gen-grpc-java=./protoc-gen-grpc-java   \
    --java_out="../java/" \
    --grpc-java_out="../java/"  \
    --proto_path="../proto/OSD/"  \
    "../proto/OSD/OSD.proto"


protoc --plugin=protoc-gen-grpc-java=./protoc-gen-grpc-java   \
    --java_out="../java/" \
    --grpc-java_out="../java/"  \
    --proto_path="../proto/StorageController/"  \
    "../proto/StorageController/FileSystem.proto"

protoc --plugin=protoc-gen-grpc-java=./protoc-gen-grpc-java   \
    --java_out="../java/" \
    --grpc-java_out="../java/"  \
    --proto_path="../proto/StorageController/"  \
    "../proto/StorageController/OSDListener.proto"

protoc --plugin=protoc-gen-grpc-java=./protoc-gen-grpc-java   \
    --java_out="../java/" \
    --grpc-java_out="../java/"  \
    --proto_path="../proto/ConfigurationHandler/"  \
    "../proto/ConfigurationHandler/ConfigHandler.proto"
