package fr.deloitte.HRsolution.Backend;


import fr.deloitte.HRsolution.Backend.Services.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BackendApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@Autowired
	private MainService mainService;

	@Override
	public void run(String... args) throws Exception{

		mainService.saveSCFromJsonFile();

	}
}
