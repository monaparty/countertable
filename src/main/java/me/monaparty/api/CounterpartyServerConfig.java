package me.monaparty.api;

import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import com.googlecode.jsonrpc4j.JsonRpcHttpClient;
import com.googlecode.jsonrpc4j.ProxyUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CounterpartyServerConfig {
    private static Logger logger = LoggerFactory.getLogger(CounterpartyServerConfig.class);

    @Value("${counterparty.endpoint}")
    private String endpoint;

    @Value("${counterparty.rpcuser}")
    private String rpcuser;

    @Value("${counterparty.rpcpass}")
    private String rpcpass;

    public CounterpartyServerConfig() { 
        Authenticator.setDefault(new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(rpcuser, rpcpass.toCharArray());
            }
        });
    }

    @Bean
    public JsonRpcHttpClient jsonRpcHttpClient() {
        URL url = null;
        Map<String, String> map = new HashMap<>();
        try {
            url = new URL(endpoint);
        } catch (Exception e) {
            logger.error("fatal. Check `counterparty` configs.", e);
        }
        return new JsonRpcHttpClient(url, map);
    }

    @Bean
    public CounterpartyAPI counterpartyAPI(JsonRpcHttpClient jsonRpcHttpClient) {
        return ProxyUtil.createClientProxy(getClass().getClassLoader(), CounterpartyAPI.class, jsonRpcHttpClient);
    }
}