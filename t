[33mcommit cf01e2aaf9eec1da7597e686a193c0423a9ad39d[m[33m ([m[1;36mHEAD -> [m[1;32mimpl[m[33m, [m[1;31mBuildingManagement/impl[m[33m, [m[1;32mmaster[m[33m)[m
Author: DennisFadeev <dennisfadeev@web.de>
Date:   Mon Jul 4 13:33:53 2022 +0200

    added protoc dependency and changed .proto location

[1mdiff --git a/.idea/misc.xml b/.idea/misc.xml[m
[1mindex 76a6d53..6372894 100644[m
[1m--- a/.idea/misc.xml[m
[1m+++ b/.idea/misc.xml[m
[36m@@ -5,9 +5,10 @@[m
     <option name="originalFiles">[m
       <list>[m
         <option value="$PROJECT_DIR$/pom.xml" />[m
[31m-        <option value="$PROJECT_DIR$/pom.xml" />[m
       </list>[m
     </option>[m
   </component>[m
[31m-  <component name="ProjectRootManager" version="2" languageLevel="JDK_18" default="true" project-jdk-name="openjdk-18" project-jdk-type="JavaSDK" />[m
[32m+[m[32m  <component name="ProjectRootManager" version="2" languageLevel="JDK_18" default="true" project-jdk-name="openjdk-18" project-jdk-type="JavaSDK">[m
[32m+[m[32m    <output url="file://$PROJECT_DIR$/out" />[m
[32m+[m[32m  </component>[m
 </project>[m
\ No newline at end of file[m
[1mdiff --git a/pom.xml b/pom.xml[m
[1mindex b867969..2af58fd 100644[m
[1m--- a/pom.xml[m
[1m+++ b/pom.xml[m
[36m@@ -17,6 +17,16 @@[m
         <java.version>18</java.version>[m
     </properties>[m
     <dependencies>[m
[32m+[m[32m        <dependency>[m
[32m+[m[32m            <groupId>io.grpc</groupId>[m
[32m+[m[32m            <artifactId>grpc-stub</artifactId>[m
[32m+[m[32m            <version>1.30.0</version>[m
[32m+[m[32m        </dependency>[m
[32m+[m[32m        <dependency>[m
[32m+[m[32m            <groupId>io.grpc</groupId>[m
[32m+[m[32m            <artifactId>grpc-protobuf</artifactId>[m
[32m+[m[32m            <version>1.30.0</version>[m
[32m+[m[32m        </dependency>[m
         <dependency>[m
             <groupId>org.springframework.boot</groupId>[m
             <artifactId>spring-boot-starter-data-rest</artifactId>[m
[36m@@ -51,6 +61,13 @@[m
     </dependencies>[m
 [m
     <build>[m
[32m+[m[32m        <extensions>[m
[32m+[m[32m            <extension>[m
[32m+[m[32m                <groupId>kr.motd.maven</groupId>[m
[32m+[m[32m                <artifactId>os-maven-plugin</artifactId>[m
[32m+[m[32m                <version>1.6.1</version>[m
[32m+[m[32m            </extension>[m
[32m+[m[32m        </extensions>[m
         <plugins>[m
             <plugin>[m
                 <groupId>org.springframework.boot</groupId>[m
[36m@@ -64,6 +81,28 @@[m
                     </excludes>[m
                 </configuration>[m
             </plugin>[m
[32m+[m[32m            <plugin>[m
[32m+[m[32m                <groupId>org.xolstice.maven.plugins</groupId>[m
[32m+[m[32m                <artifactId>protobuf-maven-plugin</artifactId>[m
[32m+[m[32m                <version>0.6.1</version>[m
[32m+[m[32m                <configuration>[m
[32m+[m[32m                    <protocArtifact>[m
[32m+[m[32m                        com.google.protobuf:protoc:3.3.0:exe:${os.detected.classifier}[m
[32m+[m[32m                    </protocArtifact>[m
[32m+[m[32m                    <pluginId>grpc-java</pluginId>[m
[32m+[m[32m                    <pluginArtifact>[m
[32m+[m[32m                        io.grpc:protoc-gen-grpc-java:1.4.0:exe:${os.detected.classifier}[m
[32m+[m[32m                    </pluginArtifact>[m
[32m+[m[32m                </configuration>[m
[32m+[m[32m                <executions>[m
[32m+[m[32m                    <execution>[m
[32m+[m[32m                        <goals>[m
[32m+[m[32m                            <goal>compile</goal>[m
[32m+[m[32m                            <goal>compile-custom</goal>[m
[32m+[m[32m                        </goals>[m
[32m+[m[32m                    </execution>[m
[32m+[m[32m                </executions>[m
[32m+[m[32m            </plugin>[m
         </plugins>[m
     </build>[m
 [m
[1mdiff --git a/src/main/resources/building_management.proto b/src/main/proto/building_management.proto[m
[1msimilarity index 100%[m
[1mrename from src/main/resources/building_management.proto[m
[1mrename to src/main/proto/building_management.proto[m
