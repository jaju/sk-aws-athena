(ns sk-aws-athena.db.athena
  (:import [com.simba.athena.jdbc DataSource]))

;; One-time system-level initialization
(defonce ^:private _
         (let [driver-class "com.simba.athena.jdbc.Driver"
               _            (Class/forName driver-class)]
           nil))

(defn- connection-url [opts-map]
  (let [user               (:aws/access-key-id opts-map)
        password           (:aws/secret-access-key opts-map)
        region             (:region opts-map)
        s3-output-location (-> opts-map :athena :s3-output-location)]
    (format
      "jdbc:awsathena://AwsRegion=%s;User=%s;Password=%s;S3OutputLocation=%s;UseResultsetStreaming=1"
      region
      user
      password
      s3-output-location)))

(defn- create-data-source
  [opts-map]
  (let [ds             (DataSource.)
        connection-url (connection-url opts-map)
        _              (.setURL ds connection-url)]
    ds))

(defn get-connection
  "Get a connection. Keep reference for re-use."
  [opts-map]
  (let [data-source (create-data-source opts-map)]
    (.getConnection data-source)))
