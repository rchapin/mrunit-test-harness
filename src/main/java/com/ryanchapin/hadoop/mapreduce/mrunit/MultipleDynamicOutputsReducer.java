package com.ryanchapin.hadoop.mapreduce.mrunit;

import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;

public class MultipleDynamicOutputsReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
  
  private MultipleOutputs<Text, IntWritable> mos;
  
  @Override
  public void setup(Context context) throws IOException, InterruptedException {
    mos = new MultipleOutputs<Text, IntWritable>(context);
    super.setup(context);
  }
  
  @Override
  public void reduce(Text key, Iterable<IntWritable> values, Context context)
    throws IOException, InterruptedException
  {
    // The first char of the key will be the string used for the named output.
    String namedOutput = String.valueOf(key.toString().charAt(0)).toUpperCase();
    
    int counter = 0;
    Iterator<IntWritable> valuesItr = values.iterator();
    while (valuesItr.hasNext()) {
      counter += valuesItr.next().get();
    }

    mos.write(namedOutput, key, new IntWritable(counter));
    context.getCounter(
        FirstLetterCounter.getWordReducerCounter(namedOutput)
        ).increment(counter);
  }
  
  @Override
  public void cleanup(Context context) throws IOException, InterruptedException {
    if (mos != null) {
      mos.close();
    }
    super.cleanup(context);
  }
}
