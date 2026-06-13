import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.time.LocalTime; 
import java.time.format.DateTimeFormatter;

// Timer - plays a song after specified time and plays for a specified duration
// Internally uses mpv command to play song
public class Timer {

  int volume = 70;

  void setTimer(int waitDuration, int playTime, String filePath ,String topic) { // arguments like song, volume , message : foucs or break
    //getting the time 
    LocalTime currentTime = LocalTime.now();
    LocalTime updatedTime = currentTime.plusMinutes(waitDuration);
    //Time format
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
    String formattedTime = updatedTime.format(formatter);
    System.out.println(topic + " started , Duration : "+ waitDuration + " Minutes , Ends At : " + formattedTime);
    System.out.println("Clock is Ticking...");
 
    //waiting logic
    try {
      TimeUnit.MINUTES.sleep(waitDuration);
    }catch (InterruptedException e) {
      System.out.println("Interrupted  while sleeping check stack for more context :: ");
      e.printStackTrace();
    }
    // sample : mpv --no-video  --volume=30  --loop  fileLocation
    ProcessBuilder pb = new ProcessBuilder(
        "mpv",
        "--no-video",
        "--volume="+volume,
        "--loop",
        filePath );

    try {
      // Start the process / command runs
      Process process = pb.start();
      System.out.println("''''  " + filePath + " is playing... " + " ''''");

      // waits for playTime seconds then kill signal send ,if not stpped then forcefully stopped
      boolean finished = process.waitFor(playTime, TimeUnit.SECONDS);

      if (!finished) {
        System.out.println(playTime + " seconds up. Stopping alarm...\n");
        process.destroyForcibly();
      } else {
        System.out.println("Alarm stopped early with code: " + process.exitValue());
      }

    } catch (IOException | InterruptedException e) {
      e.printStackTrace();
    }
  }

}


//thoughts :
//
// use variables not hashmap for values like flag , hashmap is for flags
