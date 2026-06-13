// reads the config.txt file and gives config object

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ConfigParser{
  //defaults
  String timeUnit = "Minute";
  int defaultFocusTime = 25;
  int defaultBreakTime = 5;
  int defaultLongBreakTime = 30;
  String defaultFoucsSong = "./HeatWaves.mp3";
  String defaultRestSong = "./HeatWaves.mp3";
  

  int focusTime = defaultFocusTime;
  int breakTime = defaultBreakTime;
  int longBreakTime = defaultLongBreakTime;
  String focusSong = defaultFoucsSong; 
  String restSong = defaultRestSong; 
     

  Config getConfigValues(String config_path){
    try {
          parseConfig(config_path);
    } catch (IOException e) {
          // defaults are applied 
          // If the file is missing or broken
          // if the format is wrong like no two words or not int
          System.out.println("WARNING: Could not load config file ( " + e.getMessage() + " ). Going with defaults.");
          return new Config(defaultFocusTime, defaultBreakTime, defaultLongBreakTime, defaultFoucsSong, defaultRestSong);
    }
    return new Config(focusTime, breakTime, longBreakTime, focusSong, restSong);
  }


  void parseConfig(String path) throws IOException{
    try (BufferedReader br = new BufferedReader(new FileReader(path))) {
        String line;
            
        while ((line = br.readLine()) != null) {
            line = line.trim();
            if (line.isEmpty()) continue; // It protects against blank lines at the end of files.

            String[] parts = line.split("\\s+");
            
            // validation check - stops execution if a line doesn't have exactly two parts
            if (parts.length != 2) {
                System.out.println("CRITICAL: Format Violation in config. It should be: [attribute value]");
                throw new IOException("Invalid config line format: " + line);
            }

            String key = parts[0];
            String valueStr = parts[1];
            
            if(key.equals("focus_song_path") || key.equals("rest_song_path")) 
                    set_value(key,0, valueStr);
            else { try{
                        int value = Integer.parseInt(valueStr);
                        set_value(key, value, "");
                
                     } catch (NumberFormatException e) {
                        System.out.println("CRITICAL: Value for '" + key + "' is not a valid number!");
                        throw new IOException("Invalid numeric value in config: " + valueStr, e);
                       } 
            }
        }
    }
  }

  void set_value(String attribute, int intValue, String strValue){
    
    if (intValue == 0) {
        System.out.println("Loaded Config -> " + attribute + ": " + strValue); //use for debugging 
    } 
    else  System.out.println("Loaded Config -> " + attribute + ": " + intValue); //use for debugging 

    switch (attribute) {
       case "focusTime":
          focusTime = intValue;
          break;
 
       case "breakTime":
          breakTime = intValue;
          break;     

       case "longBreakTime":
          longBreakTime = intValue;
          break;

       case "focus_song_path":
          focusSong = strValue; 
          break;

       case "rest_song_path":
          restSong = strValue;
          break;

       default:
          System.out.println("WARNING :: keyWord misMatch , check the config file [not critical] ");
          break;
    }
  }

}


//better idea:(try if you want)
//use method overloading for set_value for handling int and string 
//the problem you no more enfore types like focusSong must be a string (if you are ok or have better idea go for it)
