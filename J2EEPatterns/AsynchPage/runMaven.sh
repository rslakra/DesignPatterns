#!/bin/bash
# Author: Rohtash Lakra
# Run the AsynchPage WAR locally with embedded Jetty (javax / EE8).
clear
SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
cd "$SCRIPT_DIR" || exit 1

source "${SCRIPT_DIR}/../../version.sh"

# JAVA_VERSION=21
# export JAVA_HOME=$(/usr/libexec/java_home -v $JAVA_VERSION)

echo
echo "JAVA_HOME=${JAVA_HOME}"
echo
RELEASE_VERSION=$(buildVersion)
echo "revision=${RELEASE_VERSION}"
echo "Starting Jetty — app: http://localhost:8080/asynch-page/"
echo "(Ctrl+C to stop)"
echo

mvn clean package jetty:run-war -Drevision="${RELEASE_VERSION}"
