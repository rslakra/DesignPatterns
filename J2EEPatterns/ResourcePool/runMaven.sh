#!/bin/bash
# Author: Rohtash Lakra
# Run the ResourcePool demo (PoolTest) via exec-maven-plugin.
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
echo "Running com.devamatre.j2eepatterns.resourcepool.PoolTest"
echo "(optional JVM app args after -- , e.g. mvn ... -Dexec.args=\"5 3\")"
echo

mvn clean compile exec:java -Drevision="${RELEASE_VERSION}"
