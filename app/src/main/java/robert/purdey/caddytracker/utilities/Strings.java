package robert.purdey.caddytracker.utilities;

/**
 * Helper methods for strings
 */
public class Strings
{
    /**
     * Validates if a string is null or empty
     *
     * @param s string to validate
     *
     * @return true if string is not null and not empty, false otherwise.
     */
    public static Boolean isNullOrEmpty(String s)
    {
        return s == null || s.isEmpty();
    }
}
