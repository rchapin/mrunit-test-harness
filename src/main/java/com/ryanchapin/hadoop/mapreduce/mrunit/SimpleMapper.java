package com.ryanchapin.hadoop.mapreduce.mrunit;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class SimpleMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

  private static final IntWritable ONE = new IntWritable(1);
  
  @Override
  public void map(LongWritable key, Text value, Context context)
    throws IOException, InterruptedException
  {
    // Simply split each line on a space and count each word
    String line = value.toString();
    
    for (String word : line.split("\\s+")) {
      context.write(new Text(word), ONE);
    }
  }
}
