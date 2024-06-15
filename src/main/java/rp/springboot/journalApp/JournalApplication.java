package rp.springboot.journalApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableTransactionManagement //Tranasctional can be achieved if we have database replication
public class JournalApplication {

	public static void main(String[] args) {
		SpringApplication.run(JournalApplication.class, args);
	}

	//We have to create this PlatformTransactionManager Bean because spring will search for this bean
	//to implement TransactionManagement
//	@Bean
//	public PlatformTransactionManager add (MongoDatabaseFactory dbFactory){ //MongoDatabaseFactory used for connecting to DB
//		return new MongoTransactionManager(dbFactory) ;   	//All the transactions in Db are done by MongoDatabaseFactory
//	}
}
