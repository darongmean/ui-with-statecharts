(ns calculator.core
  (:require [rum.core :as rum]))


(enable-console-print!)


(defonce app-state (atom [:1.start {:result 0}]))


(rum/defc greeting < rum/reactive []
  (let [[_ {:keys [result]} :as state] (rum/react app-state)]
    [:div
     [:h1 "Calculator"]
     [:table
      [:tbody
       [:tr
        [:td {:colSpan "5"} result]]
       [:tr
        [:td [:button "7"]]
        [:td [:button "8"]]
        [:td [:button "9"]]
        [:td [:button "C"]]
        [:td [:button "CE"]]]
       [:tr
        [:td [:button "4"]]
        [:td [:button "5"]]
        [:td [:button "6"]]
        [:td [:button "+"]]
        [:td [:button "-"]]]
       [:tr
        [:td [:button "1"]]
        [:td [:button "2"]]
        [:td [:button "3"]]
        [:td [:button "x"]]
        [:td [:button "/"]]]
       [:tr
        [:td {:colSpan 2} [:button "0"]]
        [:td [:button "."]]
        [:td [:button "="]]
        [:td [:button "%"]]]]]
     [:h4 "state"]
     [:div (str state)]]))


(defn render []
  (rum/mount (greeting) (. js/document (getElementById "app"))))
