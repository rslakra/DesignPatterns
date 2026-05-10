#!/bin/bash
# Author: Rohtash Lakra
# TestingPatterns is a POM aggregator — run each sample from its module directory.
clear
SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
cd "$SCRIPT_DIR" || exit 1

echo "TestingPatterns: use each module's runMaven.sh to run that module's JUnit tests, e.g.:"
echo "  ${SCRIPT_DIR}/AbstractTest/runMaven.sh"
echo "  ${SCRIPT_DIR}/Assertion/runMaven.sh"
echo "  ${SCRIPT_DIR}/CategoryPartition/runMaven.sh"
echo "  ${SCRIPT_DIR}/ControlledException/runMaven.sh"
echo "  ${SCRIPT_DIR}/MockObjects/runMaven.sh"
echo "  ${SCRIPT_DIR}/ObjectMother/runMaven.sh"
echo "  ${SCRIPT_DIR}/SelfShunt/runMaven.sh"
echo "  ${SCRIPT_DIR}/UseCaseTest/runMaven.sh"
echo
echo "PetStore is reference-only (Sun J2EE BluePrints app, compile skipped)."
echo
