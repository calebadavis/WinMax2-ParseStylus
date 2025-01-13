#!/bin/bash
java_exe="/usr/bin/java"
jar_file="$HOME/lib/ParseStylus.jar"
input_devices_file="/proc/bus/input/devices"
i2c_path=$($java_exe -jar $jar_file $input_devices_file)
echo "$i2c_path"
echo 1 | sudo tee "$i2c_path/inhibited"

