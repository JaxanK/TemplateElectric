(ns TemplateElectric.ElectricGUI.Prompts.FilterListSelectShortList
  (:require [hyperfiddle.electric :as e]
            [hyperfiddle.electric-dom2 :as dom]
            [hyperfiddle.electric-ui4 :as ui]
            [TemplateElectric.ElectricGUI.Prompts.FilterListSelect :as FilterListSelect]
    
            #?(:clj [TemplateElectric.ApplicationControls :as AppControls])))

;Better to do this as a filter list with it being extended by a short list and full list etc.

#?(:clj (def State! (atom {:LineH1 "Select an Item"
                           :Linep1 "Block info 1"
                           :linep2 "Block info 2"
                           :FullSet [1 2 3 4]
                           :Selected ""
                           :PlaceholderText-Filter "Enter Filter Criteria"
                           :PlaceHolderText-Selected "Edit Selected"
                           :EnteredText ""
                           :Callback (fn [] (reset! AppControls/PromptActive false)) })))

;-------------- Window Code Snippet--------------------------------------
(do
  #?(:cljs (def window! (atom {:height (.-innerHeight js/window)
                               :width  (.-innerWidth  js/window)})))
  #?(:cljs (defn setWindow! [& _] (swap! window! assoc
                                    :height (.-innerHeight js/window)
                                    :width (.-innerWidth  js/window))))
  #?(:cljs (.addEventListener js/window "resize" setWindow!)))
;------------------------------------------------------------------------

(e/defn Prompt []
  (e/client
    (let [state (e/server (e/watch State!))
          window (e/client (e/watch window!))]
      (new FilterListSelect/Prompt state)
      )))