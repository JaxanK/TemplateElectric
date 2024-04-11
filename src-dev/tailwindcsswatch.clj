(ns tailwindcsswatch
  (:require  [clojure.java.shell :refer [sh]]))


  (defn RunTailWindWatch "Hook that causes recompilation of tailwind output file" {:shadow.build/stage :compile-prepare} [build-state & args]
    ;(future (sh "RunTailwindWatch.bat" :dir (System/getProperty "user.dir")))
    (future (sh "powershell" "npx tailwindcss -i ./resources/public/TemplateElectric/input.css -o ./resources/public/TemplateElectric/output.css" :dir (System/getProperty "user.dir")))
    build-state)

  ;The watch version does not work on a build hook... at least with the stuff I tried
;; (let [!first-run? (volatile! true)]
;;   (defn RunTailWindWatch {:shadow.build/stage :compile-prepare} [build-state & args]
;;     (if !first-run?
;;       (do
;;         (vreset! !first-run? false)
;;         (future (sh "RunTailwindWatch.bat" :dir (System/getProperty "user.dir")))))
;;     build-state))

  
(comment
  (RunTailWindWatch)
  )
