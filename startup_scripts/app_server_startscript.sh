#!/bin/sh
apt-get update
apt-get -y install openjdk-8-jre nginx

#Download the application
mkdir -p /opt/app_server
wget http://35.242.231.101/app_server.jar -O /opt/app_server/app_server.jar

#Create the user
adduser --system --shell /bin/bash --home /opt/app_server --group app_server
chown -R app_server.app_server /opt/app_server/

#Create the service
cat <<EOF > /etc/systemd/system/app_server.service
[Unit]
Description=Storage Controller Daemon
After=network.target

[Service]
User=app_server
Group=app_server

Type=simple
WorkingDirectory=/opt/app_server/
ExecStart=/usr/bin/java -Djava.net.preferIPv4Stack=true -Duser.language=en -Duser.country=US -jar /opt/app_server/app_server.jar -cip 10.156.0.6
TimeoutStopSec=20
KillMode=process

[Install]
WantedBy=multi-user.target
EOF


#change nginx config
cat <<EOF > /etc/nginx/sites-available/default

server {
        listen 80 default_server;

        server_name _;

        location / {
                proxy_pass       http://127.0.0.1:9595;
                proxy_set_header Host      \$host;
                proxy_set_header X-Real-IP \$remote_addr;
        }

}
EOF

systemctl restart nginx
systemctl enable app_server
systemctl start app_server