# AWS S3 Consumer

The aims of this project is to experiment with AWS Lambda functions and Spring Cloud Function
https://spring.io/projects/spring-cloud-function

The function of this example is triggered by a PUT event on a configurable S3 bucket, so that the function will start when you upload a file to this bucket and then can
process this in order to perform a task



### Configuration



#### application.properties
in the **application.properties** file you need to update the following configuration variables in order to specify your preferred AWS Region and your credential informations: the **Access Key ID** and the **Secret Access Key**



```
aws.region=<<YOUR_REGION>>
aws.iam.access.key.id=<<YOUR_ACCESS_KEY_ID>>
aws.iam.secret.access.key=<<YOU_SECRET_ACCESS_KEY>>
```



In case you have not an AWS account yet, you need to create a new one, and review how to use the free tier when possible

https://aws.amazon.com



In case you have not an access key ID, you need to create a new security credential for your user using the IAM section on the AWS console



#### serverless.yml

In the **serverless.yml** configuration file you need to update the following configurations:



You may want to update the info related to your organization and application, this is an optional step you may want to do in case you have an account on Serverless Framework (free or pro account)

`org: <<YOU_ORGANIZATION>>`
`app: <<YOU_APPLICATION_NAME>>`



You need to configure your preferred region:

`region: <<YOUR_REGION>>`



You also nee to configure the S3 bucket where your will upload some files in order to trigger the execution of your function



here:

    iamRoleStatements:
      - Effect: "Allow"
        Action:
         - "s3:*"
        Resource: { "Fn::Join": ["", ["arn:aws:s3:::<<YOUR_BUCKET>>", "/*" ] ] }


and here:

```
functions:
  processS3Event:
    handler: mdaros.labs.spring.cloud.function.aws.s3.consumer.AwsLambdaS3FunctionHandler
    events:
      - s3: <<YOUR_BUCKET>>    
```



### Deployment

You can deploy your function on AWS using the open source Serverless Framework
https://www.serverless.com/

In order to use it you hae to download the CLI for your operating system and then configure the framework
as explained on the getting started guide: https://www.serverless.com/framework/docs/getting-started/

In particular you need to perform the following actions:

**Login to your account**

`serverless login`



**Deploy the function**

`serverless deploy`



If you want to remove the function and all the infrastructure you can use this command:

`serverless remove`