package com.daniel.martins.youtubeclone;

import com.daniel.martins.youtubeclone.model.User;
import com.daniel.martins.youtubeclone.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.daniel.martins.youtubeclone")
public class YoutubeCloneApplication {

	private final UserRepository userRepo;

	@Autowired
	public YoutubeCloneApplication(UserRepository userRepo) {
		this.userRepo = userRepo;
	}

	public static void main(String[] args) {
		SpringApplication.run(YoutubeCloneApplication.class, args);
	}

//	@Override
//	public void run(ApplicationArguments args) throws Exception {
//		User user = new User();
//		user.setUsername("user2");
//		user.setPassword("$2a$10$4EvCE3wPMBPYEV/FA8B.3e1mrlCGaVuq.cO0x0fmrt198H61q/dFG");
//		userRepo.save(user);
//
//	}
}
