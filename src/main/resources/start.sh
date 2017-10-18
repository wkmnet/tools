#!/bin/sh
script_dir=`dirname $0`
root=`cd "$script_dir"; pwd`
echo "$root"
cd $root
nohup ./IntelliJIDEALicenseServer_linux_amd64 > ./license_server.log 2>&1 &
cd -
