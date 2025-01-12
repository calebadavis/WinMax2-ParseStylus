# Stylus Device Discoverer for GPD Win Max 2 (Linux)

A java utility designed to parse the contents of `/proc/bus/input/devices`, and return the sysfs path of the stylus device.

## Description

Under linux, my GPD Win Max 2 has a tendancy to emit phantom input events from the stylus device (even when there is no such device connected) when the screen is closed.

In order to suppress these stylus input events, I throw a value of 1 to the 'inhibited' entry under the i2c device.

Unfortunately, the path of that i2c device changes on every reboot.

This little java utility parses input (piped from stdin using `cat /proc/bus/input/devices | java -jar ParseStylus.jar`). It emits the folder of the stylus i2c device.

## Getting Started

### Dependencies

* Describe any prerequisites, libraries, OS version, etc., needed before installing program.
* ex. Windows 10

### Installing

Build with apache ant in the root dir of the project. It will generate a .jar file under `build/jar/ParseStylus.jar`.


* How/where to download your program
* Any modifications needed to be made to files/folders

### Executing program

* How to run the program
* Step-by-step bullets
```
code blocks for commands
```

## Help

Any advise for common problems or issues.
```
command to run if program contains helper info
```

## Authors

Contributors names and contact info

ex. Dominique Pizzie  
ex. [@DomPizzie](https://twitter.com/dompizzie)

## Version History

* 0.2
    * Various bug fixes and optimizations
    * See [commit change]() or See [release history]()
* 0.1
    * Initial Release

## License

This project is licensed under the [NAME HERE] License - see the LICENSE.md file for details

## Acknowledgments
