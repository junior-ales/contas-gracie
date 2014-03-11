(defproject contas-gracie "0.1.0-SNAPSHOT"
  :min-lein-version "2.3.4"
  :description "Dividindo as contas de uma maneira mais justa que um mata-leão encaixado"
  :url "http://contas-grancie.com"
  :license {:name "The MIT License (MIT)"
            :url "https://github.com/junior-ales/contas-gracie/blob/master/LICENSE"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [ring "1.2.1"]
                 [compojure "1.1.6"]
                 [de.ubercode.clostache/clostache "1.3.1"]]
  :main contas-gracie.core)
