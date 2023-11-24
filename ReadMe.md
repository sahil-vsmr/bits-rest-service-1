Step 1:

1. Create a project using Spring initializer
2. Add Controller to the project along with get mapping
3. Test app locally by running the spring application
4. Create JAR
5. Deploy jar on EC2 instance - 
   6. ssh -i "BITSServer-KP.cer" ubuntu@ec2-3-110-94-4.ap-south-1.compute.amazonaws.com
   7. scp -i ../BITSServer-KP.cer build/libs/RestService1-0.0.1-SNAPSHOT.jar ubuntu@ec2-3-110-94-4.ap-south-1.compute.amazonaws.com:jar
6. Run jar using - java -jar <jarname>
