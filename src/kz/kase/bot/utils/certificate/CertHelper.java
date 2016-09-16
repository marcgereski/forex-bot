package kz.kase.bot.utils.certificate;

import java.math.BigInteger;
import java.security.cert.X509Certificate;


public class CertHelper {
    public static final BigInteger MAX_BI = new BigInteger("115792089237316195423570985008687907853269984665640564039457584007913129639936");

    public static String getStandardSerial(X509Certificate cert) {
        if(cert == null) {
            return null;
        } else {
            BigInteger serialBi = cert.getSerialNumber();
            if(serialBi.max(BigInteger.ZERO).equals(BigInteger.ZERO)) {
                serialBi = MAX_BI.add(serialBi);
            }

            String serial = serialBi.toString(16);

            for(int i = 64 - serial.length(); i > 0; --i) {
                serial = "0" + serial;
            }

            return serial.toUpperCase();
        }
    }
}
