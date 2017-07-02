(ns calculator.control-object)


(defn init-state [*app-state]
  (reset! *app-state [:1.start {:result 0}]))

