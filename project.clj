(defproject contas-gracie "0.1.0-SNAPSHOT"
  :min-lein-version "2.3.4"
  :description "Dividindo as contas de uma maneira mais justa que um mata-leão encaixado"
  :url "http://contas-grancie.com"
  :license {:name "The MIT License (MIT)"
            :url "https://github.com/junior-ales/contas-gracie/blob/master/LICENSE"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [ring "1.2.1"]
                 [compojure "1.1.6"]
                 [de.ubercode.clostache/clostache "1.3.1"]
                 [enlive "1.1.5"]
                 [org.clojure/java.jdbc "0.2.3"]
                 [org.xerial/sqlite-jdbc "3.7.2"]
                 [org.clojure/tools.namespace "0.2.4"]]
  :plugins [[lein-bower "0.2.0"]
            [com.jakemccrary/lein-test-refresh "0.5.0"]]
  :bower-dependencies [[foundation "5.2.1"]]
  :bower {:directory "resources/public/bower_components"}
  :main contas-gracie.core)
