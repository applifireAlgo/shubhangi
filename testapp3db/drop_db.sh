




echo $PATH
DB_PATH=/tmp/applifire/db/HX8GNHQR37HO4ZOHCVDUW/57F0693A-2414-49A5-B075-E07D2C0515F9
MYSQL=/usr/bin
USER=testapp3
PASSWORD=testapp3
HOST=localhost


echo 'drop db starts....'
$MYSQL/mysql -h$HOST -u$USER -p$PASSWORD -e "SOURCE $DB_PATH/drop_db.sql";
echo 'drop db ends....'