import java.io.IOException;

public class Notification {
    
void displayMessage(String title, String message){
        
    // command = notify-send -u critical "Title" "Can you see me now"
 ProcessBuilder pb = new ProcessBuilder(
        "notify-send",
        "-u",
        "critical",
        title,
        message);

    try {
      // Start the process / command runs
      Process process = pb.start();
      System.out.println("'''' Notification send ''''");

    } catch (IOException e) {
      e.printStackTrace();
    }
   
  }
}
