public class Pomodoro {

  static String Details = "This is a simple Pomodoro app , Each command must have a action \n\n"
      + " Usage: \n  pomodoro [action] [flags...]\n\n"
      + " Actions :  start - starts the timer \n"
      + "            config - to set the durations it is stored \n"
      + " Flags (used with config) : -f  to set focus duration , -r to set rest duration ";

  public static void main(String[] args) {
    // edge case
    if (args.length < 1) {
      System.out.println(" Error : Action missing , try this command \"Pomodoro help\" to learn more");
      return;
    }

    Controller control = new Controller();
    String action = args[0];
    switch (action) {

      case "help":
        System.out.println(Details);
        break;

      case "start":
        control.start();
        break;

      case "config":

        break;

      default:
        System.out.println("wrong action, use help");

    }

  }
}
