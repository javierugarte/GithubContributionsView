GithubContributionsView
=======
 [ ![Download](https://api.bintray.com/packages/javiergm/maven/GithubContributionsView/images/download.svg) ](https://bintray.com/javiergm/maven/GithubContributionsView/_latestVersion)

<img src="https://raw.githubusercontent.com/javierugarte/GithubContributionsView/master/resources/images/github.png" width="600">

## You can customize the color

<img src="https://raw.githubusercontent.com/javierugarte/GithubContributionsView/master/resources/images/red.png" width="500">

<img src="https://raw.githubusercontent.com/javierugarte/GithubContributionsView/master/resources/images/blue.png" width="500">

<img src="https://raw.githubusercontent.com/javierugarte/GithubContributionsView/master/resources/images/blue_gray.png" width="500">

<img src="https://raw.githubusercontent.com/javierugarte/GithubContributionsView/master/resources/images/green.png" width="500">

<img src="https://raw.githubusercontent.com/javierugarte/GithubContributionsView/master/resources/images/pink.png" width="500">

## Download sample
Download [here](https://raw.githubusercontent.com/javierugarte/GithubContributionsView/master/resources/apk/sample-debug.apk)

## Download library

- Using Gradle

```groovy
  compile 'com.github.javierugarte:githubcontributionsview:1.2.2'
```

- Using Maven

```xml
  <dependency>
    <groupId>com.github.javierugarte</groupId>
    <artifactId>githubcontributionsview</artifactId>
    <version>1.2.2</version>
    <type>pom</type>
  </dependency>
```


## How to use this library


- java

```java
  GitHubContributionsView contributionView = (GitHubContributionsView)
    findViewById(R.id.github_contributions_view);

  contributionView.loadUserName("javierugarte");
```

- customize programatically

```java
  contributionsView.setBaseColor(R.color.colorAccent);
  contributionsView.setBaseEmptyColor("#EEEEEE");
  contributionsView.setBackgroundBaseColor(Color.TRANSPARENT);
  contributionsView.setLastWeeks(50);
  contributionsView.displayMonth(true);
  contributionsView.setTextColor(R.color.colorPrimary);        
```
-  xml

```xml
  <com.github.javierugarte.GitHubContributionsView
    android:id="@+id/github_contributions_view_attrs"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:layout_margin="10dp"
    app:baseColor="@color/colorAccent"
    app:baseEmptyColor="#0FFF4081"
    app:displayMonth="true"
    app:textColor="@color/colorPrimary"
    app:username="javierugarte" />        
```
- attrs table

| Attr name | Attr format | Example |
|---|---|---|
| baseColor | color | #D6E685 |
| backgroundBaseColor | color | #FFFFFF |
| baseEmptyColor | color | #EEEEEE |
| displayMonth | boolean | true / false |
| textColor | color | #000000 |
| lastWeeks | integer | 50 |
| username | string | javierugarte |


## Contribute
[Issues](https://github.com/javierugarte/GithubContributionsView/issues)

## Apps using this library
 
Want to be here? Open an `issue` or make a `pull request`.

<table>
	<tr>
		<td><img src="https://github.com/gitskarios/Gitskarios/blob/develop/app/src/release/res/mipmap-hdpi/ic_launcher.png?raw=true" width="64"/></td>
		<td><a href="https://github.com/gitskarios/Gitskarios">Gitskarios</a></td>
	</tr>
	<tr>
		<td><img src="https://github.com/GLodi/GitNav/blob/master/app/src/main/res/mipmap-hdpi/ic_launcher.png?raw=true" width="64"></td>
		<td><a href="https://github.com/GLodi/GitNav">GitNav</a></td>
	</tr>
	<tr>
		<td><img src="https://github.com/k0shk0sh/FastHub/blob/master/app/src/main/res/mipmap-hdpi/ic_launcher.png?raw=true" width="64"/></td>
		<td><a href="https://github.com/k0shk0sh/FastHub">FastHub</a></td>
	</tr>
	<tr>
		<td><img src="https://github.com/tpb1908/AndroidProjectsClient/blob/master/app/src/main/res/mipmap-hdpi/ic_launcher.png?raw=true" width="64"/></td>
		<td><a href="https://github.com/tpb1908/AndroidProjectsClient">AndroidProjectsClient</a></td>
	</tr>
</table>

## Contributors
* [Bernat Borrás Paronella](https://github.com/alorma)

## About me
#### Javier González - [jugarte.es](http://jugarte.es)

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
