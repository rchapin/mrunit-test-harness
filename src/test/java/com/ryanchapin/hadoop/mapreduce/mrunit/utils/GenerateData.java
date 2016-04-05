package com.ryanchapin.hadoop.mapreduce.mrunit.utils;

import java.io.File;

import org.apache.avro.file.CodecFactory;

public abstract class GenerateData {
  
  protected String outputPath;
  protected File outputFile;
  protected static final CodecFactory COMPRESSION_CODEC = CodecFactory.snappyCodec();
  
  protected void init() {
    // Set up our output File
    outputFile = new File(outputPath);
    
    if (outputFile.isDirectory()) {
      throw new IllegalArgumentException("The outputPath provided " +
          "[" + outputPath + "] is a directory.");
    }
    if (outputFile.exists()) {
      outputFile.delete();
    }
  }
  
  protected abstract void generateData();
}
