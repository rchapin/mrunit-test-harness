package com.ryanchapin.hadoop.mapreduce.mrunit;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.mapreduce.MapDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SimpleMapperTest {

  private MapDriver<LongWritable, Text, Text, IntWritable> mapDriver;
  
  @Before
  public void setUp() {
    mapDriver = new MapDriver<LongWritable, Text, Text, IntWritable>(
        new SimpleMapper());
  }
  
  @After
  public void tearDown() {
    mapDriver = null;
  }
  
  @Test
  public void testMapper() throws IOException {
    mapDriver.withInput(new LongWritable(0),
        new Text("This is test"))
      .withOutput(new Text("This"), new IntWritable(1))
      .withOutput(new Text("is"), new IntWritable(1))
      .withOutput(new Text("test"), new IntWritable(1))
      .runTest();
  }
}
