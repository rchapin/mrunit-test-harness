package com.ryanchapin.hadoop.mapreduce.mrunit;

import java.io.IOException;

import org.apache.avro.mapred.AvroKey;
import org.apache.avro.mapred.AvroValue;
import org.apache.hadoop.mapreduce.Mapper;

import com.ryanchapin.hadoop.mapreduce.mrunit.avro.User;

public class UserDataSortMapper extends
  Mapper<AvroKey<Integer>, AvroValue<User>, AvroKey<Integer>, AvroValue<User>>
{
  @Override
  public void map(AvroKey<Integer> key,
                  AvroValue<User> value,
                  Context context)
    throws IOException, InterruptedException
  {
    context.write(key, value);
  }
}