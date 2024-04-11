(ns TemplateElectric.ElectricGUI.ListBox
  (:require [hyperfiddle.electric :as e]
            [hyperfiddle.electric-dom2 :as dom]
            [hyperfiddle.electric-ui4 :as ui]))

(e/defn ListBox [strings selected]
    (e/client
    (let [*selected (if (= (type (atom "")) (e/server (type selected))) ;This dumb predicate used because the other options not working for cljs I think
                      selected (atom selected))
          selected (e/server (e/watch *selected))]
      (dom/div
        (dom/ul (dom/style {:overflow-y :auto})
          (e/for [entry strings]
            (dom/li (dom/text entry)
              (dom/props {:style {:cursor :pointer
                                  :color (if (= selected entry) :white :inherit)
                                  :background-color (if (= selected entry) :blue :inherit)
                                  :padding "0.1rem 0.5rem"}})
              (dom/on "click" (e/fn [_] (reset! *selected entry))))))))))