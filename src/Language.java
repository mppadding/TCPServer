public class Language {

    public static String UNKNOWN_COMMAND    = "Unknown command `{command}`\n";

    public static String NEWS_FOR           = "News for {location}\n";
    public static String WELCOME            = "Welcome to Matthijs Padding's News server\nType `help` to get help.\n";
    public static String GOODBYE            = "Goodbye :(\n";

    public static String NEWS_ITEM          = "News ({date}): {title}\nRead more at: {url}\n\n";

    private static String HELP_QUIT          = "quit\t\tAborts the connection\n";
    private static String HELP_HELP          = "help\t\tRuns this command\n";
    private static String HELP_NEWS          = "news {location}\tGives news about {location}\n";
    private static String HELP_PING          = "ping\t\tPong!\n";
    public static String HELP_MESSAGE       = "Help for News server:\n" + HELP_HELP + HELP_NEWS + HELP_PING + HELP_QUIT;

    public static String ERROR_ARGS         = "Invalid number of arguments. Command {command} needs {number} arguments\n";
}
