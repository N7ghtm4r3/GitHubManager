
# GitHubManager

**v1.0.1**

               MMM.           .MMM
               MMMMMMMMMMMMMMMMMMM
               MMMMMMMMMMMMMMMMMMM      ___________________________________________
              MMMMMMMMMMMMMMMMMMMMM    |                                           |
             MMMMMMMMMMMMMMMMMMMMMMM   | This is a Java Based library              |
             MMMMMMMMMMMMMMMMMMMMMMM   | useful to work with GitHub's API service! |
            MMMMMMMMMMMMMMMMMMMMMMMM   |_   _______________________________________|
            MMMM::- -:::::::- -::MMMM    |/
             MM~:~ 00~:::::~ 00~:~MM
        .. MMMMM::.00:::+:::.00::MMMMM ..
              .MM::::: ._. :::::MM.
                 MMMM;:::::;MMMM
          -MM        MMMMMMM
          ^  M+     MMMMMMMMM
              MMMMMMM MM MM MM
                   MM MM MM MM
                   MM MM MM MM
                .~~MM~MM~MM~MM~~.
             ~~~~MM:~MM~~~MM~:MM~~~~
            ~~~~~~==~==~~~==~==~~~~~~
             ~~~~~~==~==~==~==~~~~~~
                 :~==~==~==~==~~
                 
## Implementation

Add the JitPack repository to your build file

### Gradle

- Add it in your root build.gradle at the end of repositories

    #### Gradle (Short)
         
    ```gradle
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
    ```

    #### Gradle (Kotlin)
         
    ```gradle
    repositories {
        ...
        maven("https://jitpack.io")
    }
    ```
    
- Add the dependency

    #### Gradle (Short)
         
    ```gradle
    dependencies {
        implementation 'com.github.N7ghtm4r3:GitHubManager:1.0.1'
    }
    ```

    #### Gradle (Kotlin)
         
    ```gradle
    dependencies {
        implementation("com.github.N7ghtm4r3:GitHubManager:1.0.1")
    }
    ```

### Maven

- Add it in your root build.gradle at the end of repositories

```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```
- Add the dependency

```xml
<dependency>
    <groupId>com.github.N7ghtm4r3</groupId>
    <artifactId>GitHubManager</artifactId>
    <version>1.0.1</version>
</dependency>
```

## 🛠 Skills
- Java

## Usage/Examples

### Creating access token

To use correctly the library you must before create the access token to pass to the managers to correctly perform their 
workflow, you can follow how do to it <a href="https://docs.github.com/en/authentication/keeping-your-account-and-data-secure/creating-a-personal-access-token">here</a>  

### Execution

```java

try{
    GitHubUsersManager gitHubUsersManager = new GitHubUsersManager();
    gitHubUsersManager.getUsers();
}catch(Exception e){
    e.printStackTrace();
 }
```

To avoid re-entering credentials for each manager, you can instantiate managers like this with the **ARCS**:

```java
// choose the manager (for signed and no-signed managers same procedure), for example: GitHubUsersManager, GitHubRepositoriesManager, etc 
GitHubManager firstManager = new GitHubManager(/* params of the constructor chosen */, "accessToken");
// and then use it 
firstManager.makeSomething();
// you don't need to insert all credentials to make manager work
GitHubManager secondManager = new GitHubManager(); // same credentials used
// and then use it
secondManager.makeSomething();
```

### Responses

Library give to you the opportunity to customize the return object after a request, the possibilities are:

- **JSON:** return response formatted as **JSON** (**org.json.JSONObject** or **org.json.JSONArray**)
- **STRING:** return response formatted as **String**
- **LIBRARY_OBJECT:** return response formatted as custom object offered by the library

```java
// choose the manager for example: GitHubUsersManager, GitHubRepositoriesManager, etc
GitHubManager manager = new GitHubManager(/* params of the constructor chosen */);
// method to return directly a library given by library
manager.someRequest(); // in this case will be returned directly a LIBRARY_OBJECT
// method to customize the format of the return 
manager.someRequest(ReturnFormat.JSON); // in this case will be returned response in JSON format
```

### Errors handling

```java
try{
    System.out.println(manager.getUsers());
}catch(Exception e){
    System.out.println(manager.getErrorResponse());
    //or
    manager.printErrorResponse();
}

/* NOTE: if is not a request error will appear: "Error is not in api request, check out your code"
  and you will have to work on your code to manage error*/
```

## Authors

- [@N7ghtm4r3](https://www.github.com/N7ghtm4r3)

## Support

If you need help using the library or encounter any problems or bugs, please contact us via the following links:

- Support via <a href="mailto:infotecknobitcompany@gmail.com">email</a>
- Support via <a href="https://github.com/N7ghtm4r3/GitHubManager/issues/new">GitHub</a>

Thank you for your help!

## Badges

[![](https://img.shields.io/badge/Google_Play-414141?style=for-the-badge&logo=google-play&logoColor=white)](https://play.google.com/store/apps/developer?id=Tecknobit)
[![Twitter](https://img.shields.io/badge/Twitter-1DA1F2?style=for-the-badge&logo=twitter&logoColor=white)](https://twitter.com/tecknobit)

[![](https://img.shields.io/badge/GitHub-100000?style=for-the-badge&logo=github&logoColor=white)](https://docs.github.com/en/rest)
[![](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)](https://www.oracle.com/java/)

[![](https://jitpack.io/v/N7ghtm4r3/GitHubManager.svg)](https://jitpack.io/#N7ghtm4r3/GitHubManager)

## Donations

If you want support project and developer

| Crypto  | Address| Network |
| ------------- | ------------- | ------------- |
| ![](https://img.shields.io/badge/Bitcoin-000000?style=for-the-badge&logo=bitcoin&logoColor=white) | **3H3jyCzcRmnxroHthuXh22GXXSmizin2yp** | Bitcoin |
| ![](https://img.shields.io/badge/Ethereum-3C3C3D?style=for-the-badge&logo=Ethereum&logoColor=white)  | **0x1b45bc41efeb3ed655b078f95086f25fc83345c4**  | Ethereum |

If you want support project and developer
with <a href="https://www.paypal.com/donate/?hosted_button_id=5QMN5UQH7LDT4">PayPal</a>

Copyright © 2024 Tecknobit
