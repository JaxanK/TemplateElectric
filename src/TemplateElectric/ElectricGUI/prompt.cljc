(ns TemplateElectric.ElectricGUI.prompt
  (:require [hyperfiddle.electric :as e]
            [hyperfiddle.electric-dom2 :as dom]
            [hyperfiddle.electric-ui4 :as ui]

            [TemplateElectric.ElectricGUI.Prompts.TextSingleLine :as sl]
            [TemplateElectric.ElectricGUI.Prompts.FilterListSelect :as fls]
            [TemplateElectric.ElectricGUI.Prompts.FilterListSelectShortList :as flssl]
            ))


#?(:clj (defonce PromptType! (atom :TemplateElectric.ElectricGUI.Prompts.TextSingleLine)))

;(reset! PromptType! :TemplateElectric.ElectricGUI.Prompts.FilterListSelect)
;(reset! PromptType! :TemplateElectric.ElectricGUI.Prompts.FilterListSelectShortList)


(e/defn PromptElecticCode []
  (e/client
    #_{:clj-kondo/ignore [:unresolved-namespace]}
    (case (e/server (e/watch PromptType!))
      :TemplateElectric.ElectricGUI.Prompts.TextSingleLine (new sl/Prompt)
      :TemplateElectric.ElectricGUI.Prompts.FilterListSelect (new fls/Prompt)
      :TemplateElectric.ElectricGUI.Prompts.FilterListSelectShortList (new flssl/Prompt)
      (dom/div (dom/text "No Prompt type set")))))


;; (comment
;;   ;This code works but not on regen... no idea why
;;   ; Tried a few different things but nothing is working
;;   ; Something about the namespace code is not evaluating
;;   ; Even tried eval'ing a string generated on the server... but nothing works
;;   #?(:cljs
;;      (do
;;        (defn Find-All-Prompts []
;;          (let [allns            (all-ns)
;;                promptNamespaces (filter #(clojure.string/includes? (name (ns-name %)) "TemplateElectric.ElectricGUI.Prompts")   allns)]
;;            (for [x promptNamespaces]
;;              (let [interns (ns-interns x)]
;;                {:name (ns-name x)
;;                 :interns interns
;;                 :prompt ('Prompt interns)}))))
;;        (defn PromptMapGen []
;;          (let [allPrompts (Find-All-Prompts)]
;;            (into {} (for [prompt allPrompts]
;;                       (vector (keyword (:name prompt)) (:prompt prompt))))))

;;      ;(defonce PromptMap (PromptMapGen))
;;        ))
;;   (e/def PromptMap (e/client (PromptMapGen)))

;;   (e/defn PromptElecticCode []
;;     (e/client
;;       (let [prompt (find PromptMap (e/server (e/watch PromptType!)))]
;;         (if prompt
;;           (new prompt)
;;           (dom/div (dom/text "No Prompt type set")))))))

;Also tried multimethods but they did not work either... electric probably has to have them integrated somehow 
; as it was not working, but I could also not be understanding something