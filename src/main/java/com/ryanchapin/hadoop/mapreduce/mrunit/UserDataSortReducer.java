package com.ryanchapin.hadoop.mapreduce.mrunit;

import java.io.IOException;

import org.apache.avro.mapred.AvroKey;
import org.apache.avro.mapred.AvroValue;
import org.apache.hadoop.mapreduce.Reducer;

import com.ryanchapin.hadoop.mapreduce.mrunit.avro.User;

public class UserDataSortReducer extends
  Reducer<AvroKey<Integer>, AvroValue<User>, AvroKey<Integer>, AvroValue<User>>
{
  @Override
  public void reduce(AvroKey<Integer> key,
                     Iterable<AvroValue<User>> values,
                     Context context)
    throws IOException, InterruptedException
  {
    for (AvroValue<User> avroValue : values) {
      context.write(key, avroValue);
    }
  }
}