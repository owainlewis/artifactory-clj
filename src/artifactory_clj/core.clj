(ns artifactory-clj.core
  (:require [clj-http.client :as client]
            [cheshire.core :as json]
            [clojure.string :as s]
            [clojure.java.io :as io]))

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

(defn- artifactory-request
  "Internal constructor for all HTTP requests that will merge in config values for auth etc"
  [config request & opts]
  (let [{:keys [username password auth-token]} (:auth config)
        request-path (:path request)
        normalized-request (dissoc request :path)
        response (client/request (assoc normalized-request
                                   :basic-auth [username password]
                                   :throw-exceptions false
                                   :url (path-builder (:host config) "artifactory" request-path)))]
    (-> response :body (json/parse-string true))))

(defn artifact-deploy
  "Deploy an artifact to artifactory"
  [config local-file-path repo-key file-path]
  (artifactory-request config
    {:method :put
     :path (path-builder repo-key file-path)
     :body (io/input-stream local-file-path)}))

;; Artifacts

(defn folder-info [config artifact-path]
  (artifactory-request config
                       {:method :get
                        :path (str "/api/storage/" artifact-path)}))

(defn file-info
  "GET /api/storage/{repo-key}/{file-path}"
  [config repo-key file-path]
  (construct-full-path (:host config) "/api/storage" repo-key file-path))
