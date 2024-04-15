(ns TemplateElectric.ElectricGUI.componentlibrary
    (:require [hyperfiddle.electric :as e]
              [hyperfiddle.electric-dom2 :as dom]
              [hyperfiddle.electric-ui4 :as ui]))


(e/defn ChangingColorComponent []
  (e/client (dom/div (dom/props {:class "p-1 sm:bg-violet-500 md:bg-lime-500 lg:bg-slate-300 xl:bg-orange-400 2xl:bg-red-600"}) (dom/text "This is a color changinging reactive component, change window size to see it change"))))