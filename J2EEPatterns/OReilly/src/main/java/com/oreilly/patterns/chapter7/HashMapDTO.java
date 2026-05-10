// Example 7-5: HashMapDTO

package com.oreilly.patterns.chapter7;

import java.util.HashMap;

public class HashMapDTO extends HashMap  {
  private String mapDesc = null;
  
  public HashMapDTO(String desc) {
    mapDesc = desc;
  }

  public String getMapDescription() {
    return mapDesc;
  }
}
