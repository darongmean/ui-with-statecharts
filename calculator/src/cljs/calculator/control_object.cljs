(ns calculator.control-object)


(defonce *app-state (atom))


(defn init []
  (reset! *app-state [:1.start {:result 0}])
  *app-state)
