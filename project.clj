(defproject sk-aws-athena "0.1.0-SNAPSHOT"
  :description "Starter Kit: AWS Athena"
  :url "https://github.com/jaju/sk-aws-athena"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url  "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [seancorfield/next.jdbc "1.0.4"]
                 [com.cognitect.aws/api "0.8.352"]]
  :repl-options {:init-ns sk-aws-athena.core}
  :resource-paths ["libs/AthenaJDBC42_2.0.7.jar"])
