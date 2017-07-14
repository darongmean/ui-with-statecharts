(ns calculator.statemachine.event)


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
