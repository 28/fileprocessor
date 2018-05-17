# filer

*filer* is a library that aims to contain a bunch of useful file IO functions in
one place. The functions are mostly related to bulk operations such as bulk
content modification, bulk generation and bulk assertion.

There are different namespaces for different file types, but of course some
functions can be used on file types other than the one defined by the namespace
name. `dir.core` has functions for directory manipulation. `txt.core` has
functions for text file generation and modification. Namespace `xml.core`
currently has only one function that performs a XML file validity check against
an XSD file. Finally, `html.code` namespace focuses on assertion upon HTML file
structure and content.

#### `html.core` namespace

This namespace was created with a specific role in mind - HTML content and
structure assertion. The domain where this idea arose is mass mailing systems.
There is a need to check the output e-mail's structure and content during
testing in order to be sure that the perfect presentation arrives to the
destination.

The idea for the shape of the functions is inspired by this *Ruby on Rails*
[method](https://apidock.com/rails/ActionDispatch/Assertions/SelectorAssertions/assert_select).

## Usage

### `dir.core`

```clojure
(require '[org.theparanoidtimes.filer.dir.core :as dir])
=> nil

(def test-dir "/test-dir/")
=> #'user/test-dir

;; Takes a directory name prefix and a number of directories that should be
;; generated. Outputted directories have names '/test-dit/t0', '/test-dit/t1'.
(dir/generate-n-directories (str test-dir "t") 2)
=> nil

;; Takes a directory name and returns a seq of all files in it. Returns files
;; from nested directories, but not the directories themselves.
(dir/files-in-directory test-dir)
=>
(#object[java.io.File 0x5e24856c "/test-dir/1/1one.txt"]
 #object[java.io.File 0x3330c88a "/test-dir/one.txt"]
 #object[java.io.File 0x2606def2 "/test-dir/three.html"]
 #object[java.io.File 0x62d732f9 "/test-dir/two.xml"])

;; Can also take a set of file extensions and then return files only of the
;; specified type/s.
(dir/files-in-directory test-dir #{".txt"})
=>
(#object[java.io.File 0x5772de8a "/test-dir/1one.txt"]
 #object[java.io.File 0x8701197 "/test-dir/one.txt"])
```

### `txt.core`

```clojure
(require '[org.theparanoidtimes.filer.txt.core :as txt])
=> nil

;; Takes a prefix, a number of files to generate, a file extension and the
;; initial text of the generated files. Outputted files have names
;; /test-dir/t0.txt' and '/test-dir/t1.txt'.
(txt/generate-n-files (str test-dir "t") 2 ".txt" "Test")
=> nil

;; Files generated...
(dir/files-in-directory test-dir)
=>
(#object[java.io.File 0x65598480 "/test-dir/t0.txt"]
 #object[java.io.File 0x3ed5dd8f "/test-dir/t1.txt"])

;; Takes a file, string to replace and string for replacement and replaces
;; all occurrences in the file.
(txt/replace-text-in-file (io/file "/test-dir/t0.txt") "T" "t")
=> nil

;; The text in the file is changed...
(slurp (io/file "/test-dir/t0.txt"))
=> "test"

;; Takes a file and a string for replacement and replaces the whole text in the
;; file with it.
(txt/replace-whole-text-in-file (io/file "/test-dir/t0.txt") "changed")
=> nil

;; The text in the file is changed...
(slurp (io/file "/test-dir/t0.txt"))
=> "changed"

;; A alternative of the replace-text-in-file for multiple files. Replaces all
;; occurrences in all files. Can take a file extensions set.
(txt/replace-text-in-files test-dir "T" "t")
=> nil

;; A alternative of the replace-whole-text-in-file for multiple files. Can take
;; a file extensions set.
(txt/replace-whole-text-in-files test-dir "changed")
=> nil

;; A special case function that generates files for each line of the provided
;; names list file. Each line is a input to a name decorator function. The
;; output of the function is the name of the generated file.
(txt/generate-files-from-names-list (str test-dir "names") s/upper-case "Test")
=> nil
```

### `xml.core`

```clojure
(require '[org.theparanoidtimes.filer.xml.core :as xml])
=> nil

;; Validates the given xml file against the xsd file.
(xml/validate-xml-against-xsd xml-file xsd-file)
=> true
```

### `html.core`

```clojure
(require '[org.theparanoidtimes.filer.html.core :as html])
=> nil

TBD
```

## License

MIT License

Copyright (c) 2014 Dejan Josifović, the paranoid times

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
