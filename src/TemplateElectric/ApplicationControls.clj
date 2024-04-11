(ns TemplateElectric.ApplicationControls)

(def RunningProgramState (atom {:Profile :Dev}))

(defn EndTemplateElectric []
  (case (:Profile @RunningProgramState)
    :Dev (println "This would have shut things down but you are in dev so just kill your REPL")
    :Prod (System/exit 0)
    nil
    ))

(def UseOldController (atom true))

(def PromptActive (atom false))

(comment 
  (reset! PromptActive true)
  (reset! PromptActive false)

  (reset! UseOldController false)

  (str EndTemplateElectric)
)