// manages everything from config reading to running timmers 

public class Controller {
  Timer timer = new Timer(); 
  int noStudyCycle = 2; //no of focus + rest = 1 cycles , later give user access to change it 
  int FocusPlayTime = 25;
  int BreakPlayTime = 25; //seconds the song plays
  int continuousWorkLimit = 120; //in minute, time for longBreakTime
  int TimeSpent = 0;
  String config_path = "./config.txt";
  ConfigParser parser = new ConfigParser(); // parses and give the config object

  void start() { 
    Config config = parser.getConfigValues(config_path);
    int cycle = 1;
    while (cycle <= noStudyCycle){
      TimeSpent += config.focusTime + config.breakTime; //  breakTime is added here to avoid short break befor long  and to avoid more focus mode
      timer.setTimer(config.focusTime, FocusPlayTime, config.focusSong, "Focus mode");  //foucs timer 
      if (TimeSpent >= continuousWorkLimit ){
        TimeSpent = 0; //reset 
        timer.setTimer(config.longBreakTime, BreakPlayTime, config.restSong, "Long Rest mode after every 2 hours of timer / study+rest"); //Long break time
      }
      else timer.setTimer(config.breakTime, BreakPlayTime, config.restSong, "Rest mode");  //rest timer
      cycle++;
    }
  }
  







  // just keep minusing until you got more value for a focus round, if less than
  // spend the time and say completed .
  // think about edge cases ,but this is how you go
}
