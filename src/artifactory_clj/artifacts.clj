(ns artifactory-clj.artifacts
  (require [artifactory-clj.core :as core]
           [clojure.java.io :as io]))

;; Folder Info
(defn folder-info
  "For virtual use, the virtual repository returns the unified children.
   Supported by local, local-cached and virtual repositories.
   GET /api/storage/{repoKey}/{folder-path}"
  [config repo-key folder-path]
  (core/artifactory-request config
    {:method :get
     :path (core/path-builder "api/storage" repo-key folder-path)}))

;; File Info
(defn file-info
  "GET /api/storage/{repoKey}/{filePath}
   For virtual use the virtual repository returns the resolved file.
   Supported by local, local-cached and virtual repositories."
  [config repo-key file-path]
  (core/artifactory-request config
    {:method :get
     :path (core/path-builder "api/storage" repo-key file-path)}))

;; Get Storage Summary Info
(defn get-storage-summary-info
  "Returns storage summary information regarding binaries, file store and repositories.
   GET /api/storageinfo"
  [config]
  (core/artifactory-request config
    {:method :get
     :path (core/path-builder "api/storageinfo")}))

;; Item Last Modified
(defn item-last-modified
  "Retrieve the last modified item at the given path.
   If the given path is a folder, the latest last modified item is searched for recursively.
   Supported by local and local-cached repositories
   GET /api/storage/{repoKey}/{item-path}?lastModified"
  [config repo-key item-path]
  (core/artifactory-request config
    {:method :get
     :path (core/path-builder "api/storage" repo-key item-path "?lastModified")}))

;; File Statistics
(defn file-statistics
  "Item statistics record the number of times an item was downloaded,
   last download date and last downloader.
   Supported by local and local-cached repositories."
  [config repo-key item-path]
  (core/artifactory-request config
    {:method :get
     :path (core/path-builder "api/storage" repo-key item-path "?stats")}))

;; Item Properties
;; Set Item Properties
;; Delete Item Properties
;; Set Item SHA256 Checksum
;; Retrieve Artifact
;; Retrieve Latest Artifact
;; Retrieve Build Artifacts Archive
;; Retrieve Folder or Repository Archive
;; Trace Artifact Retrieval
;; Archive Entry Download
;; Create Directory

;; Deploy Artifact
(defn deploy-artifact
  "Deploy an artifact to the specified destination.
   You can also attach properties as part of deploying artifacts.
   Requires a user with 'deploy' permissions (can be anonymous)"
  [config local-file-path repo-key file-path]
  (core/artifactory-request config
    {:method :put
     :path (core/path-builder repo-key file-path)
     :body (io/input-stream local-file-path)}))

;; Deploy Artifact by Checksum
;; Deploy Artifacts from Archive
;; Push a Set of Artifacts to Bintray
;; Push Docker Tag to Bintray
;; Distribute Artifact
;; File Compliance Info
;; Delete Item
;; Copy Item
;; Move Item
;; Get Repository Replication Configuration
;; Set Repository Replication Configuration
;; Update Repository Replication Configuration
;; Delete Repository Replication Configuration
;; Scheduled Replication Status
;; Pull/Push Replication
;; Pull/Push Replication (Deprecated)
;; Create or Replace Local Multi-push Replication
;; Update Local Multi-push Replication
;; Delete Local Multi-push Replication
;; Enable or Disable Multiple Replications
;; Get Global System Replication Configuration
;; Block System Replication
;; Unblock System Replication
;; Artifact Sync Download
;; Folder Sync (Deprecated)
;; File List
;; Get Background Tasks
;; Empty Trash Can
;; Delete Item From Trash Can
;; Restore Item from Trash Can
;; Optimize System Storage
