GithubContributionsView
=======


Sample app. Download [here](https://raw.githubusercontent.com/javierugarte/GithubContributionsView/master/resources/apk/sample_debug.apk)

![Screen](https://raw.githubusercontent.com/javierugarte/GithubContributionsView/master/resources/images/screen_app.png)

You can customize the color

![Color red](https://raw.githubusercontent.com/javierugarte/GithubContributionsView/master/resources/images/red.png)

![Color red](https://raw.githubusercontent.com/javierugarte/GithubContributionsView/master/resources/images/blue.png)

## Download


- Using Gradle

```groovy
    compile 'com.github.javierugarte: githubcontributionsview:1.0.0'
```

- Using Maven

```xml
    <dependency>
        <groupId>com.github.javierugarte</groupId>
        <artifactId> githubcontributionsview </artifactId>
        <version>1.0.0</version>
        <type>pom</type>
    </dependency>
```


## How to use this library


- java

```java

	GitHubContributionsView contributionView = (GitHubContributionsView) findViewById(R.id.github_contributions_view);
    contributionView.loadUserName("javierugarte");
	
```

## Contribute

## About me
Javier González - [jugarte.es](http://jugarte.es)

## License

```
Copyright 2016 Javier González

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```

