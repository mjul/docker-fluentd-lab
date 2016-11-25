(ns capitalizer.core
  (:gen-class)
  (:require [clojure.data.json :as json]))

(defn capitalize-string
  [s]
  (.toUpperCase s))

(defn capitalize-values
  [key value]
  (if (string? value)
    (capitalize-string value)
    value))


(defn add-metadata
  [obj]
  (-> obj
      (assoc-in ["meta" "application_id"] "capitalizer")))

(defn write-message
  [msg]
  (->> msg
       add-metadata
       json/write-str
       println))

(defn log-message
  [txt]
  {"data" {"message" txt}
   "type" "log_message"})


(defn -main
  "Capitalize all the string values in the JSON input"
  [& args]
  (write-message (log-message "Starting capitalizer..."))
  (doseq [line (line-seq (java.io.BufferedReader. *in*))]
    (write-message (json/read-str line :value-fn capitalize-values)))
  (write-message (log-message "Capitalizer done.")))
