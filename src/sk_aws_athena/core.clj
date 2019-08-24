(ns sk-aws-athena.core
  (:require [cognitect.aws.credentials :as aws-creds]
            [sk-aws-athena.db.athena :as athena]
            [next.jdbc :as jdbc]))

(defn get-config
  "Hook into your config-sourcing logic as appropriate,
  and return something like the following"
  []
  {:aws {
         :profile "default"                                 ; Your profile to use from ~/.aws/credentials
         :region  "ap-south-1"                              ; AWS region
         :athena  {:s3-output-location                      ; Athena needs a place to store results
                   "s3://your-bucket/query-results-path/"}}})


(defn aws-config [c]
  (let [aws (:aws c)
        aws (if-let [profile (:profile aws)]
              (let [updated-aws (aws-creds/fetch (aws-creds/profile-credentials-provider profile))]
                (merge aws updated-aws))
              aws)]
    aws))

(comment
  (defonce athena-connection (-> (get-config) ; Make sure you've used your own
                                 aws-config
                                 athena/get-connection))

  (jdbc/execute! athena-connection
                 ["select count(*) as num_records from my_db.my_table"]))
