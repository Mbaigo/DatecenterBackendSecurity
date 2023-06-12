package com.datecenter.BackendSecurity.jwtUtils;

import org.springframework.stereotype.Component;

import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@Component
public class RSAKeyProperties {
    private RSAPublicKey rsaPublicKey;
    private RSAPrivateKey rsaPrivateKey;
//le constructeur sans paramètre pour l'initialisation de la paire des clés d'auhtehtification( public et privée)
    public RSAKeyProperties(){
        KeyPair pair =KeyGeneratorUtility.generateRsaKey();
        //initialisation de la clé public
        this.rsaPublicKey = (RSAPublicKey) pair.getPublic();
        //initialisation de la clé privée
        this.rsaPrivateKey = (RSAPrivateKey) pair.getPrivate();
    }

    public RSAPublicKey getRsaPublicKey() {
        return rsaPublicKey;
    }

    public void setRsaPublicKey(RSAPublicKey rsaPublicKey) {
        this.rsaPublicKey = rsaPublicKey;
    }

    public RSAPrivateKey getRsaPrivateKey() {
        return rsaPrivateKey;
    }

    public void setRsaPrivateKey(RSAPrivateKey rsaPrivateKey) {
        this.rsaPrivateKey = rsaPrivateKey;
    }
}
