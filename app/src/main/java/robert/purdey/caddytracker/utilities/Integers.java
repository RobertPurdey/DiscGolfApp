package robert.purdey.caddytracker.utilities;

/**
 * Helper methods for integers
 */
public class Integers
{
    public static String SignInt(int n)
    {
        return n >= 0
            ? "+" + n
            : ""  + n;
    }
}
