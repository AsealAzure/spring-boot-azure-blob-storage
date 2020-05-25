# Spring Boot App for operation on Azure Blob Storage

## Key concepts
This project is an example for communicating with the Azure Blob storage using the Spring Boot.

### Prerequisites

* An Azure subscription
* A [Java Development Kit (JDK)](http://www.oracle.com/technetwork/java/javase/downloads/), version >= 1.8.
* Gradle

## Getting started

## Configuration

The access of the Azure Blob Storage is based on the connection string of the storage account. We can access 
the connection string details under the Access Keys section. Refer the screenshot below.

![](img/AzureConnectionString.png)

Next thing to grab is container name. Under the storage account there is a tab sections which lists all the containers for
 the storage account. Refer the image to know it.

![](img/AzureStorageContainer.png)

Now we can set this as part of the application.yml. Go to src/main/resources for the file.
```yaml
azure:
  storage:
    connection-string: ${AZURE_STORAGE_CONNECTION_STRING}
    container-name: ${AZURE_STORAGE_CONTAINER_NAME}
```

Or we pass it as spring boot env arguments.

## Build with Gradle
``
gradlew clean build
``
## Run with Gradle
``
gradlew bootRun
``

