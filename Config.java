
// Data Object for configs [ only stores data] , has default values 
public class Config { 
  
  int focusTime ;
  int breakTime ;
  int longBreakTime ;
  String focusSong;
  String restSong;

  Config(int _focusTime , int _breakTime, int _longBreakTime, String _focusSong, String _restSong){
    focusTime = _focusTime;
    breakTime = _breakTime;
    longBreakTime = _longBreakTime;
    focusSong = _focusSong;
    restSong = _restSong;
  }

}
