# guava-mini
<a href="https://github.com/davidmoten/guava-mini/actions/workflows/ci.yml"><img src="https://github.com/davidmoten/guava-mini/actions/workflows/ci.yml/badge.svg"/></a><br/>
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.davidmoten/guava-mini/badge.svg?style=flat)](https://maven-badges.herokuapp.com/maven-central/com.github.davidmoten/guava-mini)<br/>
[![codecov](https://codecov.io/gh/davidmoten/guava-mini/branch/master/graph/badge.svg)](https://codecov.io/gh/davidmoten/guava-mini)<br/>
Some popular utilities from Guava repackaged (with different package names but same class names and method names) into a little jar (11K) available on Maven Central.

Compatible with java 6 and later.

Status: *on Maven Central*

Maven site reports are [here](http://davidmoten.github.io/guava-mini/index.html) including [javadoc](http://davidmoten.github.io/guava-mini/apidocs/index.html).

Features
-----------

Taken from Guava:

* `Optional`
* `Objects`: `equal`, `hashCode`
* `Preconditions`:`checkNotNull`, `checkArgument`
* `Lists`: `newArrayList`
* `Sets`: `newHashSet`

Non-guava:

* `Maps`: `hashMap`, `treeMap`, `linkedHashMap`
```java
Map<Integer, String> map = Maps.put(1, "hi").put(2, "there").build();
```

Getting started
------------------
Add this dependency to your pom.xml:
```xml
<dependency>
  <groupId>com.github.davidmoten</groupId>
  <artifactId>guava-mini</artifactId>
  <version>VERSION_HERE</version>
</dependency>
```
