package com.example.crud.configuration.security.ssl;

import javax.net.ssl.*;
import java.security.cert.X509Certificate;

public class BypassSSLCert {
    public static void crunchifyDisableCertificateValidation() {
        try {
            // SSLContext: Instances of this class represent a secure socket protocol implementation
            // which acts as a factory for secure socket factories or SSLEngines.
            // This class is initialized with an optional set of key and trust managers and source of secure random bytes.
            SSLContext crunchifySSLC = SSLContext.getInstance("TLS");
            TrustManager[] trustManagerArray = {new NullX509TrustManager()};
            crunchifySSLC.init(null, trustManagerArray, null);
            HttpsURLConnection.setDefaultSSLSocketFactory(crunchifySSLC.getSocketFactory());
            // Override the default hostname verifier
            HostnameVerifier allHostsValid = (hostname, session) -> true;
            HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static class NullX509TrustManager implements X509TrustManager {
        public void checkClientTrusted(X509Certificate[] chain, String authType) {
            // do nothing
        }

        public void checkServerTrusted(X509Certificate[] chain, String authType) {
            // do nothing
        }

        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }
}
