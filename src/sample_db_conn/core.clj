(ns sample-db-conn.core
  (:gen-class)
  (:require [environ.core :as env]))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))

(defn env-variable [var-k]
  (env/env var-k))
