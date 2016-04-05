package com.ryanchapin.hadoop.mapreduce.mrunit.utils;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import org.apache.avro.Schema;
import org.apache.avro.file.CodecFactory;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.hadoop.io.AvroKeyValue;

import com.ryanchapin.hadoop.mapreduce.mrunit.avro.Event;
import com.ryanchapin.hadoop.mapreduce.mrunit.avro.EventType;

/**
 * Class for randomly generating Event data avro records.
 */
public class GenerateEventData extends GenerateData {

  private static final int REQUIRED_NUM_ARGS = 4;
  
  private int maxUserId;
  private int numEvents;
  private String fromTime;
  private long fromTimeStamp;
  private long toTimeStamp;
  private long timeStampDelta;
  
  public GenerateEventData(
      int maxUserId,
      int numEvents,
      String fromTime,
      String outputPath)
  {
    this.maxUserId  = maxUserId;
    this.numEvents  = numEvents;
    this.fromTime   = fromTime;
    this.outputPath = outputPath;
  }
  
  public void init() {
    super.init();

    // Generate the timestamp for the beginning of our time window
    fromTimeStamp = LocalDateTime.parse(
        fromTime, DateTimeFormatter.ISO_DATE_TIME)
          .toEpochSecond(ZoneOffset.UTC);
    
    // Generate the timestamp for the end of the time window
    toTimeStamp = System.currentTimeMillis()/1000;
    
    timeStampDelta = toTimeStamp - fromTimeStamp;
  }
  
  @Override
  public void generateData() {
    // Since the Random api for getting a random is exclusive this makes the
    // code more fluent, and does not incur the cost of adding 1 each time
    // we invoke Random.nextInt();
    int uidUpperBound = maxUserId + 1;
    
    EventType[] eventTypes = EventType.values();
    int eventTypeBound = eventTypes.length - 1;
    
    Random random = new Random();
    
    Event event = new Event();
    long randomLong   = 0L;
    double percentage = 0D;
    long offset       = 0L;
    long timeStamp    = 0L;
    int eventTypeIdx  = 0;
    int uid           = 0;
    
    try {
      // Wrapping the Event records with AvroKeyValue
      Schema kvSchema = AvroKeyValue.getSchema(
          Schema.create(Schema.Type.INT),
          Event.SCHEMA$);
      
      GenericDatumWriter<GenericRecord> datumWriter = new GenericDatumWriter<>();
      DataFileWriter<GenericRecord> dataFileWriter = new DataFileWriter<>(datumWriter);

      dataFileWriter.setCodec(COMPRESSION_CODEC);
      dataFileWriter.create(kvSchema, outputFile);
      
      for (int i = 1; i <= numEvents; i++) {
        uid = random.nextInt(uidUpperBound);
        
        // Calculating a random timestamp between the given range
        randomLong = random.nextLong();
        if (randomLong < 0) {
          randomLong *= -1;
        }
        
        percentage   = ((double)(randomLong % toTimeStamp)) / toTimeStamp;
        offset       = (long) (timeStampDelta * percentage);
        timeStamp    = fromTimeStamp + offset;
        eventTypeIdx = random.nextInt(eventTypeBound);
        
        event.setEventType(eventTypes[eventTypeIdx]);
        event.setUid(uid);
        event.setTimestamp(
            LocalDateTime.ofEpochSecond(timeStamp, 0, ZoneOffset.UTC)
              .toString()
          );
        
        AvroKeyValue<Integer, Event> record = new AvroKeyValue<>(
            new GenericData.Record(kvSchema));
        record.setKey(event.getUid());
        record.setValue(event);
        
        dataFileWriter.append(record.get());
      }
      dataFileWriter.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  public static void main(String[] args) {
    if (args.length != REQUIRED_NUM_ARGS) {
      throw new IllegalArgumentException("Requires " +
          REQUIRED_NUM_ARGS + " number of arguments: " +
          "max-uid-value, " +
          "num-events-to-generate, " +
          "from-time [2014-01-04T23:18:24]" +
          "output-file-path");
    }
    
    int maxUserId     = Integer.parseInt(args[0]);
    int numEvents     = Integer.parseInt(args[1]);
    String outputPath = args[2];
    String fromTime   = args[3];
    
    GenerateEventData gued = new GenerateEventData(
        maxUserId, numEvents, outputPath, fromTime);
    gued.init();
    gued.generateData();
  }
}
