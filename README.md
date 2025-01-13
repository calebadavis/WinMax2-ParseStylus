# Stylus Device Discoverer for GPD Win Max 2 (Linux)

A java utility designed to parse the contents of `/proc/bus/input/devices`, and return the sysfs path of the stylus device.

## Description

Under linux, when the lid of my GPD Win Max 2 is closed, the stylus device has a tendancy to emit phantom input events (even when there is no stylus connected). Based on comments on the GPD Discord server, my laptop is not the only one with this behavior. I reached out to GPD support, but they seem to have no solution. So I decided to fix it myself.

In order to suppress these stylus input events, I throw a value of 1 to the 'inhibited' entry under the stylus i2c device.

Unfortunately, the path of that i2c device changes on every reboot.

This little java utility parses the argument file (almost certainly should be passed as `java -jar ~/lib/ParseStylus.jar /proc/bus/input/devices`). It emits the folder path of the stylus i2c device.

## Getting Started

### Dependencies

* Requires a GPD Win Max 2 device with misbehaving stylus behavior
* A Linux OS
* java installed to /usr/bin/java

### Installing

Build with apache ant in the root dir of the project. It will generate a .jar file under `build/jar/ParseStylus.jar`.
Place that .jar in an easily accessible location (I use $HOME/lib).
Then copy the script from `scripts/disable_stylus.sh`
I recommend setting a crontab root entry to run the script in the @boot section

## Authors

Contributors names and contact info

Caleb Davis

## Version History

* 0.1
    * Initial implementation

## License

This project is licensed under version 3 of the GNU General Public License - see the LICENSE.md file for details

## Acknowledgments
