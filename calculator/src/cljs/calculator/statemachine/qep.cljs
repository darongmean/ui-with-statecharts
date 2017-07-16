(ns calculator.statemachine.qep
  (:require [calculator.statemachine.event :as event]
            [calculator.statemachine.state :as state]))


(declare start-state)

(declare operand1-state)

(declare operator-entered-state)

(declare operand2-state)


(defn start-state [app-state {:keys [signal] :as event}]
  (condp = signal
    event/num-sig
    (-> app-state
      (state/operand1 event)
      (assoc :state-handler-fn operand1-state))

    (state/ignore app-state event)))


(defn operand1-state [app-state {:keys [signal] :as event}]
  (condp = signal
    event/operator-sig
    (-> app-state
      (state/operator-entered event)
      (assoc :state-handler-fn operator-entered-state))

    (state/ignore app-state event)))

(defn operator-entered-state [app-state {:keys [signal] :as event}]
  (condp = signal
    event/num-sig
    (-> app-state
      (state/operand2 event)
      (assoc :state-handler-fn operand2-state))

    (state/ignore app-state event)))

(defn operand2-state [app-state {:keys [signal] :as event}]
  (condp = signal
    event/equal-sig
    (-> app-state
      (state/start)
      (assoc :state-handler-fn start-state))

    (state/ignore app-state event)))


(def init-state
  (-> state/init
    (assoc :state-handler-fn start-state)))


(defn transition-state [{:keys [state-handler-fn] :as app-state} event]
  (state-handler-fn app-state event))
