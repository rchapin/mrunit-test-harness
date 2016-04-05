package com.ryanchapin.hadoop.mapreduce.mrunit;

import org.apache.avro.Schema;
import org.apache.avro.file.CodecFactory;
import org.apache.avro.mapred.AvroKey;
import org.apache.avro.mapred.AvroValue;
import org.apache.avro.mapreduce.AvroJob;
import org.apache.avro.mapreduce.AvroKeyValueInputFormat;
import org.apache.avro.mapreduce.AvroKeyValueOutputFormat;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import com.ryanchapin.hadoop.mapreduce.mrunit.avro.User;
import com.ryanchapin.hadoop.mapreduce.mrunit.utils.FileUtils;

public class UserDataSort extends Configured implements Tool {
  
  protected static final CodecFactory COMPRESSION_CODEC = CodecFactory.snappyCodec();
  private static final String JOB_NAME = "UserDataSort";
  
  public UserDataSort() {}
  
  @Override
  public int run(String[] args) throws Exception {
    Configuration conf = getConf();
    conf.setBoolean("mapred.input.compress", true);
    
    Job job = Job.getInstance(conf);
    job.setJarByClass(UserDataSort.class);
    
    Path inputPath  = new Path(args[0]);
    Path outputPath = new Path(args[1]);
    
    // Check that the output path does not already exist and clean-up if need be.
    FileUtils.deletePath(conf, outputPath, true);
    
    FileInputFormat.setInputPaths(job, inputPath);
    FileOutputFormat.setOutputPath(job, outputPath);
    
    // Configure the Avro k/v pair types for Mapper and Reducer output.
    AvroJob.setInputKeySchema(job, Schema.create(Schema.Type.INT));
    AvroJob.setInputValueSchema(job, User.SCHEMA$);
    AvroJob.setMapOutputKeySchema(job, Schema.create(Schema.Type.INT));
    AvroJob.setMapOutputValueSchema(job, User.SCHEMA$);
    AvroJob.setOutputKeySchema(job, Schema.create(Schema.Type.INT));
    AvroJob.setOutputValueSchema(job, User.SCHEMA$);
    
    job.setJobName(JOB_NAME);
    
    job.setInputFormatClass(AvroKeyValueInputFormat.class);
    job.setOutputFormatClass(AvroKeyValueOutputFormat.class);
    
    job.setMapOutputKeyClass(AvroKey.class);
    job.setMapOutputValueClass(AvroValue.class);
    job.setOutputKeyClass(AvroKey.class);
    job.setOutputValueClass(AvroValue.class);
    
//    job.setMapperClass(UserDataSortMapper.class);
//    job.setReducerClass(UserDataSortReducer.class);
 
    job.setNumReduceTasks(2);
    
    try {
      job.submit();
      job.waitForCompletion(true);
    } catch (Exception e) {
      e.printStackTrace();
      return (1);
    }
   
   return (0);
  }
  
  public static void main(String[] args) throws Exception {
    int retVal = ToolRunner.run(new UserDataSort(), args);
    
    if (retVal != 0) {
      throw new IllegalStateException(
          String.format(
              "Execution of UserDataSort M/R job completed with exit code",
              retVal)
          );
    }
  }
}