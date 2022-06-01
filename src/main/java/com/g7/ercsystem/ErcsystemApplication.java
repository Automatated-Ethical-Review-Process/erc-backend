package com.g7.ercsystem;

import com.g7.ercsystem.repository.RoleRepository;
import com.g7.ercsystem.rest.auth.model.EnumRole;
import com.g7.ercsystem.rest.auth.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class ErcsystemApplication extends SpringBootServletInitializer implements CommandLineRunner {

	@Autowired
	private RoleRepository roleRepository;

	public static void main(String[] args) {
		SpringApplication.run(ErcsystemApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		if(roleRepository.findAll().isEmpty()){
			Role role1 = new Role(EnumRole.ROLE_APPLICANT);
			Role role2 = new Role(EnumRole.ROLE_REVIEWER);
			Role role3 = new Role(EnumRole.ROLE_CLERK);
			Role role4 = new Role(EnumRole.ROLE_SECRETORY);
			Role role5 = new Role(EnumRole.ROLE_ADMIN);

			roleRepository.save(role1);
			roleRepository.save(role2);
			roleRepository.save(role3);
			roleRepository.save(role4);
			roleRepository.save(role5);
		}
	}
}
