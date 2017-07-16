(ns calculator.statemachine.state-design-pattern
  (:require [calculator.statemachine.state-tag :as state-tag]
            [calculator.statemachine.event :as event]))


(defprotocol CalculatorState
  (on-num-event [this event])
  (on-operator-event [this event])
  (on-equal-event [this])
  (on-decimal-point-event [this]))


(defrecord StartState [state num1 num2 operator result])


(defrecord Operand1State [state num1 num2 operator result])


(defrecord OperatorEnteredState [state num1 num2 operator result])


(defrecord Operand2State [state num1 num2 operator result])


(extend-type StartState
  CalculatorState
  (on-num-event [this {:keys [number]}]
    (-> this
      (assoc :state state-tag/operand1)
      (assoc :num1 number)
      (map->Operand1State)))

  (on-operator-event [this _] this)
  (on-equal-event [this] this)
  (on-decimal-point-event [this] this))


(extend-type Operand1State
  CalculatorState
  (on-num-event [this _] this)

  (on-operator-event [this {:keys [operator]}]
    (-> this
      (assoc :state state-tag/operator-entered)
      (assoc :operator operator)
      (map->OperatorEnteredState)))

  (on-equal-event [this] this)
  (on-decimal-point-event [this] this))


(extend-type OperatorEnteredState
  CalculatorState
  (on-num-event [this {:keys [number]}]
    (-> this
      (assoc :state state-tag/operand2)
      (assoc :num2 number)
      (map->Operand2State)))

  (on-operator-event [this _] this)
  (on-equal-event [this] this)
  (on-decimal-point-event [this] this))


(extend-type Operand2State
  CalculatorState
  (on-num-event [this _] this)
  (on-operator-event [this _] this)

  (on-equal-event [{:keys [operator num1 num2] :as this}]
    (-> this
      (assoc :state state-tag/start)
      (assoc :num1 0)
      (assoc :num2 0)
      (assoc :result (operator num1 num2))
      (map->StartState)))

  (on-decimal-point-event [this] this))


(def init-state
  (map->StartState {:state    state-tag/start
                    :num1     0
                    :num2     0
                    :operator nil
                    :result   0}))


(defn transition-state [app-state {:keys [signal] :as event}]
  (condp = signal
    event/num-sig (on-num-event app-state event)
    event/operator-sig (on-operator-event app-state event)
    event/equal-sig (on-equal-event app-state)
    app-state))
