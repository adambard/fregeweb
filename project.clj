(defproject fregeweb "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [com.theoryinpractise.frege/frege "3.21.586-g026e8d7"]
                 [compojure "1.1.8"]
                 [http-kit "2.1.16"]
                 [ring/ring-defaults "0.1.1"]]
  :plugins [[lein-fregec "0.1.0-SNAPSHOT"]]
  :java-source-paths ["src/java"]
  :frege-source-paths ["src/frege"]
  :source-paths ["src/clj"]
  )
