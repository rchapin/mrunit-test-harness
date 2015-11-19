package com.ryanchapin.hadoop.mapreduce.mrunit;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

import com.sun.jersey.core.impl.provider.entity.XMLJAXBElementProvider.Text;

public class DriverMultipleDynamicOutputs {

  private static final int NUM_ARGS = 2;
  
  public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
    
    // Simple, but not at all flexible, nor the correct way to do it,
    // parsing of cli args to get the input and output paths.
    if (args.length != 2) {
      throw new IllegalArgumentException(String.format(
          "Required number of arguments was not provided.  "
          + "Must include [%d] number of args, <input_path> <output_path>",
          NUM_ARGS));
    }
    String inputPath  = args[0];
    String outputPath = args[1];
    
    Configuration conf = new Configuration();
    Job job = Job.getInstance(conf, "WordCount");
    job.setJarByClass(DriverMultipleDynamicOutputs.class);
    
    // Delete the output path if it already exists
    FileSystem fs = FileSystem.get(conf);
    Path outPath = new Path(outputPath);
    if (fs.exists(outPath)) {
      fs.delete(outPath, true);
    }
    
    job.setInputFormatClass(TextInputFormat.class);
    job.setMapperClass(SimpleMapper.class);
    job.setMapOutputKeyClass(Text.class);
    job.setMapOutputValueClass(IntWritable.class);
    
    job.setCombinerClass(MultipleDynamicOutputsReducer.class);
    job.setReducerClass(MultipleDynamicOutputsReducer.class);
    
    job.setOutputKeyClass(Text.class);
    job.setOutputValueClass(IntWritable.class);
    job.setOutputFormatClass(TextOutputFormat.class);
    
    Path inPath = new Path(inputPath);
    FileInputFormat.addInputPath(job, inPath);
    FileOutputFormat.setOutputPath(job, outPath);
    
    // Set up out MultipleOutputs will be done in the Reducer
    System.exit(job.waitForCompletion(true) ? 0 : 1);
  }
}
