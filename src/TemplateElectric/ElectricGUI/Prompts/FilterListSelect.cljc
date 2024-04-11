(ns TemplateElectric.ElectricGUI.Prompts.FilterListSelect
  (:require [hyperfiddle.electric :as e]
            [hyperfiddle.electric-dom2 :as dom]
            [hyperfiddle.electric-ui4 :as ui]
            
            #?(:clj [TemplateElectric.ApplicationControls :as AppControls])
            ))

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

(e/defn Prompt [state]
  (e/client
    (let [;state (e/server (e/watch State!))
          window (e/client (e/watch window!))]
      (dom/div
        (dom/h1 (dom/text (:LineH1 state)))
        (dom/p  (dom/text (:Linep1 state)))
        (dom/p  (dom/text (:linep2 state)))
        (ui/input
          (:EnteredText state)
          (e/fn [x] (e/server (swap! State! assoc :EnteredText x)))
          (dom/props {:placeholder (:PlaceholderText-Filter state)}))

        (dom/ul (dom/style {:max-height (str (max 75 (- (:height window) 220)) "px")
                            :overflow-y :auto})
          (e/for [entry (:FullSet state)]
            (dom/li (dom/text entry)
              (dom/props
                {:style {:cursor :pointer
                         :color (if (= (:Selected state) entry) :white :inherit)
                         :background-color (if (= (:Selected state) entry) :blue :inherit)
                         :padding "0.1rem 0.5rem"}})
              (dom/on "click" (e/fn [_] (e/server
                                          (swap! State! (fn [state] (assoc state :Selected entry)))))))))

        (ui/input
          (:Selected state)
          (e/fn [x] (e/server (swap! State! assoc :Selected x)))
          (dom/props {:placeholder (:PlaceHolderText-Selected state)
                      :size 100}))

        (dom/br)
        (ui/button
          (e/fn [] (e/server ((:Callback @State!))))
          (dom/text "Submit"))))))