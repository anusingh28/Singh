package sources;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.apache.commons.net.util.Base64;

public class MyCrypto {

    
    public static String encrypt(String texto) {
        return new String(Base64.encodeBase64(texto.getBytes()));
    }

    
    public static String decrypt(String texto) {
        return new String(Base64.decodeBase64(texto.getBytes()));
    }
}