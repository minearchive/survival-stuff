package dev.minearchive.survival.util;

public class TimeUtil {
    public static String formatDHMS(long[] dhms) {
        StringBuilder sb = new StringBuilder();

        if (dhms[4] != 0) sb.append(dhms[4]).append(" year ");
        if (dhms[3] != 0) sb.append(dhms[3]).append(" day ");
        if (dhms[2] != 0) sb.append(dhms[2]).append(" hour ");
        if (dhms[1] != 0) sb.append(dhms[1]).append(" min ");
        sb.append(dhms[0]).append(" sec");

        return sb.toString();
    }

    public static long[] millisecondsToDHMS(long milliseconds) {
        long[] dhms = new long[5];
        dhms[0] = (milliseconds / 1000) % 60;
        dhms[1] = (milliseconds / (1000 * 60)) % 60;
        dhms[2] = (milliseconds / (1000 * 60 * 60)) % 24;
        dhms[3] = (milliseconds / (1000 * 60 * 60 * 24)) % 365;
        dhms[4] = (milliseconds / (1000L * 60 * 60 * 24 * 365));
        return dhms;
    }

}
