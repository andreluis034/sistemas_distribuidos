#!/bin/sh
IP="$(ifconfig eth0 | grep 'inet ' | awk '{print $2}')"
echo "${IP}"

wget http://192.168.10.55:8080/osd_server_jar/sistemas_distribuidos.jar -O osd_server.jar
java -jar osd_server.jar "${IP}"