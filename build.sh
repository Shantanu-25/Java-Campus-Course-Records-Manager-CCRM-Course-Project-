#!/bin/bash
mkdir -p out
find src -name "*.java" > sources.txt
javac -d out @sources.txt
echo "Compiled. Run: java -cp out edu.ccrm.cli.CCRMApp"
