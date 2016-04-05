/**
 * Autogenerated by Avro
 * 
 * DO NOT EDIT DIRECTLY
 */
package com.ryanchapin.hadoop.mapreduce.mrunit.avro;  
@SuppressWarnings("all")
@org.apache.avro.specific.AvroGenerated
public class User extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"User\",\"namespace\":\"com.ryanchapin.hadoop.mapreduce.mrunit.avro\",\"fields\":[{\"name\":\"id\",\"type\":\"int\"},{\"name\":\"email\",\"type\":\"string\"},{\"name\":\"firstName\",\"type\":\"string\"},{\"name\":\"lastName\",\"type\":\"string\"}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }
  @Deprecated public int id;
  @Deprecated public java.lang.CharSequence email;
  @Deprecated public java.lang.CharSequence firstName;
  @Deprecated public java.lang.CharSequence lastName;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>. 
   */
  public User() {}

  /**
   * All-args constructor.
   */
  public User(java.lang.Integer id, java.lang.CharSequence email, java.lang.CharSequence firstName, java.lang.CharSequence lastName) {
    this.id = id;
    this.email = email;
    this.firstName = firstName;
    this.lastName = lastName;
  }

  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call. 
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return id;
    case 1: return email;
    case 2: return firstName;
    case 3: return lastName;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }
  // Used by DatumReader.  Applications should not call. 
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: id = (java.lang.Integer)value$; break;
    case 1: email = (java.lang.CharSequence)value$; break;
    case 2: firstName = (java.lang.CharSequence)value$; break;
    case 3: lastName = (java.lang.CharSequence)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'id' field.
   */
  public java.lang.Integer getId() {
    return id;
  }

  /**
   * Sets the value of the 'id' field.
   * @param value the value to set.
   */
  public void setId(java.lang.Integer value) {
    this.id = value;
  }

  /**
   * Gets the value of the 'email' field.
   */
  public java.lang.CharSequence getEmail() {
    return email;
  }

  /**
   * Sets the value of the 'email' field.
   * @param value the value to set.
   */
  public void setEmail(java.lang.CharSequence value) {
    this.email = value;
  }

  /**
   * Gets the value of the 'firstName' field.
   */
  public java.lang.CharSequence getFirstName() {
    return firstName;
  }

  /**
   * Sets the value of the 'firstName' field.
   * @param value the value to set.
   */
  public void setFirstName(java.lang.CharSequence value) {
    this.firstName = value;
  }

  /**
   * Gets the value of the 'lastName' field.
   */
  public java.lang.CharSequence getLastName() {
    return lastName;
  }

  /**
   * Sets the value of the 'lastName' field.
   * @param value the value to set.
   */
  public void setLastName(java.lang.CharSequence value) {
    this.lastName = value;
  }

  /** Creates a new User RecordBuilder */
  public static com.ryanchapin.hadoop.mapreduce.mrunit.avro.User.Builder newBuilder() {
    return new com.ryanchapin.hadoop.mapreduce.mrunit.avro.User.Builder();
  }
  
  /** Creates a new User RecordBuilder by copying an existing Builder */
  public static com.ryanchapin.hadoop.mapreduce.mrunit.avro.User.Builder newBuilder(com.ryanchapin.hadoop.mapreduce.mrunit.avro.User.Builder other) {
    return new com.ryanchapin.hadoop.mapreduce.mrunit.avro.User.Builder(other);
  }
  
  /** Creates a new User RecordBuilder by copying an existing User instance */
  public static com.ryanchapin.hadoop.mapreduce.mrunit.avro.User.Builder newBuilder(com.ryanchapin.hadoop.mapreduce.mrunit.avro.User other) {
    return new com.ryanchapin.hadoop.mapreduce.mrunit.avro.User.Builder(other);
  }
  
  /**
   * RecordBuilder for User instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<User>
    implements org.apache.avro.data.RecordBuilder<User> {

    private int id;
    private java.lang.CharSequence email;
    private java.lang.CharSequence firstName;
    private java.lang.CharSequence lastName;

    /** Creates a new Builder */
    private Builder() {
      super(com.ryanchapin.hadoop.mapreduce.mrunit.avro.User.SCHEMA$);
    }
    
    /** Creates a Builder by copying an existing Builder */
    private Builder(com.ryanchapin.hadoop.mapreduce.mrunit.avro.User.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.id)) {
        this.id = data().deepCopy(fields()[0].schema(), other.id);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.email)) {
        this.email = data().deepCopy(fields()[1].schema(), other.email);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.firstName)) {
        this.firstName = data().deepCopy(fields()[2].schema(), other.firstName);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.lastName)) {
        this.lastName = data().deepCopy(fields()[3].schema(), other.lastName);
        fieldSetFlags()[3] = true;
      }
    }
    
    /** Creates a Builder by copying an existing User instance */
    private Builder(com.ryanchapin.hadoop.mapreduce.mrunit.avro.User other) {
            super(com.ryanchapin.hadoop.mapreduce.mrunit.avro.User.SCHEMA$);
      if (isValidValue(fields()[0], other.id)) {
        this.id = data().deepCopy(fields()[0].schema(), other.id);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.email)) {
        this.email = data().deepCopy(fields()[1].schema(), other.email);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.firstName)) {
        this.firstName = data().deepCopy(fields()[2].schema(), other.firstName);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.lastName)) {
        this.lastName = data().deepCopy(fields()[3].schema(), other.lastName);
        fieldSetFlags()[3] = true;
      }
    }

    /** Gets the value of the 'id' field */
    public java.lang.Integer getId() {
      return id;
    }
    
    /** Sets the value of the 'id' field */
    public com.ryanchapin.hadoop.mapreduce.mrunit.avro.User.Builder setId(int value) {
      validate(fields()[0], value);
      this.id = value;
      fieldSetFlags()[0] = true;
      return this; 
    }
    
    /** Checks whether the 'id' field has been set */
    public boolean hasId() {
      return fieldSetFlags()[0];
    }
    
    /** Clears the value of the 'id' field */
    public com.ryanchapin.hadoop.mapreduce.mrunit.avro.User.Builder clearId() {
      fieldSetFlags()[0] = false;
      return this;
    }

    /** Gets the value of the 'email' field */
    public java.lang.CharSequence getEmail() {
      return email;
    }
    
    /** Sets the value of the 'email' field */
    public com.ryanchapin.hadoop.mapreduce.mrunit.avro.User.Builder setEmail(java.lang.CharSequence value) {
      validate(fields()[1], value);
      this.email = value;
      fieldSetFlags()[1] = true;
      return this; 
    }
    
    /** Checks whether the 'email' field has been set */
    public boolean hasEmail() {
      return fieldSetFlags()[1];
    }
    
    /** Clears the value of the 'email' field */
    public com.ryanchapin.hadoop.mapreduce.mrunit.avro.User.Builder clearEmail() {
      email = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /** Gets the value of the 'firstName' field */
    public java.lang.CharSequence getFirstName() {
      return firstName;
    }
    
    /** Sets the value of the 'firstName' field */
    public com.ryanchapin.hadoop.mapreduce.mrunit.avro.User.Builder setFirstName(java.lang.CharSequence value) {
      validate(fields()[2], value);
      this.firstName = value;
      fieldSetFlags()[2] = true;
      return this; 
    }
    
    /** Checks whether the 'firstName' field has been set */
    public boolean hasFirstName() {
      return fieldSetFlags()[2];
    }
    
    /** Clears the value of the 'firstName' field */
    public com.ryanchapin.hadoop.mapreduce.mrunit.avro.User.Builder clearFirstName() {
      firstName = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    /** Gets the value of the 'lastName' field */
    public java.lang.CharSequence getLastName() {
      return lastName;
    }
    
    /** Sets the value of the 'lastName' field */
    public com.ryanchapin.hadoop.mapreduce.mrunit.avro.User.Builder setLastName(java.lang.CharSequence value) {
      validate(fields()[3], value);
      this.lastName = value;
      fieldSetFlags()[3] = true;
      return this; 
    }
    
    /** Checks whether the 'lastName' field has been set */
    public boolean hasLastName() {
      return fieldSetFlags()[3];
    }
    
    /** Clears the value of the 'lastName' field */
    public com.ryanchapin.hadoop.mapreduce.mrunit.avro.User.Builder clearLastName() {
      lastName = null;
      fieldSetFlags()[3] = false;
      return this;
    }

    @Override
    public User build() {
      try {
        User record = new User();
        record.id = fieldSetFlags()[0] ? this.id : (java.lang.Integer) defaultValue(fields()[0]);
        record.email = fieldSetFlags()[1] ? this.email : (java.lang.CharSequence) defaultValue(fields()[1]);
        record.firstName = fieldSetFlags()[2] ? this.firstName : (java.lang.CharSequence) defaultValue(fields()[2]);
        record.lastName = fieldSetFlags()[3] ? this.lastName : (java.lang.CharSequence) defaultValue(fields()[3]);
        return record;
      } catch (Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }
}
