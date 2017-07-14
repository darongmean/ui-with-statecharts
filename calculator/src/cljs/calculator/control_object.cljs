(ns calculator.control-object)


;; States

(def start-state-tag :1.start)


(def operand1-state-tag :2.operand1)


(def operator-entered-state-tag :3.operator_entered)


(def operand2-state-tag :4.operand2)


;; Signals

(def num-sig :num)


(def decimal-sig :decimal)


(def operator-sig :operator)


(def equal-sig :equal)


;; Events

(defn num-event [n] {:signal num-sig :number n})


(defn decimal-point-event [] {:signal decimal-sig})


(defn operator-event [op] {:signal operator-sig :operator op})


(defn equal-event [] {:signal equal-sig})


;; Transitions

(defn ignore [app-state event]
  (do (println "no match signal: " event)
      app-state))


(defn operand1-state [app-state {:keys [number]}]
  (-> app-state
    (assoc :state operand1-state-tag)
    (assoc :num1 number)))


(defn operator-entered-state [app-state {:keys [operator]}]
  (-> app-state
    (assoc :state operator-entered-state-tag)
    (assoc :operator operator)))


(defn operand2-state [app-state {:keys [number]}]
  (-> app-state
    (assoc :state operand2-state-tag)
    (assoc :num2 number)))


(defn start-state [{:keys [operator num1 num2] :as app-state}]
  (-> app-state
    (assoc :state start-state-tag)
    (assoc :num1 0)
    (assoc :num2 0)
    (assoc :result (operator num1 num2))))


(defn transition-state [{:keys [state] :as app-state} {:keys [signal] :as event}]
  (condp = state
    start-state-tag
    (condp = signal
      num-sig (operand1-state app-state event)
      (ignore app-state event))

    operand1-state-tag
    (condp = signal
      operator-sig (operator-entered-state app-state event)
      (ignore app-state event))

    operator-entered-state-tag
    (condp = signal
      num-sig (operand2-state app-state event)
      (ignore app-state event))

    operand2-state-tag
    (condp = signal
      equal-sig (start-state app-state)
      (ignore app-state event))))


(defn init-state [*app-state]
  (reset! *app-state {:state  start-state-tag
                      :num1   0
                      :num2   0
                      :operator nil
                      :result 0}))


(defn dispatch [*state event]
  (swap! *state transition-state event))
