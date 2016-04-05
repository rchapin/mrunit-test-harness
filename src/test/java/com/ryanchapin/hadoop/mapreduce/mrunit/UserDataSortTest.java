package com.ryanchapin.hadoop.mapreduce.mrunit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.avro.Schema;
import org.apache.avro.hadoop.io.AvroSerialization;
import org.apache.avro.mapred.AvroKey;
import org.apache.avro.mapred.AvroValue;
import org.apache.avro.mapreduce.AvroJob;
import org.apache.avro.mapreduce.AvroKeyValueInputFormat;
import org.apache.avro.mapreduce.AvroKeyValueOutputFormat;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mrunit.MapReduceDriver;
import org.apache.hadoop.mrunit.mapreduce.MapDriver;
import org.apache.hadoop.mrunit.types.Pair;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.ryanchapin.hadoop.mapreduce.mrunit.avro.User;

public class UserDataSortTest {

  private MapDriver<AvroKey<Integer>, AvroValue<User>, AvroKey<Integer>, AvroValue<User>>
    mapDriver;
  
  private MapReduceDriver<
    AvroKey<Integer>, AvroValue<User>,
    AvroKey<Integer>, AvroValue<User>,
    AvroKey<Integer>, AvroValue<User>> mapReduceDriver;
  
//  MapDriver<
//    AvroKeyValueInputFormat<AvroKey<Integer>, AvroValue<User>>,
//    AvroKeyValue
//    >
//    mapDriver;
//  
//  mapDriver;
//  
  
//  driver = new MapReduceDriver<
//      AvroWrapper<Pair<Utf8, LiveTrackingLine>>,
//      NullWritable,
//      AvroKey<Utf8>,
//      AvroValue<Product>,AvroWrapper<Pair<Utf8, Product>>, NullWritable>
//  ()
//      .withMapper(new DayMapper()).withReducer(new DayReducer()).withConfiguration(configuration);

  
//  private ReduceDriver<AvroKey<Integer>, AvroValue<User>, AvroKey<Integer>, AvroValue<User>>
//    reduceDriver;
  
  @Before
  public void setUp() throws IOException {
    
//    IdentityMapper<AvroKey<Integer>, AvroValue<User>> idMapper = new IdentityMapper<>();
//    IdentityReducer<AvroKey<Integer>, AvroValue<User>> idReducer = new IdentityReducer<>();
//    mapReduceDriver = new MapReduceDriver<>(idMapper, idReducer);

//    Mapper<AvroKey<Integer>, AvroValue<User>, AvroKey<Integer>, AvroValue<User>> m = new UserDataSortMapper();
//    Reducer<AvroKey<Integer>, AvroValue<User>, AvroKey<Integer>, AvroValue<User>> r = new UserDataSortReducer();
//     mapReduceDriver = new MapReduceDriver<>();
//    mapReduceDriver = MapReduceDriver.newMapReduceDriver(new UserDataSortMapper(), new UserDataSortReducer());
//    mapReduceDriver.withMapper(new UserDataSortMapper());
//    mapReduceDriver.withMapper((org.apache.hadoop.mapred.Mapper<AvroKey<Integer>, AvroValue<User>, AvroKey<Integer>, AvroValue<User>>) mapper);
//    mapReduceDriver = new MapReduceDriver<
//        AvroKey<Integer>, AvroValue<User>,
//        AvroKey<Integer>, AvroValue<User>,
//        AvroKey<Integer>, AvroValue<User>>();
//    mapReduceDriver.setMapper(mapper);
    
    Configuration conf = mapReduceDriver.getConfiguration();    
    conf.setStrings("io.serializations", new String[] {
      AvroSerialization.class.getName()
    });
    
    // Configure AvroSerialization by specifying the key writer and value writer schemas
    conf.setStrings("avro.serialization.key.writer.schema", Schema.create(Schema.Type.INT).toString(true));
    conf.setStrings("avro.serialization.value.writer.schema", User.SCHEMA$.toString(true));
   
    Job job = Job.getInstance(conf);
    job.setInputFormatClass(AvroKeyValueInputFormat.class);
    job.setOutputFormatClass(AvroKeyValueOutputFormat.class);
    job.setMapOutputKeyClass(AvroKey.class);
    job.setMapOutputValueClass(AvroValue.class);
    job.setOutputKeyClass(AvroKey.class);
    job.setOutputValueClass(AvroValue.class);
  
    // Configure the Avro k/v pair types for Mapper and Reducer output.
    AvroJob.setInputKeySchema(job, Schema.create(Schema.Type.INT));
    AvroJob.setInputValueSchema(job, User.SCHEMA$);
    AvroJob.setMapOutputKeySchema(job, Schema.create(Schema.Type.INT));
    AvroJob.setMapOutputValueSchema(job, User.SCHEMA$);
    AvroJob.setOutputKeySchema(job, Schema.create(Schema.Type.INT));
    AvroJob.setOutputValueSchema(job, User.SCHEMA$);
   
//    
//    
    
    
    // MapDriver was working, but I could not get the mapReduceDriver configured
    // for AvroData
    mapDriver = new MapDriver<AvroKey<Integer>, AvroValue<User>, AvroKey<Integer>, AvroValue<User>>
      (new UserDataSortMapper());

    
//    
//    IdentityMapper<AvroKey<Integer>, AvroValue<User>> idMapper = new IdentityMapper<>();
//    IdentityReducer<AvroKey<Integer>, AvroValue<User>> idReducer = new IdentityReducer<>();
//    mapReduceDriver = new MapReduceDriver<>(idMapper, idReducer);
//    
//    // Set the serialization configuration for the job.
//    Configuration conf = mapReduceDriver.getConfiguration();
//    conf.setStrings("io.serializations", new String[] {
//        AvroSerialization.class.getName()
//    });
//    
//    //Configure AvroSerialization by specifying the key writer and value writer schemas
//    conf.setStrings("avro.serialization.key.writer.schema", Schema.create(Schema.Type.INT).toString(true));
//    conf.setStrings("avro.serialization.value.writer.schema", User.SCHEMA$.toString(true));
//
//    Job job = Job.getInstance(conf);
//    job.setInputFormatClass(AvroKeyValueInputFormat.class);
//    job.setOutputFormatClass(AvroKeyValueOutputFormat.class);
//    job.setMapOutputKeyClass(AvroKey.class);
//    job.setMapOutputValueClass(AvroValue.class);
//    job.setOutputKeyClass(AvroKey.class);
//    job.setOutputValueClass(AvroValue.class);
//    
////    reduceDriver = new ReduceDriver<AvroKey<Integer>, AvroValue<User>, AvroKey<Integer>, AvroValue<User>>
////      (new UserDataSort.UserDataSortReducer());
//    
//    // Configure the Avro k/v pair types for Mapper and Reducer output.
//    AvroJob.setInputKeySchema(job, Schema.create(Schema.Type.INT));
//    AvroJob.setInputValueSchema(job, User.SCHEMA$);
//    AvroJob.setMapOutputKeySchema(job, Schema.create(Schema.Type.INT));
//    AvroJob.setMapOutputValueSchema(job, User.SCHEMA$);
//    AvroJob.setOutputKeySchema(job, Schema.create(Schema.Type.INT));
//    AvroJob.setOutputValueSchema(job, User.SCHEMA$);
    
  }
  
  @After
  public void tearDown() {
//    mapDriver    = null;
//    reduceDriver = null;
    mapReduceDriver = null;
  }
  
  @Test
  public void testMapper() throws Exception {
    
//    // Print out the classpath
//    ClassLoader sysClassLoader = ClassLoader.getSystemClassLoader();
//    URL[] urls = ((URLClassLoader)sysClassLoader).getURLs();
//    System.out.println("---------------------------------------");
//    for(int i=0; i< urls.length; i++) {
//        System.out.println(urls[i].getFile());
//    }
//    System.out.println("---------------------------------------");
    
    // Thread.sleep(100000L);
    
//    Schema kvSchema = AvroKeyValue.getSchema(
//        Schema.create(Schema.Type.INT),
//        User.SCHEMA$);
    
    // Generate some Avro input records
    List<Pair<AvroKey<Integer>, AvroValue<User>>> inputList = 
        new ArrayList<>();
    List<Pair<AvroKey<Integer>, AvroValue<User>>> outputList = 
        new ArrayList<>();
    
    User user1 = User.newBuilder()
        .setId(10)
        .setEmail("userone@example.com")
        .setFirstName("Bob")
        .setLastName("Fredman")
        .build();
    AvroKey<Integer> record1Key = new AvroKey<>(10);
    AvroValue<User> record1Value = new AvroValue<>(user1);
    Pair<AvroKey<Integer>, AvroValue<User>> record1 =
        new Pair<>(record1Key, record1Value);
    
    User user2 = User.newBuilder()
        .setId(2)
        .setEmail("usertwo@example.com")
        .setFirstName("Ernest")
        .setLastName("Jameson")
        .build();
    AvroKey<Integer> record2Key = new AvroKey<>(2);
    AvroValue<User> record2Value = new AvroValue<>(user2);
    Pair<AvroKey<Integer>, AvroValue<User>> record2 =
        new Pair<>(record2Key, record2Value);
    
    inputList.add(record1);
    inputList.add(record2);
    
    outputList.add(record2);
    outputList.add(record1);
    
    mapReduceDriver.withAll(inputList)
      .withAllOutput(outputList);
    
    mapReduceDriver.runTest(true);
    
//    // Mapper should output the records in the same order in which they were received.
//    mapDriver.withAll(inputList)
//      .withAllOutput(outputList);
//    mapDriver.runTest(true); 
  }
}
