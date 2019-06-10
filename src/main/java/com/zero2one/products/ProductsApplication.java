package com.zero2one.products;

import com.google.common.collect.Lists;
import javax.sql.DataSource;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

@SpringBootApplication
public class ProductsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductsApplication.class, args);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	CommandLineRunner init(DataSource dataSource, PasswordEncoder passwordEncoder) {
		JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager();
		jdbcUserDetailsManager.setDataSource(dataSource);
		return new CommandLineRunner() {
			@Override
			public void run(String... args) throws Exception {
				try {
					User manuel = new User("usuario1", passwordEncoder.encode("1"), Lists.newArrayList(new SimpleGrantedAuthority("USER")));
					User maria = new User("usuario2", passwordEncoder.encode("2"), Lists.newArrayList(new SimpleGrantedAuthority("USER")));
					User toni = new User("usuario3", passwordEncoder.encode("3"), Lists.newArrayList(new SimpleGrantedAuthority("USER")));
					jdbcUserDetailsManager.createUser(manuel);
					jdbcUserDetailsManager.createUser(maria);
					jdbcUserDetailsManager.createUser(toni);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
	}
}
