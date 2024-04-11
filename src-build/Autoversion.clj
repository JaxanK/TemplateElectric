(ns Autoversion
  (:require    [clojure.java.io :as io]
               [clojure.string :as str]
               ))


;For our build file, we will have automatically generated version numbers. 
; I rarely want to have to think about this, so the version of format Major.Minor.Patch will be used where the patch is automatically
; updated everytime we release a new jar file to the I drive. The way this is done is by query of the Idrive release folder via get-latest-version function below

;The startup program runs a batch script that is designed to find the latest jar file of that format on the I-drive, so it works in parallel to this

;Finally, I want to be able to access and show the version number somewhere in TemplateElectric and so easiest way I thought to do this is to simply have a version.clj file
; This build file will update the version.clj file via the update-version-file! fn. This will run prior to the build so it should show up properly in the build.

(defn get-latest-version "Mostly Chat-GPT generated function to find latest version in source directory" [directory]
  (let [jar-files (->> (io/file directory)
                       (.listFiles)
                       (filter #(str/ends-with? (.getName %) ".jar"))
                       (map #(.getName %))
                       (map #(re-find #"\d+\.\d+\.\d+" %))
                       (filter (complement nil?))
                       (map #(str/split % #"\."))
                       (sort-by #(Integer/parseInt (last %)))
                       (sort-by #(Integer/parseInt (second %)))
                       (sort-by #(Integer/parseInt (first %)))
                       (last))]
    (if jar-files
      (let [[major minor patch] jar-files]
        (str major "." minor "." (inc (Integer/parseInt patch))))
      "0.0.0")))

(defn update-version-file! [new-version path-to-ver-file]
  (spit path-to-ver-file (str "(ns TemplateElectric.version \"Version Information for current build. Managed by build.clj\")\n;This File autoupdated and replaced by build.clj, so don't edit directly\n(def version \"" new-version "\")")))
