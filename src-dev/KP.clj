(ns KP
  (:require [portal.api :as p]
            [scicloj.kindly.v4.api :as kindly]
            [scicloj.kindly.v4.kind :as kind]
            [scicloj.kind-portal.v1.api :as kp]
            ))


(defn clear [] (p/clear))
(defn open [] 
  (def p (kp/open-if-needed))
  (add-tap #'p/submit)
  )
(defn close [] (p/close))

(open)
(close)
