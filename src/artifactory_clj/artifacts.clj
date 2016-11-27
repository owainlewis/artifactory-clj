(ns artifactory-clj.artifacts
  (require [artifactory-clj.core :as core]
           [clojure.java.io :as io]))

(defn deploy-artifact
  "Deploy an artifact to the specified destination.
   You can also attach properties as part of deploying artifacts.
   Requires a user with 'deploy' permissions (can be anonymous)"
  [config local-file-path repo-key file-path]
  (core/artifactory-request config
    {:method :put
     :path (core/path-builder repo-key file-path)
     :body (io/input-stream local-file-path)}))

(defn folder-info
  "For virtual use, the virtual repository returns the unified children. Supported by local, local-cached and virtual repositories.
   GET /api/storage/{repoKey}/{folder-path}"
  [config repo-key folder-path]
  (core/artifactory-request config
    {:method :get
     :path (core/path-builder "api/storage" repo-key folder-path)}))

(defn file-info
  "GET /api/storage/{repoKey}/{filePath}
   For virtual use the virtual repository returns the resolved file. Supported by local, local-cached and virtual repositories."
  [config repo-key file-path]
  (core/artifactory-request config
    {:method :get
     :path (core/path-builder "api/storage" repo-key file-path)}))

(defn get-storage-summary-info
  "Returns storage summary information regarding binaries, file store and repositories.
   GET /api/storageinfo"
  [config]
  (core/artifactory-request config
    {:method :get
     :path (core/path-builder "api/storageinfo")}))
