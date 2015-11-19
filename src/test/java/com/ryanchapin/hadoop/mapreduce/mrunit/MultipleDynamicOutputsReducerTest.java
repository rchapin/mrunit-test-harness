package com.ryanchapin.hadoop.mapreduce.mrunit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Counters;
import org.apache.hadoop.mrunit.mapreduce.MapDriver;
import org.apache.hadoop.mrunit.mapreduce.ReduceDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * Requires the {@code @PrepareForTest(WordReducer.class} annotation to
 * enable the mocking of the MultipleOutputs class.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({MultipleDynamicOutputsReducer.class})
public class MultipleDynamicOutputsReducerTest {

  private MapDriver<LongWritable, Text, Text, IntWritable> mapDriver;
  private ReduceDriver<Text, IntWritable, Text, IntWritable> reduceDriver;
  
  @Before
  public void setUp() {
    mapDriver = new MapDriver<LongWritable, Text, Text, IntWritable>(
        new SimpleMapper());
    reduceDriver = new ReduceDriver<Text, IntWritable, Text, IntWritable>(
        new MultipleDynamicOutputsReducer());
  }
  
  @After
  public void tearDown() {
    mapDriver    = null;
    reduceDriver = null;
  }
  
  @Test
  public void testReducer() throws IOException {
    List<IntWritable> thisInputList = new ArrayList<IntWritable>();
    thisInputList.add(new IntWritable(2));
    thisInputList.add(new IntWritable(1));
    thisInputList.add(new IntWritable(1));
    
    List<IntWritable> isInputList = new ArrayList<IntWritable>();
    isInputList.add(new IntWritable(1));
    
    List<IntWritable> testInputList = new ArrayList<IntWritable>();
    testInputList.add(new IntWritable(3));
    testInputList.add(new IntWritable(1));
    
    final String namedOutputT = "T";
    final String namedOutputI = "I";
    
    reduceDriver.withInput(new Text("This"), thisInputList)
      .withInput(new Text("is"),   isInputList)
      .withInput(new Text("test"), testInputList)
      .withMultiOutput(namedOutputT, new Text("This"), new IntWritable(4))
      .withMultiOutput(namedOutputT, new Text("test"), new IntWritable(4))
      .withMultiOutput(namedOutputI, new Text("is"),   new IntWritable(1));
    
    //
    // MRUnit 1.1.0 compatible
    //
//    reduceDriver.run();
//    
//    Map<FirstLetterCounter, Long> expectedCounts = new HashMap<>();
//    expectedCounts.put(FirstLetterCounter.T_COUNT, 8L);
//    expectedCounts.put(FirstLetterCounter.I_COUNT, 1L);
//    Counters counters = reduceDriver.getCounters();
//    
//    TestUtils.validateCounters(
//        counters, expectedCounts, FirstLetterCounter.class);
    
    //
    // MRUnit 1.2.0 compatible
    //
    reduceDriver.runTest(false);
  }
}
