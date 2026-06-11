// reads the config.txt file and gives config object

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ConfigParser{
  //defaults
  String timeUnit = "Minute";
  int focusTime = 25;
  int breakTime = 5;
  int longBreakTime = 30;
  int defaultFocusTime = 25;
  int defaultBreakTime = 5;
  int defaultLongBreakTime = 30;
  
 

  Config getConfigValues(String config_path){
    try {
          parseConfig(config_path);
    } catch (IOException e) {
          // defaults are applied 
          // If the file is missing or broken
          // if the format is wrong like no two words or no int
          System.out.println("WARNING: Could not load config file ( " + e.getMessage() + " ). Going with defaults.");
          return new Config(defaultFocusTime, defaultBreakTime, defaultLongBreakTime);
    }
    return new Config(focusTime, breakTime, longBreakTime);
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

            try {
                int value = Integer.parseInt(valueStr);
                set_value(key, value);
            //    System.out.println("Loaded Config -> " + key + ": " + value);
                
            } catch (NumberFormatException e) {
                System.out.println("CRITICAL: Value for '" + key + "' is not a valid number!");
                throw new IOException("Invalid numeric value in config: " + valueStr, e);
            }
        }
    }
  }

  void set_value(String attribute, int value){
        
    switch (attribute) {
       case "focusTime":
          focusTime = value;
          break;
 
       case "breakTime":
          breakTime = value;
          break;     

       case "longBreakTime":
          longBreakTime = value;
          break;

       default:
          System.out.println("WARNING :: keyWord misMatch , check the config file [not critical] ");
          break;
    }
  }

}
