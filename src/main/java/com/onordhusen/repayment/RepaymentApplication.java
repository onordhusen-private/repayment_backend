package com.onordhusen.repayment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Startpunkt der Anwendung.
 *
 * @author Ole Nordhusen
 * @version 1.0.0
 * @since 1.0.0
 */
@SpringBootApplication
class RepaymentApplication {

	/**
	 * Wird von der Java-Runtime beim Start der Anwendung aufgerufen.
	 * Wir übergeben hier Spring die Kontrolle über die Anwendung.
	 *
	 * @param args Start-Parameter der Anwendung.
	 *
	 * @since 1.0.0
	 */
	public static void main(String[] args) {
		SpringApplication.run(RepaymentApplication.class, args);
	}

}
