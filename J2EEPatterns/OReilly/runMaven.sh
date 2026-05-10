#!/bin/bash
# Author: Rohtash Lakra
# O'Reilly book samples are packaged as a JAR only (no bundled web app).
clear
SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
cd "$SCRIPT_DIR" || exit 1

source "${SCRIPT_DIR}/../../version.sh"

echo
echo "JAVA_HOME=${JAVA_HOME}"
echo
RELEASE_VERSION=$(buildVersion)
echo "revision=${RELEASE_VERSION}"
echo "Building j2ee-oreilly-samples JAR (no Jetty entry point in this module)."
echo

mvn clean package -Drevision="${RELEASE_VERSION}"
