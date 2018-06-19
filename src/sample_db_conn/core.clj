(ns sample-db-conn.core
  (:gen-class)
  (:require [environ.core :as env]
            [clojure.java.jdbc :as jdbc]
            [honeysql.core :as sql]
            [honeysql.helpers :as h]))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))

(def pgurl
  (env/env :database-url))

(defn create-table [table-name]
  (jdbc/with-db-connection
    [db pgurl]
    (jdbc/execute!
      db
      (format "CREATE TABLE %s (col1 varchar(5) NOT NULL, col2 varchar(5) NOT NULL)"
              (name table-name)))))
;; (create-table :doom)

(defn insert-in-table [table-name coll]
  (jdbc/with-db-connection
    [db pgurl]
    (jdbc/insert-multi! db table-name coll)))
;; (insert-in-table :doom [{:col1 "a", :col2 "b"}
;;                         {:col1 "c", :col2 "d"}
;;                         {:col1 "e", :col2 "f"}])

(defn select-from-table []
  (jdbc/with-db-connection
    [db pgurl]
    (jdbc/insert-multi! db :doom [{:col1 "6", :col2 "9"}
                                  {:col1 "6", :col2 "9"}
                                  {:col1 "6", :col2 "9"}])
    (jdbc/query db "SELECT * FROM doom")))
;; (select-from-table)

;; (assoc (assoc (assoc {} :a 1) :b 2) :c 3)
;; equals
#_(-> (assoc {} :a 1)
    (assoc :b 2)
    (assoc :c 3))

(defn select-from-table-2 []
  (jdbc/with-db-connection
    [db pgurl]
    (jdbc/query db (sql/format
                     (-> (h/select :%distinct.col1)
                         (h/from :doom)
                         (h/where [:<> :col2 "9"]))))))

(defn select-from-table-3 [s]
  (jdbc/with-db-connection
    [db pgurl]
    (jdbc/query db (sql/format
                     {:select [:%distinct.col1]
                      :from [:doom]
                      :where [:<> :col2 s]}))))
