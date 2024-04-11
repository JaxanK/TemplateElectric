(ns TemplateElectric.SystemTray
  (:require [jaxank.ExtensionLibs.JaxExtension :as je]
            [clj-systemtray.core :as tray]
            [TemplateElectric.version :as ver]
            [TemplateElectric.ApplicationControls :as AppCntrls]
            [TemplateElectric.V1.Run :as run]
            )
  (:import ;[java.awt SystemTray TrayIcon Toolkit PopupMenu MenuItem Menu]
           [java.awt.event ActionListener]))


(def Icon (atom nil))

(def IconPath "TemplateElectricLogo1.png") ; Must be a .PNG, JPEG, or there is one other file type, but not an .ico file!
(def IconTooltip "TemplateElectric Server")

(defn MainClickFn "the main function to call on a left click of the icon" [_event]
  (println "Open TemplateElectric GUI in Browser")
  (je/OpenInDefaultBrowser "http://localhost:8080/"))

(def PopupMenu (tray/popup-menu
                 
                 (tray/menu-item "Open GUI in Browser" MainClickFn)
                 (tray/separator)
                 (tray/menu-item (str "TemplateElectric version: " ver/version) (fn [_] ))
                 (tray/separator)
                 (tray/menu "Commands"
                   (tray/menu-item "Start Controller" (fn [& _] (run/StartControllerCLJR)))
                   (tray/menu-item "End Controller"   (fn [& _] (run/StopControllerCLJR)))
                   (tray/separator)
                   (tray/menu-item "Kill Controller"  (fn [& _] (run/EndControllerCljr))))
                   (tray/menu-item "Exit TemplateElectric" (fn [& _] (println "Ending TemplateElectric") (AppCntrls/EndTemplateElectric)))))


(defn StartSystemTray! "Call this to start the SystemTray Icon and Submenu (will automatically replace existing TrayIcon)" []
  ;Stop previous Icon if it is running
  (if @Icon (tray/remove-tray-icon! @Icon))

  (reset! Icon (tray/make-tray-icon! IconPath PopupMenu))

  ;Set the Tool tip (shows up on mouse hover)
  (.setToolTip @Icon IconTooltip)

  ;If Icon is left clicked, MainClickFn will get called
  (doto @Icon
    (.addActionListener
      (proxy [ActionListener] []
        (actionPerformed [event]
          (MainClickFn event))))))

(defn StopSystemTray! "Call this to stop the SystemTray Icon and Submenu" []
  (if @Icon (tray/remove-tray-icon! @Icon)))


(defn DisplaySystemTrayMessage "Display system tray notification. Not supported on all platforms (by Java classes). \ntype can be #{:none :info :warning :error}" [heading message type]
  (let [heading (if (string? heading) heading  "")
        message (if (string? message) message  "")
        type    (if (keyword?   type)    type  :none)]

    (tray/display-message @Icon heading message type)))

(comment
  (StartSystemTray!)
  (StopSystemTray!)

  (DisplaySystemTrayMessage "This is heading" "This is message" :none)
  (DisplaySystemTrayMessage nil "This is message" nil)
  (DisplaySystemTrayMessage "This is heading" nil nil)
  (DisplaySystemTrayMessage nil nil nil)
)