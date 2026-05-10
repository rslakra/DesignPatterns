#!/bin/bash
# Author: Rohtash Lakra
# Source common version function
SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
source "${SCRIPT_DIR}/../../version.sh"

# Set Java version
# JAVA_VERSION=21
# export JAVA_HOME=$(/usr/libexec/java_home -v $JAVA_VERSION)

echo
echo "${JAVA_HOME}"
echo
SNAPSHOT_VERSION=$(buildVersion SNAPSHOT)
RELEASE_VERSION=$(buildVersion)
mvn clean install -Drevision=$SNAPSHOT_VERSION
echo
