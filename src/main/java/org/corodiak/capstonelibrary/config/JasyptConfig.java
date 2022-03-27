package org.corodiak.capstonelibrary.config;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;

@Configuration
@EnableEncryptableProperties
public class JasyptConfig {

	@Value("${jasypt.encryptor.password}")
	String password;

	@Bean("jasyptEncryptorBean")
	public StringEncryptor stringEncryptor() {
		System.out.println("password : " + password);
		PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
		encryptor.setProvider(new BouncyCastleProvider());
		encryptor.setPoolSize(1);
		encryptor.setPassword(password);
		encryptor.setAlgorithm("PBEWithSHA256And128BitAES-CBC-BC");
		return encryptor;
	}

}
