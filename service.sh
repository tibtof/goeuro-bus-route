#!/bin/bash
DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"

# Keep the pwd in mind!
# Example: RUN="java -jar $DIR/target/magic.jar"
RUN="java -jar $DIR/build/libs/goeuro-bus-route-1.0-SNAPSHOT.jar"
NAME="direct-bus-route"

DATA_FILE=$2

PIDFILE=/tmp/$NAME.pid
LOGFILE=/tmp/$NAME.log

application_pid() {
    pgrep -f "java -jar.*goeuro-bus-route"
}

start() {
    pid=`application_pid`
    if [ -n "$pid" ] && kill -0 $pid; then
        echo 'Service already running' >&2
        return 1
    fi
  local CMD="$RUN --data=$DATA_FILE --port=8088 &"
  sh -c "$CMD" > "$LOGFILE"
}

stop() {
    pid=`application_pid`
    if [ ! -n "$pid" ] || ! kill -0 $pid; then
        echo 'Service not running' >&2
        return 1
    fi
    kill -15 $pid
    echo 'Service stopped' >&2
}


case "$1" in
  start)
    start
    ;;
  stop)
    stop
    ;;
  block)
    start
    sleep infinity
    ;;
  *)
    echo "Usage: $0 {start|stop|block} DATA_FILE"
esac
