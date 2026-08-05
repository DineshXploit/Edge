#!/usr/bin/env sh
set -eu
ROOT_DIR=$(CDPATH= cd -- "$(dirname -- "$0")" && pwd)
if [ -f "$ROOT_DIR/gradle/wrapper/gradle-wrapper.jar" ]; then
  exec java -jar "$ROOT_DIR/gradle/wrapper/gradle-wrapper.jar" "$@"
fi
if [ -d /root/.local/share/mise/installs/java/21.0.2 ]; then
  export JAVA_HOME=/root/.local/share/mise/installs/java/21.0.2
  export PATH="$JAVA_HOME/bin:$PATH"
fi
exec gradle "$@"
