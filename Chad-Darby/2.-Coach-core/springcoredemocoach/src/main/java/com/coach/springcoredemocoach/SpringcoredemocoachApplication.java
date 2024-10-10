package com.coach.springcoredemocoach;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/* En esta annotation podemos definir una lista de paquetes que queremos que escanee, por 
defecto solo escanea las anotaciones que estan dentro del paquete principal 
(springcoredemocoach) y no las de otros paquetes (com.coach.utils) */

@SpringBootApplication(
	scanBasePackages = {"com.coach.springcoredemocoach",
						"com.coach.util"})
						
public class SpringcoredemocoachApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringcoredemocoachApplication.class, args);
	}

}
