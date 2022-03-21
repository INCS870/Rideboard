#!/bin/bash

opt="$1"
ip=`ifconfig eth0 |grep "inet " |awk {'print $2'}`
if [[ -z "$opt" ]]
then
	echo 'Usage: run.sh {start|stop|status}'
	exit 0
fi
pids=`ps -ef |grep java |grep -v grep |awk {'print$2'} | xargs`	
if [ "$opt" == "start" ]
then
	if [[ -z "$pids" ]]
	then
		echo 'Starting Wildfly instance ...'
		/home/opc/wildfly/bin/standalone.sh -c standalone.xml -b "$ip" -bmanagement "$ip" -Djboss.node.name=node3 &
	else
		echo 'Wildfly is already running'
	fi
elif [ "$opt" == "stop" ]
then
	if [[ -z "$pids" ]]
        then
		echo 'Wildfly is NOT running'
		exit 0
	fi
	echo 'Stopping Wildfly instance ...'
	kill "$pids"
	echo "$pids killed"
else
	if [[ -z "$pids" ]]
	then
		echo 'Wildfly is NOT running'
	else
		echo 'Wildfly is running'
	fi
fi

while true; do sleep 1000; done