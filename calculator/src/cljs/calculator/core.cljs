(ns calculator.core
  (:require [calculator.control-object :as control-object]
            [calculator.user-interface-object :as user-interface-object]))


(enable-console-print!)


(defn render []
  (->
    (control-object/init)
    (user-interface-object/render (. js/document (getElementById "app")))))
