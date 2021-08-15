#!/bin/bash

if [ $# -ne 1 ]; then
  echo "Please provide a path to a rewards CSV file from PoolTool.io"
elif [ ! -f $1 ]; then
  echo "$1 does not exist"
else
  ./gradlew -PmainClass=Main run --args="$1"
fi