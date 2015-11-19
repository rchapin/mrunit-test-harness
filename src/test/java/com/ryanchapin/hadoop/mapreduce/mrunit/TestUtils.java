package com.ryanchapin.hadoop.mapreduce.mrunit;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.apache.hadoop.mapreduce.Counters;

public class TestUtils {

  public static <E extends Enum<E>> Map<E, Long> getCounters(Counters counters, Class<E> enumType) {
    Map<E, Long> retVal = new HashMap<E, Long>();
    long   count = 0;
    
    E[] values = enumType.getEnumConstants();
    for (E value : values) {
       count = counters.findCounter(value).getValue();
       retVal.put(value, count);
    }
    return retVal;
 }
  
  public static <E extends Enum<E>> void validateCounters(
      Counters counters, Map<E, Long> expected, Class<E> enumType)
  {
   // Get the map of our counters
   Map<E, Long> actualCounters = getCounters(counters, enumType);
   
   // We could simply invoke a .equals or .compare but if we iterate through
   // the expected map and check each value, we can then output some feedback
   // as to what has or has not matched
  
   E expectedKey    = null;
   long expectedVal = 0;
   long actualVal   = 0;
   for (Map.Entry<E, Long> entry : expected.entrySet()) {
      expectedKey = entry.getKey();
      expectedVal = entry.getValue();
      actualVal   = actualCounters.get(expectedKey);
      
      assertEquals("Expected counter value of " + expectedVal + " for " +
            expectedKey.toString() + ":", expectedVal, actualVal);
      
      // Reset the actualVal
      actualVal = 0;
   }
}
}
