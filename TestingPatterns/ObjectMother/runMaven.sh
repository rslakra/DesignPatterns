#!/bin/bash
# Author: Rohtash Lakra
# Run the ObjectMother JUnit tests via Maven Surefire.
clear
SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
cd "$SCRIPT_DIR" || exit 1

source "${SCRIPT_DIR}/../../version.sh"

echo
echo "JAVA_HOME=${JAVA_HOME}"
echo
RELEASE_VERSION=$(buildVersion)
echo "revision=${RELEASE_VERSION}"
echo "Running ObjectMother tests..."
echo

mvn clean test -Drevision="${RELEASE_VERSION}"
