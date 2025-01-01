package org.example.models;

import java.util.HashMap;
import java.util.Map;

public enum PieceType {
   X, O, $, Z;

   private static final  Map<Integer, PieceType> indexWisePieceTypeMap = new HashMap<>();
    static {
        indexWisePieceTypeMap.put(0, X);
        indexWisePieceTypeMap.put(1, O);
        indexWisePieceTypeMap.put(2, $);
        indexWisePieceTypeMap.put(3, Z);

    }
    public static PieceType getPieceType(int index){
        return indexWisePieceTypeMap.get(index);
    }
}
