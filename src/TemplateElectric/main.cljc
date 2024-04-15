(ns TemplateElectric.main
  (:require [hyperfiddle.electric :as e]
            [hyperfiddle.electric-dom2 :as dom]
            [hyperfiddle.electric-ui4 :as ui]
            [hyperfiddle.electric-svg :as svg]

            [TemplateElectric.ElectricGUI.tab1 :as tab1]
            [TemplateElectric.ElectricGUI.tab2 :as tab2]
            [TemplateElectric.ElectricGUI.tab3 :as tab3]
            [TemplateElectric.ElectricGUI.tab4 :as tab4]
            [TemplateElectric.ElectricGUI.prompt :as prompt]
            
            #?(:clj [TemplateElectric.version    :as ver])
            #?(:clj [TemplateElectric.ApplicationControls :as appcntrls])
            #?(:clj [TemplateElectric.console :as console])
            ))

;; Saving this file will automatically recompile and update in your browser

#?(:clj (defonce PromptActive! appcntrls/PromptActive))

;#?(:clj (defonce version ver/version))
(e/def DisplayVersion (e/server ver/version))

; LEts say that I have a really long comment string and I am ramble on and it
; gets annoyient line fdfsfksdsofdsonfdonoifdng kfnslflkfsknflk


#?(:cljs 
   (defonce GUIState! (atom {:ActiveTab :Tab1
                             :Tabs [{:Tag :Tab1 :DisplayText "Tab 1" :icon "mdi-numeric-1-box"}
                                    {:Tag :Tab2 :DisplayText "Tab 2" :icon "mdi-numeric-2-box"}
                                    {:Tag :Tab3 :DisplayText "Tab 3" :icon "mdi-numeric-3-box"}
                                    {:Tag :Tab4 :DisplayText "Tab 4" :icon "mdi-numeric-4-box"}]}))
     )
(e/def GUIState (e/client (e/watch GUIState!)))

(e/defn allss [this] 
  (e/client  
    (let [htmlobj (.-target this)]
    (js/alert (str (.-scrollHeight htmlobj) " Test Popup")))))

(e/defn ChangeActiveTab [tabkey]
  (e/client (swap! GUIState! assoc :ActiveTab tabkey)))


(e/defn Top []
  (e/client
    (let [activeTab (:ActiveTab GUIState)]
      (dom/nav (dom/props {:class "bg-slate-100 max-w-full sticky top-0"})
        (dom/div (dom/props {:class "flex pl-6"})

          (dom/div  (dom/props {:class "box-content w-64 max-sm:hidden"})
            (dom/img (dom/props {:class "size-16" :alt "Logo1" :src "assets\\img\\Logo.svg"}))
            (dom/div (dom/props {:class "text-2xl font-semibold font-mono whitespace-nowrap text-blue-900"}) (dom/text "TemplateElectric")))

          (dom/div (dom/props {:class "w-full block" :id "navbar-default"})
            (dom/ul (dom/props {:class "align-top font-medium flex mt-4 flex-row"})
              (let [activeStyle "align-top block py-2 px-3 rounded bg-transparent text-green-700 p-0 hover:text-green-800 hover:cursor-pointer"
                    normalStyle "align-top block py-2 px-3 rounded bg-transparent text-blue-700  p-0 hover:text-green-800 hover:cursor-pointer"]
                (e/for [tab (:Tabs GUIState)]
                  (let [tag (:Tag tab)
                        icon (:icon tab)
                        tabStyle (if (= tag activeTab) activeStyle normalStyle)]
                    (dom/li (dom/a
                              (dom/on "click" (e/fn [_] (ChangeActiveTab. tag)))
                              (dom/props {:class tabStyle
                                          :aria-current "page"})

                              (if icon (dom/span (dom/props {:class "iconify size-10" :data-icon icon})))
                              (dom/div (dom/props {:class ""}) (dom/text (:DisplayText tab))))))))))
          (dom/p  (dom/props {:class "text-lg grow font-light font-sans "})  (dom/text (str "Version: " DisplayVersion))))))))



(e/defn CmdHistory [history]
  (e/client
    (dom/div (dom/style {:max-height "200px"
                         :overflow-y :auto})
      (dom/on "click" allss)
      (e/for [x history]
        (dom/p (dom/text (:Text x)))))))
  
  

(e/defn Console []
  (e/client
    (let [history (e/server (e/watch console/*ConsoleStringEntries))
          ;input   (e/server (e/watch console/*ConsoleInput))
          ]
      (dom/nav (dom/props {:class ""}) (dom/style {:background-color :aliceblue
                                                                         ;:max-height "200px"
                                                                         ;:overflow-y :auto
                                                                         })
        ;(CmdHistory. history)
        ))))

(e/defn Main [ring-request]
  (e/client
    (binding [dom/node js/document.body]
      (if-not (e/server (e/watch PromptActive!))
        (do
          (Top.)
          (dom/div (dom/props {:class "block pl-10"})
            (case (:ActiveTab GUIState)
              :Tab1 (tab1/Tab.)
              :Tab2 (tab2/Tab.)
              :Tab3 (tab3/Tab.)
              :Tab4 (tab4/Tab.)
              ))
          ;(Console.)
          )
        (do
          ;(dom/h1 (dom/text "Prompt"))
          (prompt/PromptElecticCode.))))))
