(defproject sample-db-conn "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [environ "1.1.0"]
                 [org.clojure/java.jdbc "0.7.6"]
                 [org.postgresql/postgresql "42.2.2"]
                 [honeysql "0.9.2"]
                 [cheshire "5.8.0"]]
  :main ^:skip-aot sample-db-conn.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
