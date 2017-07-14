(ns calculator.control-object)


;; States

(def start-state :1.start)


(def operand1-state :2.operand1)


(def operator-entered-state :3.operator_entered)


(def operand2-state :4.operand2)


;; Signals

(def num-sig :num)


(def decimal-sig :decimal)


(def operator-sig :operator)


(def equal-sig :equal)


;; Events

(defn num [n] {:signal num-sig :number n})


(defn decimal-point [] {:signal decimal-sig})


(defn operator [o] {:signal operator-sig :operator o})


(defn equal [] {:signal equal-sig})


;; Transitions

(defn transition-state [state event]
  (condp = (:state state)
    start-state (condp = (:signal event)
                  num-sig (-> state
                            (assoc :state operand1-state)
                            (assoc :num1 (:number event)))
                  (do (println "no match signal: " event)
                      state))
    operand1-state (condp = (:signal event)
                     operator-sig (-> state
                                    (assoc :state operator-entered-state)
                                    (assoc :operator (:operator event)))
                     (do (println "no match signal: " event)
                         state))
    operator-entered-state (condp = (:signal event)
                             num-sig (-> state
                                       (assoc :state operand2-state)
                                       (assoc :num2 (:number event)))
                             (do (println "no match signal: " event)
                                 state))
    operand2-state (condp = (:signal event)
                     equal-sig (-> state
                                 (assoc :state start-state)
                                 (assoc :result ((:operator state) (:num1 state) (:num2 state))))
                     (do (println "no match signal: " event)
                         state))
    (do (println "no match state: " state)
        state)))


(defn init-state [*app-state]
  (reset! *app-state {:state  :1.start
                      :result 0}))


(defn dispatch [*state event]
  (swap! *state transition-state event))
