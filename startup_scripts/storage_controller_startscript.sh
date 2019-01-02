#!/bin/sh
apt-get update
apt-get -y install openjdk-8-jre nginx

#Download the application
mkdir -p /opt/storage_controller
wget http://35.242.231.101/storage_controller.jar -O /opt/storage_controller/storage_controller.jar
touch /opt/storage_controller/fileSystem.xml /opt/storage_controller/crushmap.xml

#Create the user
adduser --system --shell /bin/bash --home /opt/storage_controller --group storage_controller
chown -R storage_controller.storage_controller /opt/storage_controller/

#Create the service
cat <<EOF > /etc/systemd/system/storage_controller.service
[Unit]
Description=Storage Controller Daemon
After=network.target

[Service]
User=storage_controller
Group=storage_controller

Type=simple
WorkingDirectory=/opt/storage_controller/
ExecStart=/usr/bin/java -Djava.net.preferIPv4Stack=true -Duser.language=en -Duser.country=US -Djava.net.preferIPv4Stack=true -jar /opt/storage_controller/storage_controller.jar 10.156.0.2
TimeoutStopSec=20
KillMode=process

[Install]
WantedBy=multi-user.target
EOF

systemctl enable storage_controller
systemctl start storage_controller