(ns TemplateElectric.StandardSpecs
  (:require [clojure.math :as math]
            [clojure.spec.alpha :as s]
            [jaxank.ExtensionLibs.JaxExtension :refer [nor]]
            ))


(s/def ::decent-number        (s/and number? #(nor (NaN? %) (infinite? %))))
(s/def ::decent-rad           (s/and number? #(nor (NaN? %) (infinite? %)) #(and (> % 0) (< % (* 2 math/PI)))))
(s/def ::non-negative         (s/and number? #(<= 0 %)))
(s/def ::non-negative-decent  (s/and number? #(<= 0 %) #(nor (NaN? %) (infinite? %))))

