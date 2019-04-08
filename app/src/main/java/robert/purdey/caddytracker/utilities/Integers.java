package robert.purdey.caddytracker.utilities;

/**
 * Helper methods for integers
 */
public class Integers
{
    public static String SignInt(int n)
    {
        String sign = n < 0 ? "-" : "+";
        return sign + n;
    }
}
