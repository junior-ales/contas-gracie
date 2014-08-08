(defproject contas-gracie "0.1.0-SNAPSHOT"
  :min-lein-version "2.3.4"
  :description "Dividindo as contas de uma maneira mais justa que um mata-leão encaixado"
  :url "http://contas-grancie.com"
  :license {:name "The MIT License (MIT)"
            :url "https://github.com/junior-ales/contas-gracie/blob/master/LICENSE"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [compojure "1.1.6"]
                 [org.clojure/java.jdbc "0.3.3"]
                 [org.xerial/sqlite-jdbc "3.7.2"]
                 [org.clojure/tools.namespace "0.2.4"]
                 [hiccup "1.0.5"]
                 [ring-server "0.3.1"]
                 [liberator "0.10.0"]
                 [migratus "0.7.0"]
                 [korma "0.3.3"]
                 [cheshire "5.2.0"]
                 [log4j "1.2.15" :exclusions [javax.mail/mail
                                              javax.jms/jms
                                              com.sun.jdmk/jmxtools
                                              com.sun.jmx/jmxri]]]
  :plugins [[lein-ring "0.8.10"]
            [migratus-lein "0.1.0"]
            [com.jakemccrary/lein-test-refresh "0.5.0"]]
  :migratus {:store :database
           :migration-dir "migrations"
           :db {:classname "org.sqlite.JDBC",
                :subprotocol "sqlite",
                :subname "resources/db/prod.sq3"}}
  :ring {:handler contas-gracie.handler/app
         :init contas-gracie.handler/init
         :destroy contas-gracie.handler/destroy
         :auto-reload? true
         :auto-refresh? true}
  :aot :all
  :profiles {:production
             {:ring
              {:open-browser? false
               :stacktraces? false
               :auto-reload? false}}
             :dev
             {:dependencies [[ring-mock "0.1.5"]
                             [ring/ring-devel "1.2.1"]]}})
