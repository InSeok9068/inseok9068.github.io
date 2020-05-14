package editor;

import java.util.HashMap;
import java.util.Map;

public class JavaEditor {
  public static void main(String[] args) {
    Map<String,Object> map = new HashMap<>();

    map.put("param1", "1번");
    map.put("param2", 1);
    map.put("param3", null);
  
    System.out.println(String.valueOf(map.get("param1")));
    System.out.println(String.valueOf(map.get("param2")));
    System.out.println(String.valueOf(map.get("param3")));
  
  }
}