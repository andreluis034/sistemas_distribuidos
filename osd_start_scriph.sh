#!/bin/sh
apt-get update
apt-get -y install openjdk-8-jre nginx

#Download the application
mkdir -p /opt/osd_server
wget http://35.242.231.101/osd_server.jar -O /opt/osd_server/osd_server.jar

#Create the user
adduser --system --shell /bin/bash --home /opt/osd_server --group osd_server
chown -R osd_server.osd_server /opt/osd_server/

#Create the service
cat <<EOF > /etc/systemd/system/osd_server.service
[Unit]
Description=Storage Controller Daemon
After=network.target

[Service]
User=osd_server
Group=osd_server

Type=simple
WorkingDirectory=/opt/osd_server/
ExecStart=/usr/bin/java -Duser.language=en -Duser.country=US -Djava.net.preferIPv4Stack=true -jar /opt/osd_server/osd_server.jar -cip 10.156.0.6
TimeoutStopSec=20
KillMode=process

[Install]
WantedBy=multi-user.target
EOF

systemctl enable osd_server
systemctl start osd_server