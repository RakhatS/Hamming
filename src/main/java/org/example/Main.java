package org.example;

// Сапарбаев Рахат СИб37

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int powerOfTwo[] = {1,2,4,8,16,32,64,128,256,512,1024,2048,4096,8192,16384,32768,65536}, lengthCode = 0;
        Scanner in = new Scanner(System.in);
        System.out.print("Кодтағыңыз келетін екілік санды енгізіңіз: "); 
        String code = in.nextLine();
        in.close();
        if (code.length() < 2) 
            System.out.println("Өте аз сан.");

        boolean controlBits[] = new boolean[code.length()*2+1];
        int i = 0;

        //---------------KX ЖӘНЕ БАСҚАРУ БИТТЕРІНІҢ ҰЗЫНДЫҒЫН ЕСЕПТЕУ------------------
        do {
            controlBits[powerOfTwo[i]] = true;
            i++;
        } while (powerOfTwo[i] <= (code.length()+1)); // Цикл екінің ЕҢ ТӨМЕН қуатын іздейді, ол > немесе = кіріс кодының ұзындығы.

        lengthCode = code.length() + i;
        //----------------------------------------------------------------------

        //-------------ЕНГІЗІЛГЕН КОДТЫ БАСҚАРУ БИТТЕРІМЕН ЖАЗЫҢЫЗ-----------
        boolean hammingCode[] = new boolean[lengthCode];
        int j = 0;
        for (i = 0; i < lengthCode; i++) { // 
            if (!controlBits[i+1]) {
                hammingCode[i] = (code.charAt(j) == '1');
                j++;
            }
        }
        //----------------------------------------------------------------------

        System.out.print("Басқару биттері бар аралық код: [");
        for (int k = 0; k < lengthCode; k++) {
            char c = (hammingCode[k]) ? '1' : '0';
            if (controlBits[k+1]) System.out.print("!");
            System.out.print(c);
        }
        System.out.print("]\n");

        //---------------------БАСҚАРУ БИТТЕРІНІҢ МӘНІН ЕСЕПТЕУ----------------
        for (i = 0; i < (lengthCode - code.length()); i++) { // Аралық код үшін биттерді орнатамыз
            boolean nextBit = false;
            for (j = powerOfTwo[i]-1; j < lengthCode; j += powerOfTwo[i]*2) {
                for (int n = j; n < j + powerOfTwo[i]; n++) {
                    if (n > lengthCode - 1) break;
                    nextBit ^= hammingCode[n];
                }
            }
            hammingCode[powerOfTwo[i]-1] = nextBit; // i-биттің разрядтың мәнін жазамыз.
        }
        //----------------------------------------------------------------------
        
        System.out.print("Басқару биттері бар Хамминг коды: [");
        for (int k = 0; k < lengthCode; k++) {
            char c = (hammingCode[k]) ? '1' : '0';
            System.out.print(c);
        }
        System.out.print("]\n");
    }
}
