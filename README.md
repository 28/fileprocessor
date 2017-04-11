# filer

The main motivation for this library is to have various file processing functionalities
in one place. Word "file" means HTML, XML, text or any other file type. It also means
directories.

## Usage

For directory processing use functions from
`com.paranoidtimes.filer.directory-processor` namespace.

For raw/text file processing use functions from
`com.paranoidtimes.filer.raw-file-processor` namespace.

For XML file processing use functions from
`com.paranoidtimes.filer.xml-processor` namespace.

For HTML file processing use functions from
`com.paranoidtimes.filer.html.html-processor` and
`com.paranoidtimes.filer.html.html-assertion-helpers`
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

MIT License

Copyright (c) 2014 Dejan Josifović

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

