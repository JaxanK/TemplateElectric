(ns TemplateElectric.console
  (:require [clojure.spec.alpha :as s]))

(def PromptsSeq (atom []))

(def *ConsoleInput (atom {:Prompt "Enter Command Here: "
                          :PlaceHolderText "Enter something..."
                          :EnteredStr ""}))


(def *ConsoleStringEntries "Atom for All Console printouts and messages" (atom [{:Text "Welcome to command line"}]))


(defn SendMessage! [msg]
  (let [msgmap (cond
                 (string? msg) {:Type :Text
                                :Text msg}
                 (map? msg)    msg ;TODO check with valid spec

                 :else {:Type :unknown
                        :Text (str msg)})]
    (swap! *ConsoleStringEntries conj msgmap)))

(defn ClearConsole! []
  (reset! *ConsoleStringEntries [{:Text "Command line cleared!"}]))

(comment
  (SendMessage! "Hello There!")
  (SendMessage! {:Text "General Kenobi!" :ExtendedInfo 678 :Type :Message})
  (SendMessage! {:Text "Something went wrong!" :ExtendedInfo 678 :Type :ErrorMsg})
  (SendMessage! 5)
  
  (ClearConsole!)
  ;
  )


(defn Alert [])

(defn AskYesOrNo [])

(defn PickFromList [])
(defn PickFromFilterList [])

(defn GetString [])
(defn GetInt []) ;TODO add error string


;Ideas
;  Could add a display tag to messages to control how many messages should show and allow option to shop showing without clearing the atom