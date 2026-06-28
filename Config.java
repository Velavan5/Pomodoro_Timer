
// Data Object for configs [ only stores data] , has default values 
public class Config { 
  
  int focusTime ;
  int breakTime ;
  int longBreakTime ;
  String focusEndSong;
  String restEndSong;

  Config(int _focusTime , int _breakTime, int _longBreakTime, String _focusSong, String _restSong){
    focusTime = _focusTime;
    breakTime = _breakTime;
    longBreakTime = _longBreakTime;
    focusEndSong = _focusSong;
    restEndSong = _restSong;
  }

}
