(ns TemplateElectric.ElectricGUI.tab4
    (:require [hyperfiddle.electric :as e]
            [hyperfiddle.electric-dom2 :as dom]
            [hyperfiddle.electric-ui4 :as ui]))

(e/defn Tab []
  (e/client (dom/div (dom/text "This is Tab 4"))))

