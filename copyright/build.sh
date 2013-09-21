#!/bin/bash

ENGINE="latex"

OPTS=`getopt -o n:e: --long name:,engine: -- "$@"`
eval set -- "$OPTS"

while true
do
  case "$1" in
    -n|--name)
      name="$2"
      shift 2
      ;;
    -e|--engine)
      engine="$2"
      shift 2
      ;;
    --)
      shift
      break
      ;;
     *)
      echo "Internal error!"
      exit 1
  esac
done

aspell -t check $name
$engine $name
