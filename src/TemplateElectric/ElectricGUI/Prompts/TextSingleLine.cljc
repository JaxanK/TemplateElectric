(ns TemplateElectric.ElectricGUI.Prompts.TextSingleLine
  (:require [hyperfiddle.electric :as e]
            [hyperfiddle.electric-dom2 :as dom]
            [hyperfiddle.electric-ui4 :as ui]))

#?(:clj (def State! (atom {:LineH1 "Prompt"
                           :Linep1 ""
                           :linep2 ""
                           :PlaceholderText ""
                           :EnteredText ""})))
(e/defn Prompt []
  (e/client
    (let [state (e/server (e/watch State!))]
      (dom/div
        (dom/h1 (dom/text (:LineH1 state)))
        (dom/p  (dom/text (:Linep1 state)))
        (dom/p  (dom/text (:linep2 state)))
        (ui/input
          (:EnteredText state)
          (e/fn [x] (e/server (swap! State! assoc :EnteredText x)))
          (dom/props {:placeholder (:PlaceholderText state)}))))))