(ns calculator.user-interface-object
  (:require [rum.core :as rum]))


(rum/defc main-screen < rum/reactive [*app-state]
  (let [[_ {:keys [result]} :as state] (rum/react *app-state)]
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


(defn render [*app-state js-dom]
  (rum/mount (main-screen *app-state) js-dom))
