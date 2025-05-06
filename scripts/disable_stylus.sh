#!/bin/bash
java_exe="/usr/bin/java"
jar_file="$HOME/lib/ParseStylus.jar"
input_devices_file="/proc/bus/input/devices"
i2c_paths=$($java_exe -jar $jar_file $input_devices_file)
echo "$i2c_paths"
while IFS= read -r i2c_path; do
  echo 1 | sudo tee "$i2c_path/inhibited"
done <<< "$i2c_paths"
