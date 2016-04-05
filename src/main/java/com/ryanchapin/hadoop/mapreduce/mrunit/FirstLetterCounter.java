package com.ryanchapin.hadoop.mapreduce.mrunit;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum FirstLetterCounter {
  A_COUNT("A"), B_COUNT("B"), C_COUNT("C"), D_COUNT("D"), E_COUNT("E"),
  F_COUNT("F"), G_COUNT("G"), H_COUNT("H"), I_COUNT("I"), J_COUNT("J"),
  K_COUNT("K"), L_COUNT("L"), M_COUNT("M"), N_COUNT("N"), O_COUNT("O"),
  P_COUNT("P"), Q_COUNT("Q"), R_COUNT("R"), S_COUNT("S"), T_COUNT("T"),
  U_COUNT("U"), V_COUNT("V"), W_COUNT("W"), X_COUNT("X"), Y_COUNT("Y"),
  Z_COUNT("Z");
  
  private String letter;
  
  private static final Map<String, FirstLetterCounter> LETTER_MAP = 
      initLetterMap();

  private static Map<String, FirstLetterCounter> initLetterMap() {
    return Collections.unmodifiableMap(new HashMap<String, FirstLetterCounter>() {
      private static final long serialVersionUID = 0L;
      {
        put("A", A_COUNT);
        put("B", B_COUNT);
        put("C", C_COUNT);
        put("D", D_COUNT);
        put("E", E_COUNT);
        put("F", F_COUNT);
        put("G", G_COUNT);
        put("H", H_COUNT);
        put("I", I_COUNT);
        put("J", J_COUNT);
        put("K", K_COUNT);
        put("L", L_COUNT);
        put("M", M_COUNT);
        put("N", N_COUNT);
        put("O", O_COUNT);
        put("P", P_COUNT);
        put("Q", Q_COUNT);
        put("R", R_COUNT);
        put("S", S_COUNT);
        put("T", T_COUNT);
        put("U", U_COUNT);
        put("V", V_COUNT);
        put("W", W_COUNT);
        put("X", X_COUNT);
        put("Y", Y_COUNT);
        put("Z", Z_COUNT);
      }
    });
  }
  
  public static FirstLetterCounter getWordReducerCounter(String letter) {
    return LETTER_MAP.get(letter.toUpperCase());
    
//    String letterUC = letter.toUpperCase();
//    for (FirstLetterCounter wrc : FirstLetterCounter.values()) {
//      if (wrc.getLetter().equals(letterUC)) {
//        return wrc;
//      }
//    }
//    return null;
    
  }
  
  public String getLetter() {
    return letter;
  }
  
  private FirstLetterCounter(String letter) {
    this.letter = letter;
  }
  

}