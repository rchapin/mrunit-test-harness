package com.ryanchapin.hadoop.mapreduce.mrunit.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.apache.avro.Schema;
import org.apache.avro.file.CodecFactory;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.hadoop.io.AvroKeyValue;

import com.ryanchapin.hadoop.mapreduce.mrunit.avro.User;

/**
 * Class for generating Avro data from a tsv generated on
 * http://www.fakenamegenerator.com/order.php.
 * 
 * The order of the columns generated:
 *  <ul>
 *    <li>Number (which is the uid)</li>
 *    <li>GivenName<li>
 *    <li>Surname</li>
 *    <li>EmailAddress</li>
 *  </ul>
 */
public class GenerateUserData extends GenerateData {
  
  private static final int REQUIRED_NUM_ARGS = 2;

  private String inputFilePath;
  
  public GenerateUserData(String inputFilePath, String outputFilePath) {
    this.inputFilePath = inputFilePath;
    this.outputPath    = outputFilePath;
  }
  
  @Override
  protected void generateData() {
    try {
      
      // Wrapping the User data records with AvroKeyValue
      Schema kvSchema = AvroKeyValue.getSchema(
          Schema.create(Schema.Type.INT),
          User.SCHEMA$);
      
      GenericDatumWriter<GenericRecord> datumWriter = new GenericDatumWriter<>();
      DataFileWriter<GenericRecord> dataFileWriter  = new DataFileWriter<>(datumWriter);
      dataFileWriter.setCodec(COMPRESSION_CODEC);
      dataFileWriter.create(kvSchema, outputFile);
      
      File inputFile = new File(inputFilePath);
      FileReader fw = new FileReader(inputFile);
      BufferedReader br = new BufferedReader(fw);
      
      String line = null;
      User user = new User();
      
      // Read the first line, because we know it is a header
      br.readLine();
      while ((line = br.readLine()) != null) {
        String[] lineArr = line.split("\\\t");
        
        user.setId(Integer.parseInt(lineArr[0]));
        user.setFirstName(lineArr[1]);
        user.setLastName(lineArr[2]);
        user.setEmail(lineArr[3]);

        AvroKeyValue<Integer, User> record = new AvroKeyValue<>(
            new GenericData.Record(kvSchema));
        record.setKey(user.getId());
        record.setValue(user);
        
        dataFileWriter.append(record.get());
      }
      
      br.close();
      dataFileWriter.close();
      
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
    
  public static void main(String[] args) {
    if (args.length != REQUIRED_NUM_ARGS) {
      throw new IllegalArgumentException("Requires " +
          REQUIRED_NUM_ARGS + " number of arguments: " +
          "input-file-path, " +
          "output-file-path");
    }
    
    String inputFilePath  = args[0];
    String outputFilePath = args[1];

    GenerateUserData gud = new GenerateUserData(inputFilePath, outputFilePath);
    gud.init();
    gud.generateData();
  }
}
