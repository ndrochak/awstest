# awstest

This is our test project.  AWS works great (once it's working)

## building locally

Easy!

<code>
./gradlew build
</code>

## AWS CodePipeline
1. Source: GitHub
2. Build: AWS CodeBuild
3. Deploy: AWS CloudFormation

## building on AWS CodeBuild

* This project uses image: aws/codebuild/java:openjdk-8
* Be wary of the AWS documentation.  It has been incorrect multiple times, which has led to much spinning.  Trust, but verify.

## AWS CodeBuild error messages I have known and loved
* YAML_FILE_ERROR Message: Cannot unmarshal map to string
  * This means the buildspec.yml file is bad because...?
  * Cut losses and revert to last working yaml file or use the "Insert build commands" template
* ./gradlew: Permission denied
  * You might think the gradle file does not have correct permissions.  You *might* be right!
  * When AWS obtains source it does not appear to retain permissions because reasons
  * However, gradle *will* run if the build is started manually ¯\_(ツ)_/¯
  * To workaround: add chmod +x gradlew to buildspec
* ERROR: JAVA_HOME is set to an invalid directory: /usr/lib/jvm/java-8-openjdk-amd64
  * Do not listen to the "Build Specification Reference"
  * http://docs.aws.amazon.com/codebuild/latest/userguide/build-spec-ref.html
  * Ensure the project uses the correct image aws/codebuild/java:openjdk-8
* Skipping invalid artifact path build/distributions/awstest.zip
  * This doesn't mean the path is invalid, it means it can't find the file
  * The build.gradle script provided in "Creating a .zip Deployment package" builds a zip based on the project's directory name
  * http://docs.aws.amazon.com/lambda/latest/dg/create-deployment-pkg-zip-java.html
  * However, running in CodePipeline extracts the GitHub repo into another directory name ("src"). This results in a zipfile named something different.
  * To address: add "baseName" to the buildZip function to control the name of the output zip file.

## AWS CodeBuild output
* CodePipeline has an "Input artifact" and "Output artifact", but this appears to be different from CodeBuild's "Artifacts upload location" / "Bucket name"

## AWS CloudFormation
* http://docs.aws.amazon.com/lambda/latest/dg/automating-deployment.html
* Added a similar "SamTemplate.yaml"
* http://docs.aws.amazon.com/AWSCloudFormation/latest/UserGuide/template-guide.html

## AWS Lambda info
