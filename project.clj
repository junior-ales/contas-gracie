(defproject contas-gracie "0.1.0-SNAPSHOT"
  :min-lein-version "2.3.4"
  :description "Dividindo as contas de uma maneira mais justa que um mata-leão encaixado"
  :url "http://contas-grancie.com"
  :license {:name "The MIT License (MIT)"
            :url "https://github.com/junior-ales/contas-gracie/blob/master/LICENSE"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [compojure "1.1.6"]
                 [org.clojure/java.jdbc "0.2.3"]
                 [org.xerial/sqlite-jdbc "3.7.2"]
                 [org.clojure/tools.namespace "0.2.4"]
                 [hiccup "1.0.5"]
                 [ring-server "0.3.1"]]
  :plugins [[lein-ring "0.8.10"]
            [com.jakemccrary/lein-test-refresh "0.5.0"]]
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
                             [ring/ring-devel "1.2.1"]
                             [liberator "0.10.0"]
                             [cheshire "5.2.0"]]}})
