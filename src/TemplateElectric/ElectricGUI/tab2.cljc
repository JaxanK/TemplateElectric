(ns TemplateElectric.ElectricGUI.tab2
    (:require [hyperfiddle.electric :as e]
            [hyperfiddle.electric-dom2 :as dom]
            [hyperfiddle.electric-ui4 :as ui]
              
              [TemplateElectric.ElectricGUI.componentlibrary :as cl]))

(e/defn Tab []
  (e/client 
   
   (dom/div (dom/text "This is Tab 2"))
   (cl/ChangingColorComponent)
   
   ))

