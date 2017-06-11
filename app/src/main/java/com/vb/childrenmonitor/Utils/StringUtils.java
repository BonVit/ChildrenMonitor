package com.vb.childrenmonitor.Utils;

import java.util.regex.Pattern;

/**
 * Created by bonar on 6/10/2017.
 */

public class StringUtils {
    public static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    /**
     * At least 8 chars
        Contains at least one digit
        Contains at least one lower alpha char and one upper alpha char
        Contains at least one char within a set of special chars (@#%$^ etc.)
        Does not contain space, tab, etc.
     */
    public static final String PASSWORD_PATTERN =
            "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";

    public static boolean isMatching(final String string, final String pattern) {
        return Pattern.compile(pattern)
        .matcher(string)
        .matches();
    }
}
