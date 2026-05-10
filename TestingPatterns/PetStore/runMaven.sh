#!/bin/bash
# Author: Rohtash Lakra
# Build the buildable subset of the original Sun J2EE BluePrints PetStore
# (Java 21 + javax.* J2EE APIs).  See pom.xml for the legacy/sources/ split.
clear
SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
cd "$SCRIPT_DIR" || exit 1

source "${SCRIPT_DIR}/../../version.sh"

echo
echo "JAVA_HOME=${JAVA_HOME}"
echo
RELEASE_VERSION=$(buildVersion)
echo "revision=${RELEASE_VERSION}"
echo "Building testing-petstore (41 self-contained sources, Java 21)..."
echo "Reference-only sources: ${SCRIPT_DIR}/legacy/sources"
echo "Original BluePrints app: ${SCRIPT_DIR}/legacy/apps"
echo

mvn clean package -Drevision="${RELEASE_VERSION}"
