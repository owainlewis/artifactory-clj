(ns artifactory-clj.core-test
  (:require [clojure.test :refer :all]
            [artifactory-clj.core :refer :all]))

(def local-config
  {:host "http://192.168.99.100:8080"
   :auth {:username "admin"
          :password "password"
          :auth-token ""}})

(deftest a-test
  (testing "FIXME, I fail."
    (is (= 0 1))))
