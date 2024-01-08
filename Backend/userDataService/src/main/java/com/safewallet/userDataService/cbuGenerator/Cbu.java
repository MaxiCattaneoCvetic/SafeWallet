package com.safewallet.userDataService.cbuGenerator;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class Cbu {

    public static String generateCbu() {
        String CBU = "";

        //generar un cbu
        int limite = 22;
        Random random = new Random(); // para generar numero random
        StringBuilder sb = new StringBuilder();// Generamos un stringbuilder, esto es asi porque es una clase que nos permite modificarla una vez creada

        for(int i = 0; i < limite; i++){
            char digito = (char) (random.nextInt(10) + '0'); // el 0 es para crear valores numericos
            sb.append(digito);
        }

        CBU = String.valueOf(sb);

        return  CBU;
    }

}
