(ns calculator.statemachine.state-table
  (:require [calculator.statemachine.state-tag :as state-tag]
            [calculator.statemachine.state :as state]
            [calculator.statemachine.event :as event]

            [cljs.core.match :refer-macros [match]]))


(def table
  {[state-tag/start event/num-sig]            state/operand1
   [state-tag/operand1 event/operator-sig]    state/operator-entered
   [state-tag/operator-entered event/num-sig] state/operand2
   [state-tag/operand2 event/equal-sig]       (fn [s _] (state/start s))})


(defn table2 [tag sig default-fn]
  (let [;; HACK: to use value in the match clause. Using the value with namespace didn't work.
        start            state-tag/start
        operand1         state-tag/operand1
        operator-entered state-tag/operator-entered
        operand2         state-tag/operand2
        num-sig          event/num-sig
        operator-sig     event/operator-sig
        equal-sig        event/equal-sig]
    (match [tag sig]
      [start num-sig] state/operand1
      [operand1 operator-sig] state/operator-entered
      [operator-entered num-sig] state/operand2
      [operand2 equal-sig] (fn [s _] (state/start s))
      :else default-fn)))


(defn transition-state [{:keys [state] :as app-state} {:keys [signal] :as event}]
  (let [transition-fn (table2 state signal state/ignore)]
    (transition-fn app-state event)))
