package com.ryanchapin.hadoop.mapreduce.mrunit;

public enum FirstLetterCounter {
  A_COUNT("A"), B_COUNT("B"), C_COUNT("C"), D_COUNT("D"), E_COUNT("E"),
  F_COUNT("F"), G_COUNT("G"), H_COUNT("H"), I_COUNT("I"), J_COUNT("J"),
  K_COUNT("K"), L_COUNT("L"), M_COUNT("M"), N_COUNT("N"), O_COUNT("O"),
  P_COUNT("P"), Q_COUNT("Q"), R_COUNT("R"), S_COUNT("S"), T_COUNT("T"),
  U_COUNT("U"), V_COUNT("V"), W_COUNT("W"), X_COUNT("X"), Y_COUNT("Y"),
  Z_COUNT("Z");
  
  private String letter;
  
  public String getLetter() {
    return letter;
  }
  
  private FirstLetterCounter(String letter) {
    this.letter = letter;
  }
  
  public static FirstLetterCounter getWordReducerCounter(String letter) {
    String letterUC = letter.toUpperCase();
    for (FirstLetterCounter wrc : FirstLetterCounter.values()) {
      if (wrc.getLetter().equals(letterUC)) {
        return wrc;
      }
    }
    return null;
  }
}