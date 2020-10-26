package mdaros.labs.spring.cloud.function.aws.s3.consumer.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class SpringConfig {

	@Value ( "${aws.region}" )
	private String region;

	@Value ( "${aws.iam.access.key.id}" )
	private String accessKeyId;

	@Value ( "${aws.iam.secret.access.key}" )
	private String secretAccessKey;


	@Bean
//	@Profile ( "aws" )
	public AmazonS3 awsS3Client () {

		BasicAWSCredentials creds = new BasicAWSCredentials ( accessKeyId, secretAccessKey );

		return AmazonS3ClientBuilder
				.standard ()
				.withRegion ( region )
				.withCredentials ( new AWSStaticCredentialsProvider ( creds ) )
				.build ();
	}

//	@Bean
//	@Profile("local")
//	public AmazonS3 awsS3ClientLocalStack(){
//		return AmazonS3ClientBuilder.standard()
//				.withEndpointConfiguration(new AwsClientBuilder
//						.EndpointConfiguration("http://localstack:4572","us-west-1"))
//				.build();
//	}
}