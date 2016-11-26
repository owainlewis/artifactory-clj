# Artifactory

A Clojure client for working with JFrog Artifactory

## Usage

```clojure
(ns my-ns
  (:require [artifactory-clj.core :refer :all]))

;; Define config
(def config
  {:host "http://192.168.99.100:8080"
   :auth {:username "admin"
          :password "password"
          :auth-token ""}})

;; Upload a file to artifactory

(artifact-deploy config "package.zip" "com/oracle/java/package/1.0.0/package.zip")

```

## License

Copyright © 2016 Owain Lewis

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
