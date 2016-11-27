# Artifactory

A Clojure client for working with JFrog Artifactory.

## Usage

All API request require some basic configuration. 

The config must specify the host/port of the artifactory instance and some basic auth information

```clojure
(def config
  {:host "http://192.168.99.100:8080"
   :auth {:username "admin"
          :password "password"
          :auth-token ""}})
```

### Full example

```clojure
(ns my-ns
  (:require [artifactory-clj.artifacts :refer :all]))

;; Upload an artifact to artifacty
(artifacts/deploy-artifact config
  "my-service-1.0.0.jar"
  "ext-release-local"
  "com/oracle/my-service/1.0.0/my-service-1.0.0.jar")

;; Fetch information about the artifact
(artifacts/file-info config
  "ext-release-local"
  "com/oracle/my-service/1.0.0/my-service-1.0.0.jar")
```

### Artifacts

WIP

### Testing / debugging

All the underlying HTTP requests can be exposed for testing. The following example shows how to get the underlying clj-http request as a map
without dispatching it.

```clojure
(binding [core/dispatch-request false]
  (artifacts/file-info config "ext-release-local" "project.clj")) =>

;; {:method :get,
;;  :basic-auth ["admin" "password"],
;;  :throw-exceptions false,
;;  :url "http://192.168.99.100:8080/artifactory/api/storage/ext-release-local/project.clj"}

```

## License

Copyright © 2016 Owain Lewis

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
