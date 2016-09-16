package kz.kase.bot.utils.certificate;


import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Enumeration;

public class SSLInfo {
    private KeyStore keystore = null;
    private Enumeration enumeration = null;
    private static Logger log = Logger.getLogger(SSLInfo.class.getSimpleName());

    public void load(String keyPath, char[] keyPass) {
        try {
            keystore = KeyStore.getInstance(KeyStore.getDefaultType());
            FileInputStream fis = new FileInputStream(keyPath);
            keystore.load(fis, keyPass);
            enumeration = keystore.aliases();
        } catch (KeyStoreException | NoSuchAlgorithmException | IOException | CertificateException e) {
            log.error("ERROR! " + e.getMessage());
        }
    }

    public void showInfo() throws KeyStoreException {
        if (keystore == null || enumeration == null) return;

        log.info("*** BEGIN CERTIFICATE INFO ***");
        for (; enumeration.hasMoreElements(); ) {
            String alias = (String) enumeration.nextElement();

            java.security.cert.Certificate cert = keystore.getCertificate(alias);
            if (cert instanceof X509Certificate) {
                X509Certificate x509cert = (X509Certificate) cert;

                Principal principal = x509cert.getSubjectDN();
                String subjectDn = principal.getName();

                principal = x509cert.getIssuerDN();
                String issuerDn = principal.getName();

                log.info("   Issuer DN \t: " + issuerDn);
                log.info("   Subject DN\t: " + subjectDn);
                log.info("   Serial    \t: " + CertHelper.getStandardSerial(x509cert));
            }
        }
        log.info("*** END CERTIFICATE INFO ***");
    }
}
