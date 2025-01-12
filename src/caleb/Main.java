package caleb;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {

    public static String sDevName = "GXTP7385:00 27C6:0113 Stylus";
    public static String sSysfsPfx = "S: Sysfs=";
    public static String sSysfsStart = "/sys";

    public static void main(String[] args) {

        boolean done = false;
        boolean foundDevice = false;
        boolean foundPath = false;
        int idx = -1;
        String input = "";
        String sysfsPath = "";

        Scanner scanner = new Scanner(System.in);

        while (!done) {
            try {
                input = scanner.nextLine();

                if (!foundDevice) {
                    if ((idx = input.indexOf(sDevName)) != -1) {
                        foundDevice = true;
                        continue;
                    }
                } else {
                    if ((idx = input.indexOf(sSysfsPfx)) != -1) {
                        foundPath = true;
                        sysfsPath = input.substring(idx + sSysfsPfx.length());
                        System.out.print(sSysfsStart + sysfsPath);
                        done = true;
                    }
                }
            } catch (NoSuchElementException nse) {
                done = true;
            } catch (IllegalStateException ise) {
                System.err.print("Error: " + Arrays.toString(ise.getStackTrace()));
                return;
            }
        }
    }
}
