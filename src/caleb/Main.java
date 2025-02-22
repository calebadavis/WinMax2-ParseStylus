/**
 * GPD Win Max 2 parsing utility for stylus device discovery
 *
 * A simple tool to parse the values from /proc/bus/input/devices, returning
 * the sysfs path (/sys/devices/platform/...) for the 'stylus' i2c device
 *
 * The intention is for use in a script which passes the value '1' to the
 * 'inhibited' file in this path, essentially disabling input from the stylus
 */

package caleb;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {

    // The i2c device for which to search
    //public static String sDevName = "GXTP7385:00 27C6:0113 Stylus";
    public static String sDevName = "GXTP7385:00 27C6:0113";
    // The prefix of the i2c device name
    public static String sDevNamePfx = "N: Name=";

    // The prefix to the sysfs path of the stylus device
    public static String sSysfsPfx = "S: Sysfs=";

    // For some reason, '/proc/bus/input/devices' omits the '/sys' root-level
    // folder from the reported entry. For convenience, this utility prepends
    // that '/sys' folder to the return value
    public static String sSysfsStart = "/sys";

    public static void main(String[] args) {

	// Flag to continue searching
        boolean done = false;

	// Set when we encounter the correct i2c device 
        boolean foundDevice = false;

	// Set when we encounter the sysfs path element of the device entry
	boolean foundPath = false;

	// locals:
	int idx = -1;
        String input = "";
        String sysfsPath = "";
	Scanner scanner = null;

	// Grab input from command line
	File f = new File(args[0]);
	try {
	    scanner = new Scanner(f);
	} catch (FileNotFoundException fnfe) {
	    System.err.println("Error: file not found " + args[0]);
	    return;
	}
	// Keep reading input lines until we find an entry, or we reach the end
        while (!done) {
            try {
                input = scanner.nextLine();

		// look for the i2c device name. If it's a match, set 'found'
		// and keep scanning
                if (!foundDevice) {

		    if (input.equals(sDevNamePfx + "\"" + sDevName + "\"")) {
		    //if ((idx = input.indexOf(sDevName)) != -1) {
                        
                        foundDevice = true;
                        continue;
                    }
                } else {
		    // If we're scanning, and we've already found the device,
		    // it means we're in the right device section, and need to
		    // find the sysfs device name
                    if ((idx = input.indexOf(sSysfsPfx)) != -1) {

			// Bingo, reaching this point means we found the stylus
			// device name. We can spit it out to stdout and end
			foundPath = true;
                        sysfsPath = input.substring(idx + sSysfsPfx.length());
                        System.out.print(sSysfsStart + sysfsPath);
                        done = true;
                    }
                }
            } catch (NoSuchElementException nse) {

		// The device entry (or name) wasn't in the input
                done = true;

            } catch (IllegalStateException ise) {
                System.err.println("Error: " + Arrays.toString(ise.getStackTrace()));
                return;
            }
        }
    }
}
