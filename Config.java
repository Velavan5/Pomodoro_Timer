
// Data Object for configs [ only stores data] , has default values 
public class Config { 
  
  int focusTime ;
  int breakTime ;
  int longBreakTime ;

  Config(int _focusTime , int _breakTime, int _longBreakTime){
    focusTime = _focusTime;
    breakTime = _breakTime;
    longBreakTime = _longBreakTime;
  }


}
