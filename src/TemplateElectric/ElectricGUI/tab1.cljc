(ns TemplateElectric.ElectricGUI.tab1
    (:require [hyperfiddle.electric :as e]
              [hyperfiddle.electric-dom2 :as dom]
              [hyperfiddle.electric-ui4 :as ui]
              [TemplateElectric.ElectricGUI.componentlibrary :as cl]
              [TemplateElectric.ElectricGUI.ListBox :as lb]))



(e/defn Tab []
  (e/client
   (dom/div (dom/props {:class "pb-6"}) (dom/text "This is Tab 1"))

   (new cl/ChangingColorComponent)

   (dom/br)

   (ui/button  (e/fn [] (e/server (println "This is printing on the server console!"))) (dom/props {:class "border border-spacing-2 bg-gray-400 p-4"}) (dom/text "Server Println Button"))
   (ui/button  (e/fn [] (e/client (println "This is printing on the client console!"))) (dom/props {:class "border border-spacing-2 bg-gray-400 p-4"}) (dom/text "Client Println Button!"))


   ;From Electric tutorial #1
   (let [c (e/client e/system-time-ms)
         s (e/server e/system-time-ms)]

     (dom/div (dom/props {:class "bg-slate-100"}) (dom/text "client time: " c))
     (dom/div (dom/props {:class ""}) (dom/text "server time: " s))
     (dom/div (dom/props {:class ""}) (dom/text "difference: " (- s c))))))

