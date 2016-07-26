GithubContributionsView
=======
 [ ![Download](https://api.bintray.com/packages/javiergm/maven/GithubContributionsView/images/download.svg) ](https://bintray.com/javiergm/maven/GithubContributionsView/_latestVersion)

<img src="https://raw.githubusercontent.com/javierugarte/GithubContributionsView/master/resources/images/github.png" width="600">

##You can customize the color

<img src="https://raw.githubusercontent.com/javierugarte/GithubContributionsView/master/resources/images/red.png" width="500">

<img src="https://raw.githubusercontent.com/javierugarte/GithubContributionsView/master/resources/images/blue.png" width="500">

<img src="https://raw.githubusercontent.com/javierugarte/GithubContributionsView/master/resources/images/blue_gray.png" width="500">

<img src="https://raw.githubusercontent.com/javierugarte/GithubContributionsView/master/resources/images/green.png" width="500">

<img src="https://raw.githubusercontent.com/javierugarte/GithubContributionsView/master/resources/images/pink.png" width="500">

## Download sample
Download [here](https://raw.githubusercontent.com/javierugarte/GithubContributionsView/master/resources/apk/sample-debug.apk)

##Download library

- Using Gradle

```groovy
    compile 'com.github.javierugarte:githubcontributionsview:1.0.1'
```

- Using Maven

```xml
    <dependency>
        <groupId>com.github.javierugarte</groupId>
        <artifactId>githubcontributionsview</artifactId>
        <version>1.0.1</version>
        <type>pom</type>
    </dependency>
```


## How to use this library


- xml

```xml

	<com.github.javierugarte.GitHubContributionsView
        android:id="@+id/github_contributions_view"
        android:layout_width="match_parent"
        android:layout_height="wrap_content" />
	
```

- java

```java

	GitHubContributionsView contributionView = (GitHubContributionsView) 
		findViewById(R.id.github_contributions_view);
	contributionView.loadUserName("javierugarte");
	
```

By default, the base color is #d6e685 (the first image) and no display the months. You can change this properties.

```java
	contributionView.setBaseColor("#FF8A80");
	contributionView.displayMonth(true);        
```

## Contribute

## About me
####Javier González - [jugarte.es](http://jugarte.es)

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

