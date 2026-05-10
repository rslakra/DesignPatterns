#!/bin/bash
# Author: Rohtash Lakra
# J2EEPatterns is a POM aggregator — run each sample from its module directory.
clear
SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
cd "$SCRIPT_DIR" || exit 1

echo "J2EEPatterns: use each module's runMaven.sh, for example:"
echo "  ${SCRIPT_DIR}/AntiPatterns/runMaven.sh"
echo "  ${SCRIPT_DIR}/MVC/runMaven.sh"
echo "See ${SCRIPT_DIR}/README.md for ports and context paths."
echo
