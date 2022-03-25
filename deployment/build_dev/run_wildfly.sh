#!/bin/bash

opt="$1"

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
		/home/opc/wildfly/bin/standalone.sh -c standalone.xml -b 0.0.0.0 -bmanagement 127.0.0.1 -Djboss.node.name=node1 &
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
