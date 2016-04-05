package com.ryanchapin.hadoop.mapreduce.mrunit.utils;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class FileUtils {

  /**
   * Will delete the given path on HDFS if it already exists.
   * 
   * @param conf
   * @param path
   * @param recursive
   * @throws IOException
   */
  public static boolean deletePath(Configuration conf, Path path, boolean recursive)
      throws IOException
  {
    FileSystem fs = FileSystem.get(conf);
    if (fs.exists(path)) {
      fs.delete(path, recursive);
    }
    return true;
  }
}
