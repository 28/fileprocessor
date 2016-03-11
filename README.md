# fileprocessor

The main motivation for this library is to have various file processing functionalities
in one place. Word "file" means HTML, XML, text or any other file type. It also means
directories.

## Usage

For directory processing use functions from
`com.paranoidtimes.fileprocessor.directory-processor` namespace.

For raw/text file processing use functions from
`com.paranoidtimes.fileprocessor.raw-file-processor` namespace.

For XML file processing use functions from
`com.paranoidtimes.fileprocessor.xml-processor` namespace.

For HTML file processing use functions from
`com.paranoidtimes.fileprocessor.html.html-processor` and
`com.paranoidtimes.fileprocessor.html.html-assertion-helpers`
namespaces.

In the later library has helper methods that provide Java interop that should feel
like native Java coding. Generated uberjar should be imported in the Java project and
used like any other Java 3rd party library

**Detailed usage documentation TBD**

## Idea

The idea for this library came up after compiling all the various scripts
that have been used for various task and that were lying around the work space. The Java
interop part came from the request to use this library's HTML assertion methods from
Java code without even knowing that the jar is actually a Clojure jar.

### HTML assertion idea

The HTML assertion function and everything related to it was inspired by
Ruby on Rails feature of the same name. See following links:

[assert_select in Rails API](http://apidock.com/rails/ActionDispatch/Assertions/SelectorAssertions/assert_select)

and

[assert_select source](https://github.com/rails/rails-dom-testing/blob/0500ae5593a0fa79bb8052ae1c4474960a81440c/lib/rails/dom/testing/assertions/selector_assertions.rb)

## License

Distributed under the Eclipse Public License, the same as Clojure.

Copyright &copy; Dejan Josifović, theparanoidtimes 2014.
