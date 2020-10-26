package mdaros.labs.spring.cloud.function.aws.s3.consumer;

import com.amazonaws.services.lambda.runtime.events.S3Event;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.function.Function;

@SpringBootApplication
@Slf4j
public class AwsS3ConsumerApplication {

	public static void main ( String[] args ) {

		SpringApplication.run ( AwsS3ConsumerApplication.class, args );
	}

	@Autowired
	private AmazonS3 amazonS3;


	@Bean
	public Function<S3Event, S3Event> processS3Event () {

		return s3Event -> {

			String bucket = s3Event.getRecords ().get ( 0 ).getS3 ().getBucket ().getName ();
			String key = s3Event.getRecords ().get ( 0 ).getS3 ().getObject ().getKey ();

			log.info ( "Something was uploaded to S3: " + bucket + "/" + key );

			// ... further processing of the S3Ev ent
			S3Object object = amazonS3.getObject ( new GetObjectRequest ( bucket, key ) );

			log.info ( "Retrieved s3 object: {} ", object );
			log.info ( "Metadata for s3 object: {} ", object.getObjectMetadata () );

			return s3Event;
		};
	}
}