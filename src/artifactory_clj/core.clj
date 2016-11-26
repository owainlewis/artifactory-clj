(ns artifactory-clj.core
  (:require [clj-http.client :as client]
            [cheshire.core :as json]
            [clojure.java.io :as io]))

(def sample-config
  {:host "http://192.168.99.100:8080"
   :auth {:username "admin"
          :password "password"
          :auth-token ""}})

(defn- artifactory-request
  "Internal constructor for all HTTP requests that will merge in config values for auth etc"
  [config request & opts]
  (let [{:keys [username password auth-token]} (:auth config)
        request-path (:path request)
        normalized-request (dissoc request :path)]
    (client/request
     (assoc normalized-request
            :basic-auth [username password]
            :url (str (:host config) request-path)))))

(defn artifact-deploy
  "Deploy an artifact to artifactory"
  [config file-path artifact-path]
  (let [{:keys [status body]} (artifactory-request config
                                {:method :put
                                 :path (str "/artifactory" artifact-path)
                                 :body (io/input-stream file-path)})]
    (when (= 201 status)
      (-> body (json/parse-string true)))))
