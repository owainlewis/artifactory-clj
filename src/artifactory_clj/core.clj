(ns artifactory-clj.core
  (:require [clj-http.client :as client]
            [cheshire.core :as json]))

(def ^:dynamic dispatch-request true)

(def sample-config
  {:host "http://192.168.99.100:8080"
   :auth {:username "admin"
          :password "password"
          :auth-token ""}})

(defn path-builder
  "Utility function for constructing artifactory paths"
  [& parts]
  (->> (interpose "/" parts)
       (apply str)))

(defn artifactory-request
  "Internal constructor for all HTTP requests that will merge in config values for auth etc.
   Not that you can bind dispatch-request to false in order to view or test the underlying HTTP request
   Example:

   (binding [core/dispatch-request false]
     (artifacts/file-info config \"ext-release-local\" \"project.clj\")) =>
       {:method :get,
        :basic-auth [\"admin\" \"password\"],
        :throw-exceptions false,
        :url \"http://192.168.99.100:8080/artifactory/api/storage/ext-release-local/project.clj\"}
  "
  [config request & opts]
  (let [{:keys [username password auth-token]} (:auth config)
        request-path (:path request)
        normalized-request (dissoc request :path)
        final-request (assoc normalized-request
                        :basic-auth [username password]
                        :throw-exceptions false
                        :url (path-builder (:host config) "artifactory" request-path))]
    (if dispatch-request
      (-> (client/request final-request) :body (json/parse-string true))
      final-request)))
