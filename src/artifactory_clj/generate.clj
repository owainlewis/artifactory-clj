(ns artifactory-clj.generate
  (:require [clojure.string :as s]))

(def example-resource
  {:method "GET"
   :path "/api/build/{build-name}/{build-number}" })

(def example-resource
  {:method :post
   :path "api/docker/{repo-key}/v2/promote"
   :description "Promotes a Docker image from one repository to another"
   })

(defn extract-path-vars [path]
  (let [path-vars (map #(s/replace % #"[\{\}]" "")
                       (re-seq #"[\{][a-z-A-Z]*[\}]" path))]
    path-vars))

(defn make-fn [fn-name path-vars]
  (let [fn-args (apply str (interpose " " path-vars))]
    (format "[%s]" fn-args)))
