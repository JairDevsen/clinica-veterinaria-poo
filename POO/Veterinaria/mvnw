#!/usr/bin/env sh
# Simple Maven launcher that downloads Apache Maven if needed.

set -e

SCRIPT="$(cd "$(dirname "$0")" && pwd -P)/$(basename "$0")"
BASEDIR="$(dirname "$SCRIPT")"

if [ -z "$JAVA_HOME" ]; then
  echo "JAVA_HOME must be set"
  exit 1
fi

MAVEN_VERSION=3.9.9
MAVEN_DIST_DIR="$BASEDIR/.mvn/wrapper/apache-maven-$MAVEN_VERSION"
MAVEN_BIN="$MAVEN_DIST_DIR/bin/mvn"

if [ ! -x "$MAVEN_BIN" ]; then
  mkdir -p "$BASEDIR/.mvn/wrapper"
  MAVEN_ZIP="$BASEDIR/.mvn/wrapper/apache-maven-$MAVEN_VERSION-bin.zip"
  MAVEN_URL="https://repo1.maven.org/maven2/org/apache/maven/apache-maven/$MAVEN_VERSION/apache-maven-$MAVEN_VERSION-bin.zip"

  if [ ! -f "$MAVEN_ZIP" ]; then
    echo "Downloading Apache Maven $MAVEN_VERSION..."
    if command -v curl >/dev/null 2>&1; then
      curl -L -o "$MAVEN_ZIP" "$MAVEN_URL"
    elif command -v wget >/dev/null 2>&1; then
      wget -O "$MAVEN_ZIP" "$MAVEN_URL"
    else
      echo "curl or wget required to download Maven"
      exit 1
    fi
  fi

  echo "Extracting Maven..."
  if command -v unzip >/dev/null 2>&1; then
    unzip -q -o "$MAVEN_ZIP" -d "$BASEDIR/.mvn/wrapper"
  else
    (cd "$BASEDIR/.mvn/wrapper" && "$JAVA_HOME/bin/jar" xf "$MAVEN_ZIP")
  fi
fi

exec "$MAVEN_BIN" "$@"
